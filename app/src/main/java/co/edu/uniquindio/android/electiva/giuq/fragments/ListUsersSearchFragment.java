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
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterRecyclerView;
import co.edu.uniquindio.android.electiva.giuq.vo.User;

/**
 * Fragmento utilizado para mostrar usuarios según una búsqueda realizada por el usuario
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class ListUsersSearchFragment extends Fragment {

    /**
     * Atributo que representa una llave String que representa al fragmento
     */
    public static final String LIST_USERS="LIST_USERS";

    /**
     * Atributo que representa el botón para regresar al fragmento buscar
     */
    @BindView(R.id.buttonBackSearch)
    protected Button buttonBackSearch;

    /**
     * Atributo que representa la lista de grupos de investigación de la aplicación
     */
    @BindView(R.id.recyclerViewSearch)
    protected RecyclerView recyclerViewSearch;

    /**
     * Atributo que representa la lista de usuarios
     */
    private ArrayList<User> users;

    /**
     * Atributo que representa el adaptador de la lista de usuarios
     */
    private AdapterRecyclerView adapterListUsers;

    /**
     * Atributo que representa el oyente del fragmento
     */
    private OnSelectedListUsersSearchListener listener;

    public static final String ARRAYLIST_USERS="arraylist_users";
    /**
     * Atributo que representa el tipo de díálogo a mostrar
     */
    public ListUsersSearchFragment() {
    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @param users lista de usuarios
     * @return instancia
     */
    public static ListUsersSearchFragment newInstance (ArrayList<User> users){
        ListUsersSearchFragment listUsersSearchFragment = new ListUsersSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARRAYLIST_USERS,users);
        listUsersSearchFragment.setArguments(bundle);
        return listUsersSearchFragment;
    }

    /**
     * Método encargado de crear el fragmento
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.users = (getArguments() != null) ? getArguments().<User>getParcelableArrayList(ARRAYLIST_USERS) : null;
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
       View view =  inflater.inflate(R.layout.fragment_list_users_search, container, false);
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
        adapterListUsers = new AdapterRecyclerView(users,ListUsersSearchFragment.this,LIST_USERS);
        recyclerViewSearch.setAdapter(adapterListUsers);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        buttonBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, ProfileFragment.newInstance(1)).commit();
            }
        });
        listener.sendListUsers(users);
    }

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface OnSelectedListUsersSearchListener {
        void sendListUsers(ArrayList<User>users);
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
                listener = (OnSelectedListUsersSearchListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnSelectedListUsersSearchListener");
            }
        }
    }
}
