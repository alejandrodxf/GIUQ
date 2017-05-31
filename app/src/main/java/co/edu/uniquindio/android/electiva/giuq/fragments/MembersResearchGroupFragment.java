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
import co.edu.uniquindio.android.electiva.giuq.activities.ProfileActivity;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterRecyclerView;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Fragmento utilizado para obtener los integrantes de un grupo de investigación
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class MembersResearchGroupFragment extends Fragment{

    /**
     * Atributo que representa una llave String que representa al fragmento
     */
    public static final String MEMBERS_RESEARCH_GROUP="MEMBERS_RESEARCH_GROUP";

    /**
     * Atributo que representa una llave String que representa la lista de títulos del fragmento
     */
    public static final String LIST_MEMBERS_RESEARCH_GROUP="LIST_MEMBERS_RESEARCH_GROUP";

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
     * Atributo que representa la imagen para remover investigadores
     */
    @BindView(R.id.imageViewRemoveMembers)
    protected   ImageView imageViewRemoveMembers;

    /**
     * Atributo que representa la lista de investigadores
     */
    private ArrayList<Researcher> researchers;

    /**
     * Atributo que representa el adaptador de la lista de investigadores
     */
    private AdapterRecyclerView adapterMembersResearchGroup;

    /**
     * Atributo que representa el oyente del fragmento
     */
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
    public static MembersResearchGroupFragment newInstance(ArrayList<Researcher> researchers) {
        MembersResearchGroupFragment fragment = new MembersResearchGroupFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LIST_MEMBERS_RESEARCH_GROUP,researchers);
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
        this.researchers= (getArguments() != null) ? getArguments().<Researcher>getParcelableArrayList(LIST_MEMBERS_RESEARCH_GROUP) : null;
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
        if(getActivity()instanceof ProfileActivity){
            imageViewAddMembers.setVisibility(View.INVISIBLE);
            imageViewRemoveMembers.setVisibility(View.INVISIBLE);
        }else {
            imageViewAddMembers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((NewResearchGroupActivity) getActivity()).showAddResearcherDialog(MembersResearchGroupFragment.class.getName());
                }
            });
            imageViewRemoveMembers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeResearcher(((NewResearchGroupActivity) getActivity()).getPositionItem());
                    ((NewResearchGroupActivity) getActivity()).setPositionItem(-1);
                }
            });
            listener.sendListResearchers(researchers);
        }
        adapterMembersResearchGroup = new AdapterRecyclerView(researchers, this, MEMBERS_RESEARCH_GROUP);
        recyclerViewMembers.setAdapter(adapterMembersResearchGroup);
        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface OnSelectedMembersResearchGroupListener {
        void sendListResearchers(ArrayList<Researcher>researchers);
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
        if(!researchers.contains(researcher)) {
            researchers.add(researcher);
            adapterMembersResearchGroup.notifyDataSetChanged();
        }
    }

    /**
     * Método utilizado para remover investigadores
     * @param position posición del investigador a eliminar
     */
    public void removeResearcher(int position){
        if(!researchers.isEmpty()&&position<researchers.size()&&position>-1) {
            researchers.remove(position);
            adapterMembersResearchGroup.notifyDataSetChanged();
        }
    }


}
