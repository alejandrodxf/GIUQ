package co.edu.uniquindio.android.electiva.giuq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;

/**
 * Actividad utilizada para seleccionar el tipo de usuario que se va a registrar
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class SelectRolActivity extends AppCompatActivity  implements View.OnClickListener{

    /**
     * Atributo que representa el bóton Back Login de la vista
     */
    @BindView(R.id.buttonBackSignIn)
    Button buttonBackSignIn;

    /**
     * Atributo que representa el imageView researcher de la vista
     */
    @BindView(R.id.imageViewResearcher)
    ImageView imageViewResearcher;

    /**
     * Atributo que representa el imageView researchGroup de la vista
     */
    @BindView(R.id.imageViewResearchGroup)
    ImageView imageViewResearchGroup;

    /**
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rol);
        ButterKnife.bind(this);
        buttonBackSignIn.setOnClickListener(this);
    }

    /**
     * Método encargado de escuchar los eventos del botón Back Sign In, New Researcher
     * y New Research Group
     *
     * @param v control que ejecuta el evento
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonBackSignIn:{
                goToLoginActivity(v);
                break;
            }
        }
    }

    /**
     * Método que se encarga de ir de la actividad SelectRolActivity a la actividad LoginActivity
     *
     * @param v View que gestiona el evento
     */
    public void goToLoginActivity(View v){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
