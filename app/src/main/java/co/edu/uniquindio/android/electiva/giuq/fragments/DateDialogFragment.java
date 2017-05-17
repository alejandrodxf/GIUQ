package co.edu.uniquindio.android.electiva.giuq.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Diálogo utilizado para mostrar el selector de fechas
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class DateDialogFragment extends DialogFragment {

    DatePickerDialog.OnDateSetListener ondateSet;
    /**
     * Atributo que representa el día
     */
    private int day;
    /**
     * Atributo que representa el month
     */
    private int month;
    /**
     * Atributo que representa el year
     */
    private int year;

    /**
     * Es obligatorio un contructor vacío para instanciar el fragmento
     */
    public DateDialogFragment() {}

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        ondateSet = ondate;
    }

    /**
     * Método utilizado para suministrar los argumentos de construcción para este fragmento
     * @param args
     */
    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    /**
     * Método encarcagado de construir el diálogo
     * @param savedInstanceState información a ser recepcionada
     * @return Una nueva instancia del diálogo que se muestra por el fragmento
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
    }

}
