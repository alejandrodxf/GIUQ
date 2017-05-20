package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearchGroupActivity;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterRecyclerView;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Fragmento utilizado para obtener los integrantes de un grupo de investigación
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class MembersResearchGroupFragment extends Fragment implements AdapterRecyclerView.OnClickAdapterRecyclerView{

    public static final String MEMBERS_RESEARCH_GROUP="MEMBERS_RESEARCH_GROUP";

    /**
     * Atributo que representa la lista de líneas de investigación de un investigador
     */
    @BindView(R.id.recyclerViewMembers)
    protected RecyclerView recyclerViewMembers;

    /**
     * Atributo que representa la imagen para agregar investigadores
     */
    @BindView(R.id.imageViewAddMembers)
    protected ImageView imageViewAddMembers;

    /**
     * Atributo que representa las lineas de investigación de un investigador
     */
    private ArrayList<Researcher> researchers;
    private AdapterRecyclerView adapterMembersResearchGroup;
    private OnSelectedMembersResearchGroupListener listener;


    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public MembersResearchGroupFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static MembersResearchGroupFragment newInstance() {
        MembersResearchGroupFragment fragment = new MembersResearchGroupFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Método encargado de crear el fragmento
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        researchers = new ArrayList<>();
    }
    /**
     * Método encargado de cargar la vista asociada al fragmento
     * @param inflater
     * @param container
     * @param savedInstanceState información a ser recepcionada
     * @return la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_members_research_group, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    /**
     * Método llamado después de que se ha completado el onCreate
     * Se utiliza para inicializaciones finales y para modificar elementos de la interfaz
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageViewAddMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewResearchGroupActivity) getActivity()).showAddResearcherDialog(MembersResearchGroupFragment.class.getName());
            }
        });
        adapterMembersResearchGroup= new AdapterRecyclerView(researchers,this,MEMBERS_RESEARCH_GROUP);
        recyclerViewMembers.setAdapter(adapterMembersResearchGroup);
        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listener.sendListResearchers(researchers);
    }
    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface OnSelectedMembersResearchGroupListener {
        void onSelectedMembersResearchGroupListener(int position, ArrayList<Researcher> researchers);
        void sendListResearchers(ArrayList<Researcher>researchers);
    }

    /**
     * Método que permite notificar a la actividad, por medio de la interface del callback,
     * que un elemento ha sido seleccionado
     * @param pos posición de la línea de investigación seleccionada
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onSelectedMembersResearchGroupListener(pos,researchers);
    }

    /**
     * Método que adjunta el fragmento en la actividad
     * @param context contexto de la actividad
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnSelectedMembersResearchGroupListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnSelectedMembersResearchGroupListener");
            }
        }
    }

    /**
     * Método utilizado para agregar investigadores
     * @param researcher investigador a agregar
     */
    public void addResearcher(Researcher researcher){
        researchers.add(researcher);
        adapterMembersResearchGroup.notifyDataSetChanged();
    }


}
