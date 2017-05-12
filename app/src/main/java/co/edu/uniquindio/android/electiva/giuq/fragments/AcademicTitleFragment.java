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
import co.edu.uniquindio.android.electiva.giuq.vo.AcademicTitle;

/**
 * Fragmento utilizado para obtener la formación académica de un investigador
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class AcademicTitleFragment extends Fragment implements AdapterRecyclerView.OnClickAdapterRecyclerView {

    /**
     * Atributo que representa una llave String
     */
    public static final String ACADEMIC_TITLE="ACADEMIC_TITLE";

    /**
     * Atributo que representa la lista de títulos académicos de un investigador
     */
    @BindView(R.id.recyclerViewAcademicTitle)
    protected RecyclerView recyclerViewAcademicTitle;

    /**
     * Atributo que representa la imagen para títlos académicos
     */
    @BindView(R.id.imageViewAddAcademicTitle)
    ImageView imageViewAddAcademicTitle;

    /**
     * Atributo que representa los títulos de un investigador
     */
    private ArrayList<AcademicTitle>academicTitles;

    private AdapterRecyclerView adapterAcademicTitle;
    /**
     * Atributo que representa el oyente del diálogo
     */
    private OnSelectedAcademicTitleListener listener;


    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public AcademicTitleFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static AcademicTitleFragment newInstance() {
        AcademicTitleFragment fragment = new AcademicTitleFragment();
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
        academicTitles = new ArrayList<>();
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
        View view =inflater.inflate(R.layout.fragment_academic_title, container, false);
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
        imageViewAddAcademicTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewResearcherActivity) getActivity()).showAddAcademicTitleDialog(AcademicTitleFragment.class.getName());
            }
        });
        adapterAcademicTitle = new AdapterRecyclerView(academicTitles,this,ACADEMIC_TITLE);
        recyclerViewAcademicTitle.setAdapter(adapterAcademicTitle);
        recyclerViewAcademicTitle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * Método que permite notificar a la actividad, por medio de la interface del callback,
     * que un elemento ha sido seleccionado
     * @param pos posición de la línea de investigación seleccionada
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onSelectedAcademicTitleListener(pos,academicTitles);
    }


    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface OnSelectedAcademicTitleListener {

        void onSelectedAcademicTitleListener(int position, ArrayList<AcademicTitle> academicTitles);
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
                listener = (OnSelectedAcademicTitleListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnSelectedLAcademicTitleListener");
            }
        }
    }

    /**
     * Método utilizado para agregar los títulos de un investigador
     * @param academicTitle título académico
     */
    public void addAcademicTitle(AcademicTitle academicTitle){
        academicTitles.add(academicTitle);
        adapterAcademicTitle.notifyDataSetChanged();
    }

}
