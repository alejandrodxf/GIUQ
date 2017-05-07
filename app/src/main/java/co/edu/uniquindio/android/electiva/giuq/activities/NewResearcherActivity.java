package co.edu.uniquindio.android.electiva.giuq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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
public class NewResearcherActivity extends AppCompatActivity implements View.OnClickListener,LineOfResearchFragment.OnSelectedLineOfResearchListener,AcademicTitleFragment.OnSelectedAcademicTitleListener{


    /**
     * Atributo que representa el bóton Back Select Rol de la vista
     */
    @BindView(R.id.buttonBackSelectRolNewResearcher)
    Button buttonBackSelectRol;

    /**
     * Atributo que representa el contenedor de los fragmentos about,academic y research de la vista
     */
    @BindView(viewPagerResearcher)
    ViewPager viewPager;

    /**
     * Atributo que representa el contenedor de los títulos de la tabs de la vista
     */
    @BindView(tabMenuResearcher)
    TabLayout tabMenu;


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

    @Override
    public void onSelectedLineOfResearchListener(int position, ArrayList<LineOfResearch> lineOfResearch) {

    }

    @Override
    public void onSelectedAcademicTitleListener(int position, ArrayList<AcademicTitle> academicTitles) {

    }
}
