package co.edu.uniquindio.android.electiva.giuq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.ControllerApplication;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.fragments.AboutResearchGroupFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AddLineOfResearchFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AddResearcherFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.LineOfResearchFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.MembersResearchGroupFragment;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterPagerFragment;
import co.edu.uniquindio.android.electiva.giuq.util.Validations;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Actividad utilizada para agregar un grupo de investigación
 *
 * @author Francisco Alejandro Hoyos Rojas
 */
public class NewResearchGroupActivity extends AppCompatActivity implements View.OnClickListener,LineOfResearchFragment.OnSelectedLineOfResearchListener,AddLineOfResearchFragment.AddLineOfResearchListener,AboutResearchGroupFragment.AboutResearchGroupListener,MembersResearchGroupFragment.OnSelectedMembersResearchGroupListener {

    /**
     * Atributo que representa el bóton Back Select Rol de la vista
     */
    @BindView(R.id.buttonBackSelectRolNewResearchGroup)
    protected Button buttonBackSelectRol;

    /**
     * Atributo que representa el contenedor de los fragmentos about,academic y research de la vista
     */
    @BindView(R.id.viewPagerResearchGroup)
    protected ViewPager viewPager;

    /**
     * Atributo que representa el contenedor de los títulos de la tabs de la vista
     */
    @BindView(R.id.tabMenuResearchGroup)
    protected TabLayout tabMenu;

    /**
     * Atributo que representa el botón Sign Up
     */
    @BindView(R.id.buttonSignUpResearchGroup)
    protected Button buttonSignUp;

    /**
     * Atributo que representa el nombre de un grupo de investigación
     */
    private String name;

    /**
     * Atributo que representa las siglas de un grupo de investigación
     */
    private String acronym;

    /**
     * Atributo que representa la categoría de un grupo de investigación
     */
    private String category;

    /**
     * Atributo que representa el lider de un grupo de investigación
     */
    private String leader;

    /**
     * Atributo que representa la url del CVLAC de un grupo de investigación
     */
    private String urlCvlac;

    /**
     * Atributo que representa el email de un grupo de investigación
     */
    private String email;

    /**
     * Atributo que representa la contraseña de un grupo de investigación
     */
    private String password;

    /**
     * Atributo que representa los integrantes de un grupo de investigación
     */
    private ArrayList<Researcher> researchers;

    /**
     * Atributo que representa las líneas de investigación de un grupo de investigación
     */
    private ArrayList<LineOfResearch>linesOfResearch;

    /**
     * Atributo que representa si la información básica del grupo de investigación esta completa y correcta
     */
    private boolean validateAbout;

    /**
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_research_group);
        ButterKnife.bind(this);
        buttonBackSelectRol.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        createViewPagerResearchGroup();
    }

    /**
     * Método encargado de crear el ViewPager
     */
    public void createViewPagerResearchGroup() {
        AdapterPagerFragment adapterPagerFragment = new AdapterPagerFragment(getSupportFragmentManager());
        // Se agregan los fragmentos al adaptador
        adapterPagerFragment.addFragment(AboutResearchGroupFragment.newInstance(), getString(R.string.about));
        adapterPagerFragment.addFragment(MembersResearchGroupFragment.newInstance(), getString(R.string.members));
        adapterPagerFragment.addFragment(LineOfResearchFragment.newInstance(), getString(R.string.lines));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // posición de donde viene el tab
            private int fromPosition = 0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                fromPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                if(fromPosition==0){
                    validateAbout= getAboutResearchGroup();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapterPagerFragment);
        tabMenu.setTabMode(TabLayout.MODE_FIXED);
        tabMenu.setTabGravity(TabLayout.GRAVITY_FILL);
        tabMenu.setupWithViewPager(viewPager);

    }

    /**
     * Método que se encarga de ir de la actividad NewResearcherActivity a la actividad SelectRolActivity
     *
     * @param v View que gestiona el evento
     */
    public void goToSelectRolActivity(View v) {
        Intent intent = new Intent(this, SelectRolActivity.class);
        startActivity(intent);
    }

    /**
     * Método utilizado para mostrar el DialogFragment AddLineOfResearch
     * @param className nombre usado para el manejo de la transacción
     */
    public void showLineOfResearchDialog(String className) {
        LineOfResearchFragment fm = (LineOfResearchFragment) getSupportFragmentManager().findFragmentById(R.id.line_of_research_fragment);
        AddLineOfResearchFragment dialogFragment = new AddLineOfResearchFragment();
        dialogFragment.setTargetFragment(fm,2);
        dialogFragment.show(getSupportFragmentManager(), className);
    }

