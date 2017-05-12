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
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.fragments.AboutResearcherFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AcademicTitleFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AddAcademicTitleFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.AddLineOfResearchFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.LineOfResearchFragment;
import co.edu.uniquindio.android.electiva.giuq.util.AdapterPagerFragment;
import co.edu.uniquindio.android.electiva.giuq.vo.AcademicTitle;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;

import static co.edu.uniquindio.android.electiva.giuq.R.id.tabMenuResearcher;
import static co.edu.uniquindio.android.electiva.giuq.R.id.viewPagerResearcher;

/**
 * Actividad utilizada para agregar un investigador
 *
 * @author Francisco Alejandro Hoyos Rojas
 */
public class NewResearcherActivity extends AppCompatActivity implements View.OnClickListener,LineOfResearchFragment.OnSelectedLineOfResearchListener,AcademicTitleFragment.OnSelectedAcademicTitleListener,AddAcademicTitleFragment.AcademicTitleListener,AddLineOfResearchFragment.LineOfResearchListener{

    /**
     * Atributo que representa el bóton Back Select Rol de la vista
     */
    @BindView(R.id.buttonBackSelectRolNewResearcher)
    protected Button buttonBackSelectRol;

    /**
     * Atributo que representa el contenedor de los fragmentos about,academic y research de la vista
     */
    @BindView(viewPagerResearcher)
    protected ViewPager viewPager;

    /**
     * Atributo que representa el contenedor de los títulos de la tabs de la vista
     */
    @BindView(tabMenuResearcher)
    protected TabLayout tabMenu;


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
        createViewPagerResearcher();
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
        }
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
        adapterPagerFragment.addFragment(AcademicTitleFragment.newInstance(),getString(R.string.academic));
        adapterPagerFragment.addFragment(LineOfResearchFragment.newInstance(),getString(R.string.lines));
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


    @Override
    public void onSelectedLineOfResearchListener(int position, ArrayList<LineOfResearch> lineOfResearch) {

    }

    @Override
    public void onSelectedAcademicTitleListener(int position, ArrayList<AcademicTitle> academicTitles) {

    }

    /**
     * Método utilizado para agregar los títulos académicos
     * @param academicTitle título académico a agregar
     */
    @Override
    public void sendAcademicTitle(AcademicTitle academicTitle) {
        FragmentPagerAdapter fragmentPagerAdapter =(FragmentPagerAdapter) viewPager.getAdapter();
        AcademicTitleFragment academicTitleFragment = (AcademicTitleFragment) fragmentPagerAdapter.getItem(1);
        academicTitleFragment.addAcademicTitle(academicTitle);
    }

    /**
     * Método utilizado para agregar las líneas de investigación
     * @param lineOfResearch línea de investigación a ser agregada
     */
    @Override
    public void sendLineOfResearch(LineOfResearch lineOfResearch) {
        FragmentPagerAdapter fragmentPagerAdapter =(FragmentPagerAdapter) viewPager.getAdapter();
        LineOfResearchFragment lineOfResearchFragment = (LineOfResearchFragment) fragmentPagerAdapter.getItem(2);
        lineOfResearchFragment.addLineOfResearch(lineOfResearch);
    }
}
