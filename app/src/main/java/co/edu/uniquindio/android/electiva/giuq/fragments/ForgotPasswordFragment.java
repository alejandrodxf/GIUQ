package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.uniquindio.android.electiva.giuq.R;

/**
 * Fragmento utilizado para recuperar la contraseña de un usuario
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class ForgotPasswordFragment extends DialogFragment {

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public ForgotPasswordFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
