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
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.ControllerApplication;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.fragments.AboutResearcherFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AcademicTitleFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AddAcademicTitleFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AddLineOfResearchFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.GeneralDialogFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.LineOfResearchFragment;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterPagerFragment;
import co.edu.uniquindio.android.electiva.giuq.util.Validations;
import co.edu.uniquindio.android.electiva.giuq.vo.AcademicTitle;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Actividad utilizada para agregar un investigador
 *
 * @author Francisco Alejandro Hoyos Rojas
 */
public class NewResearcherActivity extends AppCompatActivity implements View.OnClickListener,LineOfResearchFragment.OnSelectedLineOfResearchListener,AcademicTitleFragment.OnSelectedAcademicTitleListener,AddAcademicTitleFragment.AddAcademicTitleListener,AddLineOfResearchFragment.AddLineOfResearchListener,AboutResearcherFragment.AboutResearcherListener{

    /**
     * Atributo que representa el bóton Back Select Rol de la vista
     */
    @BindView(R.id.buttonBackSelectRolNewResearcher)
    protected Button buttonBackSelectRol;

    /**
     * Atributo que representa el contenedor de los fragmentos about,academic y research de la vista
     */
    @BindView(R.id.viewPagerResearcher)
    protected ViewPager viewPager;

    /**
     * Atributo que representa el contenedor de los títulos de la tabs de la vista
     */
    @BindView(R.id.tabMenuResearcher)
    protected TabLayout tabMenu;

    /**
     * Atributo que representa el botón Sign Up
     */
    @BindView(R.id.buttonSignUpResearcher)
    protected Button buttonSignUp;

    /**
     * Atributo que representa el nombre de un investigador
     */
    private String name;
    /**
     * Atributo que representa la nacionalidad de un investigador
     */
    private int nationality;
    /**
     * Atributo que representa la categoría de un investigador
     */
    private int category;

    /**
     * Atributo que representa el grupo de investigación de un investigador
     */
    private String researchGroup;

    /**
     * Atributo que representa la url del CVLAC de un investigador
     */
    private String urlCvlac;

    /**
     * Atributo que representa la contraseña de un investigador
     */
    private String password;

    /**
     * Atributo que representa el email de un investigador
     */
    private String email;

    /**
     * Atributo que representa el género de un investigador
     */
    private boolean genre;

    /**
     * Atributo que representa las líneas de investigación de un investigador
     */
    private ArrayList<LineOfResearch>linesOfResearch;

    /**
     * Atributo que representa los títulos académicos de un investigador
     */
    private ArrayList<AcademicTitle>academicTitles;

    /**
     * Atributo que representa si la información básica del investigador esta completa y correcta
     */
    private boolean validateAbout;

