package co.edu.uniquindio.android.electiva.giuq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.ControllerApplication;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.fragments.ForgotPasswordFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.GeneralDialogFragment;
import co.edu.uniquindio.android.electiva.giuq.util.Language;
import co.edu.uniquindio.android.electiva.giuq.util.Validations;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

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
    protected EditText editTextEmail;

    /**
     * Atributo que representa el campo de texto password
     */
    @BindView(R.id.editTextPassword)
    protected EditText editTextPassword;

    /**
     * Atributo que representa el botón Sign Up
     */
    @BindView(R.id.buttonSignUp)
    protected Button buttonSignUp;

    /**
     * Atributo que representa el botón Sign In
     */
    @BindView(R.id.buttonSignIn)
    protected Button buttonSignIn;

    /**
     * Atributo que representa el botón Forgot Password
     */
    @BindView(R.id.buttonForgotPassword)
    protected Button buttonForgotPassword;

    /**
     * Atributo que representa el campo password
     */
    @BindView(R.id.textInputLayoutPassword)
    protected TextInputLayout textInputLayoutPassword;

    /**
     * Atributo que representa una llave parcelable investigador
     */
    public static final String KEY_PARCELABLERESEARCHER="key_parcelable_researcher";
    /**
     * Atributo que representa una llave parcelable grupo de investigación
     */
    public static final String KEY_PARCELABLERESEARCHGROUP="key_parcelable_researchgroup";

    /**
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Language.setLanguage(this,Language.getLanguage(this));
        if(validateConnection()) {
            ((ControllerApplication) getApplication()).loadUsers(this);
        }else{
            goToConnectionErrorDialog();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
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
                break;
            }
            case R.id.buttonSignIn:{
                boolean validateConnectionTem = validateConnection();
                if (!validateConnectionTem) {
                    goToConnectionErrorDialog();
                } else {
                    if(!((ControllerApplication)getApplication()).getLoadDatabase()){
                        ((ControllerApplication) getApplication()).loadUsers(this);
                    }else if(((ControllerApplication)getApplication()).getLoadDatabase()){
                        if (validateEmail(editTextEmail.getText().toString())) {
                            Researcher researcher = ((ControllerApplication) getApplication()).loginResearcher(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                            if(researcher!=null){
                                goToProfileActivity(v, researcher, null);
                            }else{
                                ResearchGroup researchGroup = ((ControllerApplication) getApplication()).loginResearchGroup(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                                if(researchGroup!=null){
                                    goToProfileActivity(v, null, researchGroup);
                                }
                            }
                        }
                    }else{
                        goToConnectionErrorDialog();
                    }
                }
                break;
            }
            case R.id.buttonSignUp:{
                boolean validateConnectionTem = validateConnection();
                if (!validateConnectionTem) {
                    goToConnectionErrorDialog();
                } else {
                    if (!((ControllerApplication) getApplication()).getLoadDatabase()) {
                        ((ControllerApplication) getApplication()).loadUsers(this);
                    } else if (((ControllerApplication) getApplication()).getLoadDatabase()) {
                        goToSelectRolActivity(v);
                    }
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
     * Método encargado de mostrar el diálogo cuando no hay conexión a internet
     */
    public void goToConnectionErrorDialog(){
        GeneralDialogFragment generalDialogFragment= GeneralDialogFragment.newInstance(getResources().getString(R.string.connection_error),getResources().getString(R.string.restart_application),"ERROR");
        generalDialogFragment.show(getSupportFragmentManager(),"");
    }
    /**
     * Método encargado de mostrar el diálogo para recuperar la contraseña
     */
    public void showDialogRecoverPassword() {
        FragmentManager fm = getSupportFragmentManager();
        ForgotPasswordFragment dialogFragmentForgotPassword = new ForgotPasswordFragment();
        dialogFragmentForgotPassword.show(fm, "fragment_forgot_password");

    }

    /**
     * Método encargado de validar si los datos introducidos por el usuario
     * @param email email ingresado por el usuario
     * @return true si es el email es correcto, de lo contrario false
     */
    private boolean validateEmail(String email){
        if(!Validations.validateEmail(email)){
            textInputLayoutPassword.setError(getResources().getString(R.string.error_password_or_email));
            return false;
        }else
        {
            textInputLayoutPassword.setError(null);
        }
        return true;
    }

    /**
     * Método que se encarga de ir de la actividad LoginActivity a la actividad SelectRolActivity
     *
     * @param v View que gestiona el evento
     */
    public void goToSelectRolActivity(View v){
        Intent intent = new Intent(this,SelectRolActivity.class);
        startActivity(intent);
    }

    /**
     * Método que se encarga de ir de la actividad LoginActivity a la actividad ProfileActivity
     * @param v View que gestiona el evento
     * @param researcher investigador a ser enviado
     * @param researchGroup grupo de investigación a ser enviado
     */
    public void goToProfileActivity(View v, Researcher researcher, ResearchGroup researchGroup){
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(researcher!=null) {
            intent.putExtra(KEY_PARCELABLERESEARCHER,researcher);
        }else{
            intent.putExtra(KEY_PARCELABLERESEARCHGROUP,researchGroup);
        }
        startActivityForResult(intent,1);
    }


}
