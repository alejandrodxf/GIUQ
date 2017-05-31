package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.ProfileActivity;
import co.edu.uniquindio.android.electiva.giuq.vo.User;

/**
 * Fragmento utilizado para realizar búsquedas en la aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class SearchFragment extends Fragment {

    /**
     * Atributo que reprensenta el campo línea de investigación
     */
    @BindView(R.id.editTextLineOfResearchSearch)
    protected EditText editTextLineOfResearch;

    /**
     * Atributo que reprensenta el campo nombre grupo de investigación
     */
    @BindView(R.id.editTextNameResearchGroupSearch)
    protected EditText editTextNameResearchGroup;

    /**
     * Atributo que reprensenta el campo nombre investigador
     */
    @BindView(R.id.editTextNameResearcherSearch)
    protected EditText editTextNameResearcher;

    /**
     * Atributo que reprensenta el botón buscar
     */
    @BindView(R.id.buttonSearch)
    protected Button buttonSearch;


    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public SearchFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static SearchFragment newInstance (){
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    /**
     * Método encargado de crear el fragmento
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View view=  inflater.inflate(R.layout.fragment_search, container, false);
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
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList <User> users = ((ProfileActivity)getActivity()).search(editTextNameResearcher.getText().toString(),editTextNameResearchGroup.getText().toString(),editTextLineOfResearch.getText().toString());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, ListUsersSearchFragment.newInstance(users)).commit();
            }
        });

    }
}
