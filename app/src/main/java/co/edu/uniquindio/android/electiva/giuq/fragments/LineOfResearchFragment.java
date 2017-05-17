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
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearcherActivity;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterRecyclerView;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;

/**
 * Fragmento utilizado para obtener las líneas de investigación de un investigador
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class LineOfResearchFragment extends Fragment implements AdapterRecyclerView.OnClickAdapterRecyclerView {

    public static final String LINE_OF_RESEARCH="LINE_OF_RESEARCH";

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
     * Atributo que representa las lineas de investigación de un investigador
     */
    private ArrayList<LineOfResearch> linesOfResearch;
    private AdapterRecyclerView adapterLineOfResearch;
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
    public static LineOfResearchFragment newInstance() {
        LineOfResearchFragment fragment = new LineOfResearchFragment();
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
        linesOfResearch = new ArrayList<>();
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
        imageViewAddLineOfResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewResearcherActivity) getActivity()).showLineOfResearchDialog(LineOfResearchFragment.class.getName());

            }
        });
        adapterLineOfResearch = new AdapterRecyclerView(linesOfResearch,this,LINE_OF_RESEARCH);
        recyclerViewLineOfResearch.setAdapter(adapterLineOfResearch);
        recyclerViewLineOfResearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listener.sendListLineOfResearch(linesOfResearch);
    }

    /**
     * Método que permite notificar a la actividad, por medio de la interface del callback,
     * que un elemento ha sido seleccionado
     * @param pos posición de la línea de investigación seleccionada
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onSelectedLineOfResearchListener(pos,linesOfResearch);
    }

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface OnSelectedLineOfResearchListener {

        void onSelectedLineOfResearchListener(int position, ArrayList<LineOfResearch> lineOfResearch);
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
     * @param lineOfResearch título académico
     */
    public void addLineOfResearch(LineOfResearch lineOfResearch){
        linesOfResearch.add(lineOfResearch);
        adapterLineOfResearch.notifyDataSetChanged();
    }


}


