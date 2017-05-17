package co.edu.uniquindio.android.electiva.giuq.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;

/**
 * Diálogo utilizado para agregar las líneas de investigación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class AddLineOfResearchFragment extends DialogFragment {

    /**
     * Atributo que representa el botón para agregar una línea de investigación
     */
    @BindView(R.id.buttonAddLineOfResearch)
    protected Button buttonAddLineOfResearch;

    /**
     * Atributo que representa el botón cancelar
     */
    @BindView(R.id.buttonCancelLineOfResearch)
    protected Button buttonCancel;

    /**
     * Atributo que representa el campo línea de investigación
     */
    @BindView(R.id.editTextAddLineOfResearch)
    protected EditText editTextLineOfResearch;

    /**
     * Atributo que representa el estado de la línea de investigación
     */
    @BindView(R.id.radioGroupStatusLineOfResearch)
    protected RadioGroup radioGroupStatus;

    /**
     * Atributo que representa el oyente del diálogo
     */
    private AddLineOfResearchListener listener;
    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public AddLineOfResearchFragment() {

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
        View view =inflater.inflate(R.layout.fragment_add_line_of_research, container, false);
        ButterKnife.bind(this,view);
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
        buttonAddLineOfResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLineOfResearch();
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
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface AddLineOfResearchListener {
        void sendLineOfResearch(String lineOfResearch,Boolean status);
    }


    /**
     * Método utilizado para enviar la información de la línea de la investigación a la actividad
     */
    public void sendLineOfResearch() {
        String lineOfResearch = editTextLineOfResearch.getText().toString();
        boolean status=false;
        int selectedId = radioGroupStatus.getCheckedRadioButtonId();
        switch (selectedId){
            case R.id.radioButtonActive:{
                status=true;
                break;
            }
            case R.id.radioButtonInactive:{
                status=false;
                break;
            }
        }
        if (!lineOfResearch.isEmpty()) {
            listener.sendLineOfResearch(lineOfResearch,status);
            editTextLineOfResearch.setText("");
        }
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
                listener = (AddLineOfResearchListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz AddLineOfResearchListener");
            }

        }
    }
}
