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
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearcherActivity;
import co.edu.uniquindio.android.electiva.giuq.activities.ProfileActivity;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterRecyclerView;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;

/**
 * Fragmento utilizado para obtener las líneas de investigación de un investigador
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class LineOfResearchFragment extends Fragment  {

    /**
     * Atributo que representa una llave String que representa al fragmento
     */
    public static final String LINE_OF_RESEARCH="LINE_OF_RESEARCH";

    /**
     * Atributo que representa una llave String que representa la lista de líneas de investigación
     */
    public static final String LIST_LINE_OF_RESEARCH="LIST_LINE_OF_RESEARCH";

    /**
     * Atributo que representa la lista de líneas de investigación de un investigador
     */
    @BindView(R.id.recyclerViewLineOfResearch)
    protected RecyclerView recyclerViewLineOfResearch;

    /**
     * Atributo que representa la imagen para agregar líneas de investigación
     */
    @BindView(R.id.imageViewAddLineOfResearch)
    protected ImageView imageViewAddLineOfResearch;

    /**
     * Atributo que representa la imagen para remover líneas de investigación
     */
    @BindView(R.id.imageViewRemoveLineOfResearch)
    protected   ImageView imageViewRemoveLineOfResearch;

    /**
     * Atributo que representa las lineas de investigación de un investigador
     */
    private ArrayList<LineOfResearch> linesOfResearch;

    /**
     * Atributo que representa el adaptador de la lista de títulos académicos
     */
    private AdapterRecyclerView adapterLineOfResearch;

    /**
     * Atributo que representa el oyente del fragmento
     */
    private OnSelectedLineOfResearchListener listener;


    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public LineOfResearchFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static LineOfResearchFragment newInstance(ArrayList<LineOfResearch>linesOfResearch) {
        LineOfResearchFragment fragment = new LineOfResearchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LIST_LINE_OF_RESEARCH,linesOfResearch);
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
        this.linesOfResearch= (getArguments() != null) ? getArguments().<LineOfResearch>getParcelableArrayList(LIST_LINE_OF_RESEARCH) : null;
    }

    /**
     * Método encargado de cargar la vista asociada al fragmento
     * @param inflater
     * @param container
     * @param savedInstanceState información a ser recepcionada
     * @return la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_line_of_research, container, false);
        ButterKnife.bind(this, view);
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
            imageViewAddLineOfResearch.setVisibility(View.INVISIBLE);
            imageViewRemoveLineOfResearch.setVisibility(View.INVISIBLE);
            adapterLineOfResearch = new AdapterRecyclerView(linesOfResearch,this,LINE_OF_RESEARCH);
            recyclerViewLineOfResearch.setAdapter(adapterLineOfResearch);
            recyclerViewLineOfResearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }else{
            imageViewAddLineOfResearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(getActivity() instanceof NewResearcherActivity){
                        ((NewResearcherActivity) getActivity()).showLineOfResearchDialog(LineOfResearchFragment.class.getName());
                    }

                    if(getActivity() instanceof NewResearchGroupActivity){
                        ((NewResearchGroupActivity) getActivity()).showLineOfResearchDialog(LineOfResearchFragment.class.getName());
                    }

                }
            });
            imageViewRemoveLineOfResearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity() instanceof NewResearcherActivity){
                        removeLineOfResearch(((NewResearcherActivity)getActivity()).getPositionItem());
                        ((NewResearcherActivity)getActivity()).setPositionItem(-1);
                    }

                    if(getActivity() instanceof NewResearchGroupActivity){
                        removeLineOfResearch(((NewResearchGroupActivity)getActivity()).getPositionItem());
                        ((NewResearchGroupActivity)getActivity()).setPositionItem(-1);
                    }
                }
            });
            adapterLineOfResearch = new AdapterRecyclerView(linesOfResearch,this,LINE_OF_RESEARCH);
            recyclerViewLineOfResearch.setAdapter(adapterLineOfResearch);
            recyclerViewLineOfResearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            listener.sendListLineOfResearch(linesOfResearch);
        }
    }


    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface OnSelectedLineOfResearchListener {
        void sendListLineOfResearch(ArrayList<LineOfResearch>linesOfResearch);
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
                listener = (OnSelectedLineOfResearchListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnSelectedLineOfResearchListener");
            }
        }
    }

    /**
     * Método utilizado para agregar las líneas de investigación
     * @param lineOfResearch línea de investigación a ser agregada
     */
    public void addLineOfResearch(LineOfResearch lineOfResearch){
        linesOfResearch.add(lineOfResearch);
        adapterLineOfResearch.notifyDataSetChanged();
    }

    /**
     * Método utilizado para remover las líneas de investigación
     * @param position posición de la línea de investigación a ser eliminada
     */
    public void removeLineOfResearch(int position){
        if(!linesOfResearch.isEmpty()&&position<linesOfResearch.size()&&position>-1) {
            linesOfResearch.remove(position);
            adapterLineOfResearch.notifyDataSetChanged();
        }
    }


}


