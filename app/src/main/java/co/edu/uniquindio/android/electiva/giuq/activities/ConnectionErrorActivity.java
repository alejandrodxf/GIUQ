package co.edu.uniquindio.android.electiva.giuq.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import co.edu.uniquindio.android.electiva.giuq.R;

/**
 * Actividad utilizada para mostrar un mensaje de error cuando no hay conexión a internet
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class ConnectionErrorActivity extends AppCompatActivity {

    /**
     * Método que se encarga de realizar la conexión con la parte lógica
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_error);
    }
}
