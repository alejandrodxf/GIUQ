package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.vo.AcademicTitle;

/**
 * Diálogo utilizado para agregar los títulos obtenidos por un investigador
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class AddAcademicTitleFragment extends DialogFragment {

    /**
     * Atributo que representa el botón para agregar una fecha de graduación
     */
    @BindView(R.id.buttonAddDateGraduation)
    protected Button buttonAddDateGraduation;

    /**
     * Atributo que representa el botón para agregar un título académico
     */
    @BindView(R.id.buttonAddAcademicTitle)
    protected Button buttonAddAcademicTitle;

    /**
     * Atributo que representa el campo título académico
     */
    @BindView(R.id.editTextAcademicTitle)
    protected EditText editTextAcademicTitle;

    /**
     * Atributo que representa el campo institución
     */
    @BindView(R.id.editTextInstitution)
    protected EditText editTextInstitution;

    /**
     * Atributo que representa el botón cancelar
     */
    @BindView(R.id.buttonCancelAddAcademicTitle)
    protected Button buttonCancel;

    /**
     * Atributo que representa el oyente del diálogo
     */
    private AcademicTitleListener listener;

    /**
     * Atributo que representa una fecha de graduación
     */
    private Date graduationDate;

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public AddAcademicTitleFragment() {
    }


    /**
     * Método encargado de crear el fragmento
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método encargado de cargar la vista asociada al fragmento
     * @param inflater
     * @param container
     * @param savedInstanceState información a ser recepcionada
     * @return la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_academic_title, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Método llamado después de que se ha completado el onCreate
     * Se utiliza para inicializaciones finales y para modificar elementos de la interfaz
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonAddDateGraduation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        buttonAddAcademicTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAcademicTitle();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * Método utilizado para mostrar el DatePicker en pantalla
     */
    private void showDatePicker() {
        DateDialogFragment date = new DateDialogFragment();
        // Configura la fecha actual en el diálogo
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        // Establece un callback de nuevo para capturar la fecha seleccionada
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    /**
     * listener usado para indicar que el usuario ha terminado de seleccionar una fecha
     */
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int año, int mes, int dia) {
            graduationDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                graduationDate = sdf.parse(String.valueOf(dia) + "/" + String.valueOf(mes + 1) + "/" + String.valueOf(año));
            } catch (ParseException e) {

            }
            buttonAddDateGraduation.setText(String.valueOf(dia) + "/" + String.valueOf(mes + 1) + "/" + String.valueOf(año));
        }
    };

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface AcademicTitleListener {
        void sendAcademicTitle(AcademicTitle academicTitle);
    }

    /**
     * Método utilizado para enviar la información del título académico a la actividad
     */
    public void sendAcademicTitle() {
        String academicTitleT = editTextAcademicTitle.getText().toString();
        String institution = editTextInstitution.getText().toString();
        if (!academicTitleT.isEmpty() && !institution.isEmpty() && graduationDate != null) {
         AcademicTitle academicTitle = new AcademicTitle(academicTitleT, institution, graduationDate);
            listener.sendAcademicTitle(academicTitle);
            cleanDialog();
        }
    }

    /**
     * Método utilizado para limpiar los campos del diálogo
     */
    public void cleanDialog(){
        editTextAcademicTitle.setText("");
        editTextInstitution.setText("");
        graduationDate=null;
        buttonAddDateGraduation.setText(getResources().getString(R.string.graduation_date));
    }
    /**
     * Método que adjunta el fragmento en la actividad
     *
     * @param context contexto de la actividad
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (AcademicTitleListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz AcademicTitleListener");
            }

        }
    }
}
