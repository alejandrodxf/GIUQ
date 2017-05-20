package co.edu.uniquindio.android.electiva.giuq.activities;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.fragments.LanguageDialogFragment;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setSupportActionBar(toolbarContentProfile);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        // Poner ícono del drawer toggle
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
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

}