    private int positionItem;
    /**
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_researcher);
        ButterKnife.bind(this);
        buttonBackSelectRol.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        createViewPagerResearcher();

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
     * Método encargado de crear el ViewPager
     */
    public void createViewPagerResearcher(){
        AdapterPagerFragment adapterPagerFragment = new AdapterPagerFragment(getSupportFragmentManager());
        // Se agregan los fragmentos al adaptador
        adapterPagerFragment.addFragment(AboutResearcherFragment.newInstance(),getString(R.string.about));
        adapterPagerFragment.addFragment(AcademicTitleFragment.newInstance(new ArrayList<AcademicTitle>()),getString(R.string.academic));
        adapterPagerFragment.addFragment(LineOfResearchFragment.newInstance(new ArrayList<LineOfResearch>()),getString(R.string.lines));
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
                   validateAbout= getAboutResearcher();
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
     * Método utilizado para mostrar el DialogFragment AddDegreeTitle
     * @param className nombre usado para el manejo de la transacción
     */
    public void showAddAcademicTitleDialog(String className) {
        AcademicTitleFragment fm = (AcademicTitleFragment) getSupportFragmentManager().findFragmentById(R.id.academic_title_fragment);
        AddAcademicTitleFragment dialogFragment = new AddAcademicTitleFragment();
        dialogFragment.setTargetFragment(fm,0);
        dialogFragment.show(getSupportFragmentManager(), className);
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
     * Método utilizado para obtener la información básica de un investigador
     */
    public boolean getAboutResearcher(){
        FragmentPagerAdapter fragmentPagerAdapter =(FragmentPagerAdapter) viewPager.getAdapter();
        AboutResearcherFragment aboutResearcherFragment= (AboutResearcherFragment) fragmentPagerAdapter.getItem(0);
        return aboutResearcherFragment.sendAboutResearcher();
    }

    /**
     * Método encargado de enviar el investigador al controlador para que sea agregado
     */
    public void sendResearcher(){

       GeneralDialogFragment generalDialogFragment;
        if (viewPager.getCurrentItem()==0){
          validateAbout= getAboutResearcher();
        }
            if(!academicTitles.isEmpty()&&!linesOfResearch.isEmpty()) {
                if(validateAbout){
                    if(validateEmailDuplicate(email)){
                        generalDialogFragment= GeneralDialogFragment.newInstance(getResources().getString(R.string.invalid_registration),getResources().getString(R.string.error_email_duplicate),"ERROR");
                        generalDialogFragment.show(getSupportFragmentManager(),"");}
                    else {
                        generalDialogFragment = GeneralDialogFragment.newInstance(getResources().getString(R.string.successful_registration), getResources().getString(R.string.successful_registration_info), "DONE");
                        Researcher researcher = new Researcher(name, email, password, urlCvlac, category, null, linesOfResearch,false, nationality, researchGroup, academicTitles, genre);
                        ((ControllerApplication) getApplication()).addResearcher(researcher);
                        generalDialogFragment.show(getSupportFragmentManager(), "");
                    }
                }
            } else{
                generalDialogFragment= GeneralDialogFragment.newInstance(getResources().getString(R.string.invalid_registration),getResources().getString(R.string.error_line_or_title),"ERROR");
                generalDialogFragment.show(getSupportFragmentManager(),"");
            }

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
     * Método encargado de verficar que no exista un email duplicado en la base de datos
     * @param email email a verificar
     * @return true si ya existe el email de lo contrario false
     */
    public boolean validateEmailDuplicate(String email){
        return ((ControllerApplication)getApplication()).searchEmail(email);
    }

    /**
     * Método encargado de escuchar los eventos del botón Back Select Rol, Add Photo y Sign Up
     *
     * @param v control que ejecuta el evento
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.buttonBackSelectRolNewResearcher:{
                goToSelectRolActivity(v);
                break;
            }
            case R.id.buttonSignUpResearcher:{
                boolean validateConnectionTem = validateConnection();
                if (validateConnectionTem) {
                    sendResearcher();
                }else{
                    goToConnectionErrorDialog();
                }
                break;
            }
        }
    }

    /**
     * Método encargado de verificar si hay conexión a internet o no
     * @return true si hay conexión de lo contrario false
     */
    public boolean validateConnection(){
        return Validations.isOnline(getApplicationContext());
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
     * Método utilizado para obtener los títulos académicos de un investigador
     * @param academicTitles títulos académicos de un investigador
     */
    @Override
    public void sendListAcademicTitles(ArrayList<AcademicTitle> academicTitles) {
        this.academicTitles=academicTitles;
    }

    /**
     * Método utilizado para agregar las líneas de investigación
     * @param lineOfResearch línea de investigación a ser agregada
     */
    @Override
    public void sendLineOfResearch(String lineOfResearch,Boolean status) {
        FragmentPagerAdapter fragmentPagerAdapter =(FragmentPagerAdapter) viewPager.getAdapter();
        LineOfResearchFragment lineOfResearchFragment = (LineOfResearchFragment) fragmentPagerAdapter.getItem(2);
        LineOfResearch lineOfResearchTem = new LineOfResearch(lineOfResearch,status);
        lineOfResearchFragment.addLineOfResearch(lineOfResearchTem);
    }

    /**
     * Método encargado de obtener la información básica de un investigador
     * @param name nombre investigador
     * @param genre género investigador
     * @param nationality nacionalidad investigador
     * @param category categoría investigador
     * @param researchGroup grupo de investigación investigador
     * @param urlCvlac url CVLAC investigador
     * @param email email investigador
     * @param password contraseña investigador
     */
    @Override
    public void sendAboutResearcher(String name, boolean genre, int nationality, int category, String researchGroup, String urlCvlac, String email, String password) {
        this.name=name;
        this.genre= genre;
        this.nationality=nationality;
        this.category=category;
        this.researchGroup=researchGroup;
        this.urlCvlac=urlCvlac;
        this.email=email;
        this.password=password;

    }

    /**
     *  Método utilizado para agregar los títulos académicos
     * @param academicTitle título académico
     * @param institution institución donde se cursaron los estudios
     * @param graduationDate fecha de graduación
     */
    @Override
    public void sendAcademicTitle(String academicTitle,String institution,Date graduationDate) {
        FragmentPagerAdapter fragmentPagerAdapter =(FragmentPagerAdapter) viewPager.getAdapter();
        AcademicTitleFragment academicTitleFragment = (AcademicTitleFragment) fragmentPagerAdapter.getItem(1);
        AcademicTitle academicTitleTem = new AcademicTitle(academicTitle,institution,graduationDate);
        academicTitleFragment.addAcademicTitle(academicTitleTem);
    }

    /**
     * Método que recibe la posición del item seleccionado
     * @param position posición del item seleccionado
     */
    public void onItemPosition(int position) {
        positionItem = position;
    }

    /**
     * Método que permite obtener el valor del atributo positionItem
     *
     * @return El valor del atributo positionItem
     */
    public int getPositionItem() {
        return positionItem;
    }

    /**
     * Método que permite asignar un valor al atributo positionItem
     *
     * @param positionItem Valor a ser asignado al atributo positionItem
     */
    public void setPositionItem(int positionItem) {
        this.positionItem = positionItem;
    }

    /**
     * Método encargado de mostrar el diálogo cuando no hay conexión a internet
     */
    public void goToConnectionErrorDialog(){
        GeneralDialogFragment generalDialogFragment= GeneralDialogFragment.newInstance(getResources().getString(R.string.connection_error),getResources().getString(R.string.restart_application),"ERROR");
        generalDialogFragment.show(getSupportFragmentManager(),"");
    }
}
