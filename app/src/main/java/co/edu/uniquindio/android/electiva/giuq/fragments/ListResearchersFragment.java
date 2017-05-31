package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.ProfileActivity;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterRecyclerView;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Fragmento utilizado para mostrar la lista de investigadores activos de la aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class ListResearchersFragment extends Fragment {

    /**
     * Atributo que representa una llave String que representa al fragmento
     */
    public static final String LIST_OF_RESEARCHERS="LIST_OF_RESEARCHERS";

    /**
     * Atributo que representa la lista de investigadores de la aplicación
     */
    @BindView(R.id.recyclerViewListResearchers)
    protected RecyclerView recyclerViewListResearchers;

    /**
     * Atributo que representa el botón para regresar al fragmento listar por
     */
    @BindView(R.id.buttonBackListByResearchers)
    protected Button buttonBackListByResearchers;

    /**
     * Atributo que representa la lista de investigadores
     */
    private ArrayList<Researcher> researchers;

    /**
     * Atributo que representa el adaptador de la lista de investigadores
     */
    private AdapterRecyclerView adapterListResearchers;

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public ListResearchersFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static ListResearchersFragment newInstance() {
        ListResearchersFragment fragment = new ListResearchersFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_researchers, container, false);
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
        researchers=((ProfileActivity)getActivity()).getResearchers();
        adapterListResearchers = new AdapterRecyclerView(researchers,this,LIST_OF_RESEARCHERS);
        recyclerViewListResearchers.setAdapter(adapterListResearchers);
        recyclerViewListResearchers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        buttonBackListByResearchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, ProfileFragment.newInstance(2)).commit();
            }
        });
    }

}
