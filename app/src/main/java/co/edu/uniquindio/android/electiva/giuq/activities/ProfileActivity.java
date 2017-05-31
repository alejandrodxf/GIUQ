package co.edu.uniquindio.android.electiva.giuq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.ControllerApplication;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.fragments.AcademicTitleFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.InformationProfileFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.LanguageDialogFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.LineOfResearchFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.ListUsersSearchFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.MembersResearchGroupFragment;
import co.edu.uniquindio.android.electiva.giuq.fragments.ProfileFragment;
import co.edu.uniquindio.android.electiva.giuq.vo.AcademicTitle;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;
import co.edu.uniquindio.android.electiva.giuq.vo.User;
import io.fabric.sdk.android.Fabric;

import static co.edu.uniquindio.android.electiva.giuq.activities.LoginActivity.KEY_PARCELABLERESEARCHER;
import static co.edu.uniquindio.android.electiva.giuq.activities.LoginActivity.KEY_PARCELABLERESEARCHGROUP;

/**
 * Actividad utilizada para mostrar el perfil a un usuario que ha iniciado sesión, es decir, se utiliza
 * tanto para investigadores y grupos de investigación
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,AcademicTitleFragment.OnSelectedAcademicTitleListener,LineOfResearchFragment.OnSelectedLineOfResearchListener,MembersResearchGroupFragment.OnSelectedMembersResearchGroupListener,ListUsersSearchFragment.OnSelectedListUsersSearchListener {

    /**
     *Atributo que representa el drawerLayout
     */
    @BindView(R.id.drawerLayoutProfile)
    protected DrawerLayout drawerLayoutProfile;

    /**
     * Atributo que representa el menú deslizante
     */
    @BindView(R.id.navigationViewProfile)
    protected NavigationView navigationView;
    /**
     * Atributo que representa el contenedor de fragmentos
     */
    @BindView(R.id.toolbarContentProfile)
    protected Toolbar toolbarContentProfile;

    /**
     * Atributo que gestiona las devoluciones de llamada en el FacebookSdk
     */
    private CallbackManager callbackManager;

    /**
     * Atributa que representa una lista de usuarios
     */
    private ArrayList<User>users;
    /**
     * Atributo que representa un investigador
     */
    private Researcher researcher;
    /**
     * Atributo que representa un grupo de investigación
     */
    private ResearchGroup researchGroup;

    /**
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        researcher = getIntent().getParcelableExtra(KEY_PARCELABLERESEARCHER);
        researchGroup=getIntent().getParcelableExtra(KEY_PARCELABLERESEARCHGROUP);
        View header=navigationView.getHeaderView(0);
        TextView textViewNameHeader = (TextView)header.findViewById(R.id.textViewNameHeader);
        TextView textViewEmailHeader = (TextView)header.findViewById(R.id.textViewEmailHeader);
        if(researcher!=null) {
            textViewNameHeader.setText(researcher.getName());
            textViewEmailHeader.setText(researcher.getEmail());
        }else{
            textViewNameHeader.setText(researchGroup.getName());
            textViewEmailHeader.setText(researchGroup.getEmail());
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setSupportActionBar(toolbarContentProfile);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        // Poner ícono del drawer toggle
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                    }
                    @Override
                    public void onCancel() {

                    }
                    @Override
                    public void onError(FacebookException exception) {

                    }
                });
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getResources().getString(R.string.twitter_key),getResources().getString(R.string.twitter_secret));
       Fabric.with(this, new Twitter(authConfig));
       getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, ProfileFragment.newInstance(0)).commit();
    }

    /**
     * Método utilizado cada vez que se selecciona un elemento del menú
     * @param item Elemento del menú que se ha seleccionado
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayoutProfile.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método utilizado para especificar el menú de opciones de la actividad
     * @param menu menú de opciones
     * @return true para que el menú se muestre false para que no se muestre
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Método que maneja los eventos al seleccionar un item
     * @param item item seleccionado
     * @return no aplica
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_home: {

                break;
            }
            case R.id.menu_edit_profile: {
                break;
            }
            case R.id.menu_privacy:{

                break;}
            case R.id.menu_language:{
                showSelectLanguageDialog(ProfileActivity.class.getName());
                break;}
            case R.id.menu_exit: {

                break;
            }
        }
        item.setChecked(true);
        drawerLayoutProfile.closeDrawers();
        return true;
    }

    /**
     * Método utilizado para mostrar el DialogFragment AddLineOfResearch
     * @param className nombre usado para el manejo de la transacción
     */
    public void showSelectLanguageDialog(String className) {
        LanguageDialogFragment dialogFragment = new LanguageDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), className);
    }

    /**
     * Método utilizado para obtener resultados de los fragmentos
     * @param requestCode Código de solicitu
     * @param resultCode Un código de resultado que puede ser RESULT_OK si la operación se realizó correctamente o RESULT_CANCELED si el usuario canceló la operación o esta falló por algún motivo.
     * @param data  información del resultado.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Método encargado de obtener el investigador que esta ingresando a la aplicación
     * @return investigador que esta ingresando a la aplicación
     */
    public Researcher getResearcher() {
        return researcher;
    }

    /**
     * Método encargado de obtener el grupo de investigación que esta ingresando a la aplicación
     * @return grupo de investigación que esta ingresando a la aplicación
     */
    public ResearchGroup getResearchGroup() {
        return researchGroup;
    }

    /**
     * Método encargado de obtener la lista de grupos de investigación activos
     * @return lista de grupos de investigación activos
     */
    public ArrayList<ResearchGroup>getResearchGroups(){
        return ((ControllerApplication)getApplication()).getActiveResearchGroups();
    }

    /**
     * Método encargado de obtener la lista de investigadores activos
     * @return lista de investigadores activos
     */
    public ArrayList<Researcher> getResearchers(){
        return ((ControllerApplication)getApplication()).getActiveResearchers();
    }

    /**
     * Método utilizado para obtener los títulos académicos de un investigador
     * @param academicTitles títulos académicos de un investigador
     */
    @Override
    public void sendListAcademicTitles(ArrayList<AcademicTitle> academicTitles) {

    }

    /**
     * Método utilizado para obtener las líneas de investigación de un investigador
     * @param linesOfResearch líneas de investigación de un investigador
     */
    @Override
    public void sendListLineOfResearch(ArrayList<LineOfResearch> linesOfResearch) {

    }

    /**
     * Método utilizado para obtener los investigadores de un grupo de investigación
     * @param researchers investigador de un grupo de investigación
     */
    @Override
    public void sendListResearchers(ArrayList<Researcher> researchers) {

    }

    /**
     * Método utilizado para buscar por nombre de investigador, nombre grupo de investigación y por línea de investigación
     * @param nameResearcher nombre investigador
     * @param nameResearchGroup nombre grupo de investigación
     * @param lineOfResearch línea de investigación
     * @return lista de usuarios que coinciden con las variables enviadas por parámetros
     */
    public ArrayList<User> search(String nameResearcher, String nameResearchGroup,String lineOfResearch){
          return users = ((ControllerApplication)getApplication()).search(nameResearcher,nameResearchGroup,lineOfResearch);
    }

    /**
     * Método que recibe la posición del item seleccionado
     * @param position posición del item seleccionado
     */
    public void onItemPosition(int position) {
        if (findViewById(R.id.list_researchers_fragment) != null) {
            Researcher researcher= getResearchers().get(position);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, InformationProfileFragment.newInstance(researcher,null)).commit();
        }else if(findViewById(R.id.list_research_groups_fragment)!=null){
            ResearchGroup researchGroup = getResearchGroups().get(position);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, InformationProfileFragment.newInstance(null,researchGroup)).commit();
        }else if(findViewById(R.id.list_user_search_fragment)!=null){
             if(users.get(position) instanceof Researcher){
                Researcher researcher = (Researcher) users.get(position);
                 getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, InformationProfileFragment.newInstance(researcher,null)).commit();
             }else{
                 ResearchGroup researchGroup = (ResearchGroup) users.get(position);
                 getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment_profile, InformationProfileFragment.newInstance(null,researchGroup)).commit();
             }
        }

    }

    /**
     * Método que permite asignar un valor al atributo users
     *
     * @param users Valor a ser asignado al atributo users
     */
    @Override
    public void sendListUsers(ArrayList<User> users) {
        this.users=users;
    }
}


