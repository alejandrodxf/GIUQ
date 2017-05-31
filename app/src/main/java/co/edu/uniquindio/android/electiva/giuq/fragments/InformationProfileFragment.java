package co.edu.uniquindio.android.electiva.giuq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterPagerFragment;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Fragmento utilizado para mostrar la información de un investigador o de un grupo de investigación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class InformationProfileFragment extends Fragment {

    /**
     * Atributo que representa el contenedor de los fragmentos about,academic y research de la vista
     */
    @BindView(R.id.viewPagerFullProfile)
    protected ViewPager viewPager;

    /**
     * Atributo que representa el contenedor de los títulos de la tabs de la vista
     */
    @BindView(R.id.tabMenuFullProfile)
    protected TabLayout tabMenu;

    /**
     * Atributo que representa el botón para regresar al perfil del usuario
     */
    @BindView(R.id.buttonBackProfile)
    protected Button buttonBackProfile;

    /**
     * Atributo que representa una llave string posición de un investigador
     */
    private static final String RESEARCHER ="researcher";

    /**
     * Atributo que representa una llave string posición de un grupo de investigación
     */
    private static final String RESEARCHGROUP ="researchgroup";

    /**
     * Atributo que representa un investigador
     */
    private Researcher researcher;

    /**
     * Atributo que representa un grupo de investigación
     */
    private ResearchGroup researchGroup;

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public InformationProfileFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @param researcher investigador del cual se va a mostrar la información
     * @param researchGroup grupo de investigación del cual se va a mostrar la información
     * @return instancia
     */
    public static InformationProfileFragment newInstance(Researcher researcher,ResearchGroup researchGroup){
        InformationProfileFragment informationProfileFragment = new InformationProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESEARCHER,researcher);
        bundle.putParcelable(RESEARCHGROUP,researchGroup);
        informationProfileFragment.setArguments(bundle);
        return informationProfileFragment;
    }

    /**
     * Método encargado de crear el fragmento
     *
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.researcher= (getArguments() != null) ? getArguments().<Researcher>getParcelable(RESEARCHER) : null;
        this.researchGroup= (getArguments() != null) ? getArguments().<ResearchGroup>getParcelable(RESEARCHGROUP) : null;
    }

    /**
     * Método encargado de cargar la vista asociada al fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState información a ser recepcionada
     * @return la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    /**
     * Método llamado después de que se ha completado el onCreate
     * Se utiliza para inicializaciones finales y para modificar elementos de la interfaz
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AdapterPagerFragment adapterPagerFragment = new AdapterPagerFragment(getChildFragmentManager());
        String name,email,category,genre,nationality,acronym,leader,urlCvlac;
        if(researcher!=null) {
            name= researcher.getName().toString();
            email= researcher.getEmail().toString();
            nationality = getResources().getStringArray(R.array.nationality_array)[researcher.getNationality()];
            category =  getResources().getStringArray(R.array.category_researcher_array)[researcher.getCategory()];
            String researchGroupName = researcher.getResearchGroup().toString();
            if(researcher.isGenre()){
                genre=getResources().getString(R.string.male);
            }else{
                genre=getResources().getString(R.string.female);
            }
            urlCvlac = researcher.getUrlCVLAC();
            adapterPagerFragment.addFragment(BasicInformationProfileFragment.newInstance(name,genre+" - "+nationality,researchGroupName+" - "+category,email,urlCvlac), getString(R.string.about));
            adapterPagerFragment.addFragment(AcademicTitleFragment.newInstance(researcher.getAcademicTitles()), getString(R.string.academic));
            adapterPagerFragment.addFragment(LineOfResearchFragment.newInstance(researcher.getLinesOfResearch()), getString(R.string.lines));
        }else{
             name = researchGroup.getName();
             email = researchGroup.getEmail();
             acronym = researchGroup.getAcronym();
             category = getResources().getStringArray(R.array.category_research_group_array)[researchGroup.getCategory()];
             leader = researchGroup.getLeader().getName();
             urlCvlac=researchGroup.getUrlCVLAC();
             adapterPagerFragment.addFragment(BasicInformationProfileFragment.newInstance(name,acronym+" - "+category,leader,email,urlCvlac), getString(R.string.about));
             adapterPagerFragment.addFragment(MembersResearchGroupFragment.newInstance(researchGroup.getResearchers()),getResources().getString(R.string.members));
             adapterPagerFragment.addFragment(LineOfResearchFragment.newInstance(researchGroup.getLinesOfResearch()), getString(R.string.lines));
        }
        viewPager.setAdapter(adapterPagerFragment);
        tabMenu.setTabMode(TabLayout.MODE_FIXED);
        tabMenu.setTabGravity(TabLayout.GRAVITY_FILL);
        tabMenu.setupWithViewPager(viewPager);
        buttonBackProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, ProfileFragment.newInstance(0)).commit();
            }
        });
    }
}
