package co.edu.uniquindio.android.electiva.giuq.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.ProfileActivity;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterPagerFragment;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Fragmento utilizado para mostrar las opciones de navegación a un usuario que se ha logueado
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class ProfileFragment extends Fragment {

    /**
     * Atributo que representa el contenedor de los fragmentos
     */
    @BindView(R.id.viewPagerProfile)
    protected ViewPager viewPager;

    /**
     * Atributo que representa el contenedor de los títulos de la tabs de la vista
     */
    @BindView(R.id.tabMenuProfile)
    protected TabLayout tabMenu;

    /**
     * Atributo que representa una llave string posición
     */
    private static final String POSITION ="position";

    /**
     * Atributo que representa la posición del tab
     */
    private int position;


    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public ProfileFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static ProfileFragment newInstance(int position) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
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
        this.position= (getArguments() != null) ? getArguments().getInt(POSITION) : 0;
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
       View view =inflater.inflate(R.layout.fragment_profile, container, false);
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
        AdapterPagerFragment adapterPagerFragment = new AdapterPagerFragment(getChildFragmentManager());
        Researcher researcher =((ProfileActivity)getActivity()).getResearcher();
        ResearchGroup researchGroup= ((ProfileActivity)getActivity()).getResearchGroup();
        adapterPagerFragment.addFragment(InformationProfileFragment.newInstance(researcher,researchGroup),getString(R.string.about));
        adapterPagerFragment.addFragment(SearchFragment.newInstance(),getString(R.string.search));
        adapterPagerFragment.addFragment(ListByFragment.newInstance(),getString(R.string.lines));
        adapterPagerFragment.setOnlyIcons(true);
        viewPager.setAdapter(adapterPagerFragment);
        viewPager.setCurrentItem(position);
        tabMenu.setTabMode(TabLayout.MODE_FIXED);
        tabMenu.setTabGravity(TabLayout.GRAVITY_FILL);
        tabMenu.setupWithViewPager(viewPager);
        //Se agregan los iconos a los tabs
        tabMenu.getTabAt(0).setCustomView(setIconTab(R.drawable.ic_action_home));
        tabMenu.getTabAt(1).setCustomView(setIconTab(R.drawable.ic_action_search));
        tabMenu.getTabAt(2).setCustomView(setIconTab(R.drawable.ic_action_list));
    }

    /**
     * Método utilizado para poner de fondo la imagen que se quiere agregar al tab
     * @param drawable imagen de fondo
     * @return vista con la imagen de fondo
     */
    public View setIconTab(int drawable){
        View view = getActivity().getLayoutInflater().inflate(R.layout.tab_icon, null);
        view.findViewById(R.id.icon).setBackgroundResource(drawable);
        return view;
    }

    /**
     * Método utilizado para obtener el investigador que esta usando la aplicación
     * @return investigador
     */
    public Researcher getResearcher(){
        return ((ProfileActivity)getActivity()).getResearcher();
    }

    /**
     * Método utilizado para obtener el grupo de investigación que esta usando la aplicación
     * @return grupo de investigación
     */
    public ResearchGroup getResearchGroup(){
        return  ((ProfileActivity)getActivity()).getResearchGroup();
    }

    /**
     * Método utilizado para obtener resultados de los fragmentos
     * @param requestCode Código de solicitu
     * @param resultCode Un código de resultado que puede ser RESULT_OK si la operación se realizó correctamente o RESULT_CANCELED si el usuario canceló la operación o esta falló por algún motivo.
     * @param data  información del resultado.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
