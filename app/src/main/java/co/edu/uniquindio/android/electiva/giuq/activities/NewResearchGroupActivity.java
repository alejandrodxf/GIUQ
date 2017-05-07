package co.edu.uniquindio.android.electiva.giuq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;

import static co.edu.uniquindio.android.electiva.giuq.R.id.tabMenuResearchGroup;
import static co.edu.uniquindio.android.electiva.giuq.R.id.viewPagerResearchGroup;

public class NewResearchGroupActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Atributo que representa el bóton Back Select Rol de la vista
     */
    @BindView(R.id.buttonBackSelectRolNewResearchGroup)
    Button buttonBackSelectRol;

    /**
     * Atributo que representa el contenedor de los fragmentos about,academic y research de la vista
     */
    @BindView(viewPagerResearchGroup)
    ViewPager viewPager;

    /**
     * Atributo que representa el contenedor de los títulos de la tabs de la vista
     */
    @BindView(tabMenuResearchGroup)
    TabLayout tabMenu;


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
}