    /**
     * Método encargado de enviar el grupo de investigación al controlador para que sea agregado
     */
    public void sendResearchGroup(){

        if (viewPager.getCurrentItem()==0){
            validateAbout= getAboutResearchGroup();
        }
        if(validateAbout&&!linesOfResearch.isEmpty()){
            ResearchGroup researchGroup = new ResearchGroup(name,email,password,urlCvlac,category,null,linesOfResearch,acronym,null,null);
            ((ControllerApplication)getApplication()).addResearchGroup(researchGroup);
        }
    }

    /**
     * Método utilizado para obtener la información básica de un investigador
     */
    public boolean getAboutResearchGroup(){
        FragmentPagerAdapter fragmentPagerAdapter =(FragmentPagerAdapter) viewPager.getAdapter();
        AboutResearchGroupFragment aboutResearchGroupFragment= (AboutResearchGroupFragment) fragmentPagerAdapter.getItem(0);
        return aboutResearchGroupFragment.sendAboutResearchGroup();
    }

    /**
     * Método encargado de validar el email
     * @param email email ingresado por el usuario
     * @return true si el email es correcto, de lo contrario false
     */
    public boolean validateEmail(String email){
        return Validations.validateEmail(email);
    }

    /**
     * Método encargado de validar la url del CVLAC
     * @param urlCvlac url del CVLAC ingresada por el usuario
     * @return true si la url es correcta, de lo contrario false
     */
    public boolean validateUrlCvlac(String urlCvlac){
        return Validations.validateUrl(urlCvlac);
    }

    /**
     * Método encargado de validar si la contraseña es correcta
     * @param password contraseña ingresada por el usuario
     * @return true si la contraseña es correcta, de lo contrario false
     */
    public boolean validatePassword(String password){
        return Validations.validatePassword(password);
    }

    /**
     * Método encargado de validar campos en general
     * @param field campo a validar
     * @return true si el campo es correcto, de lo contrario false
     */
    public boolean validateFields(String field){

        return Validations.validateFields(field);
    }

    /**
     * Método utilizado para mostrar el DialogFragment AddDegreeTitle
     * @param className nombre usado para el manejo de la transacción
     */
    public void showAddResearcherDialog(String className) {
        MembersResearchGroupFragment fm = (MembersResearchGroupFragment) getSupportFragmentManager().findFragmentById(R.id.members_research_group_fragment);
        AddResearcherFragment dialogFragment = new AddResearcherFragment();
        dialogFragment.setTargetFragment(fm,0);
        dialogFragment.show(getSupportFragmentManager(), className);
    }

    /**
     * Método encargado de escuchar los eventos del botón Back Select Rol, Add Photo y Sign Up
     *
     * @param v control que ejecuta el evento
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonBackSelectRolNewResearchGroup:{
                goToSelectRolActivity(v);
                break;
            }
            case R.id.buttonSignUpResearchGroup:{
                sendResearchGroup();
                break;
            }
        }
    }

    @Override
    public void onSelectedLineOfResearchListener(int position, ArrayList<LineOfResearch> lineOfResearch) {

    }

    /**
     * Método utilizado para obtener las líneas de investigación de un investigador
     * @param linesOfResearch líneas de investigación de un investigador
     */
    @Override
    public void sendListLineOfResearch(ArrayList<LineOfResearch> linesOfResearch) {
        this.linesOfResearch=linesOfResearch;
    }

    /**
     * Método encargado de obtener la información básica de un grupo de investigación
     * @param name nombre grupo investigación
     * @param acronym siglas grupo investigación
     * @param category categoría grupo investigación
     * @param leader lider grupo investigación
     * @param urlCvlac url CVLAC grupo investigación
     * @param email email grupo investigación
     * @param password contraseña grupo investigación
     */
    @Override
    public void sendAboutResearchGroup(String name, String acronym, String category, String leader, String urlCvlac, String email, String password) {
        this.name=name;
        this.acronym= acronym;
        this.category=category;
        this.leader=leader;
        this.urlCvlac=urlCvlac;
        this.email=email;
        this.password=password;
    }

    /**
     * Método utilizado para agregar las líneas de investigación
     * @param lineOfResearch línea de investigación a ser agregada
     */
    @Override
    public void sendLineOfResearch(String lineOfResearch, Boolean status) {
        FragmentPagerAdapter fragmentPagerAdapter =(FragmentPagerAdapter) viewPager.getAdapter();
        LineOfResearchFragment lineOfResearchFragment = (LineOfResearchFragment) fragmentPagerAdapter.getItem(2);
        LineOfResearch lineOfResearchTem = new LineOfResearch(lineOfResearch,status);
        lineOfResearchFragment.addLineOfResearch(lineOfResearchTem);
    }

    @Override
    public void onSelectedMembersResearchGroupListener(int position, ArrayList<Researcher> researchers) {

    }


    /**
     * Método utilizado para obtener los investigadores de un grupo de investigación
     * @param researchers investigador de un grupo de investigación
     */
    @Override
    public void sendListResearchers(ArrayList<Researcher> researchers) {
       this.researchers=researchers;
    }


}
