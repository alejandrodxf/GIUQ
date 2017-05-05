package co.edu.uniquindio.android.electiva.giuq.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.fragments.ForgotPasswordFragment;

/**
 * Clase utilizada para el ingreso de los usuarios a la aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * Atributo que representa el campo de texto email
     */
    @BindView(R.id.editTextEmailLogin)
    EditText editTextEmail;

    /**
     * Atributo que representa el campo de texto password
     */
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    /**
     * Atributo que representa el botón Sign Up
     */
    @BindView(R.id.buttonSignUp)
    Button buttonSignUp;

    /**
     * Atributo que representa el botón Sign In
     */
    @BindView(R.id.buttonSignIn)
    Button buttonSignIn;

    /**
     * Atributo que representa el botón Forgot Password
     */
    @BindView(R.id.buttonForgotPassword)
    Button buttonForgotPassword;

    /**
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        buttonForgotPassword.setOnClickListener(this);

    }

    /**
     * Método encargado de escuchar los eventos del botón Sign Up, Sign In y Forgot Password
     *
     * @param v control que ejecuta el evento
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonForgotPassword:{
                showDialogRecoverPassword();
            }
        }

    }

    /**
     * Método encargado de mostrar el diálogo para recuperar la contraseña
     */
    public void showDialogRecoverPassword() {
        FragmentManager fm = getSupportFragmentManager();
        ForgotPasswordFragment dialogFragmentForgotPassword = new ForgotPasswordFragment();
        dialogFragmentForgotPassword.show(fm, "fragment_forgot_password");

    }
}