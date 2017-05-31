package co.edu.uniquindio.android.electiva.giuq.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.util.Language;

/**
 * Fragmento utilizado para escoger el lenguaje de la aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class LanguageDialogFragment extends DialogFragment implements View.OnClickListener{


    /**
     * Atributo que representa el estado de la línea de investigación
     */
    @BindView(R.id.radioGroupLanguage)
    protected RadioGroup radioGroupLanguage;

    /**
     * Atributo que representa el lenguaje seleccionado
     */
    @BindView(R.id.buttonSelectLanguage)
    protected Button buttonSelectLanguage;


    /**
     * Atributo que representa la imagen para cerrar el diálogo
     */
    @BindView(R.id.imageViewCloseLanguage)
    protected ImageView imageViewClose;


    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public LanguageDialogFragment() {

    }

    /**
     * Método encargado de crear el fragmento
     *
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    /**
     * Método encargado de cargar la vista asociada al fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState información a ser recepcionada
     * @return la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language_dialog, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    /**
     * Método llamado después de que se ha completado el onCreate
     * Se utiliza para inicializaciones finales y para modificar elementos de la interfaz
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonSelectLanguage.setOnClickListener(this);
        imageViewClose.setOnClickListener(this);

    }

    /**
     * Método encargado de escuchar los eventos de los botones
     *
     * @param v control que ejecuta el evento
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSelectLanguage:{
                int selectedId = radioGroupLanguage.getCheckedRadioButtonId();
                switch (selectedId){
                    case R.id.radioButtonEnglish:{
                        Language.changeLanguage(getContext(),Language.LANGUAGE_EN);
                        break;
                    }
                    case R.id.radioButtonSpanish:{
                        Language.changeLanguage(getContext(),Language.LANGUAGE_ES);
                        break;
                    }
                }
                Intent intent = getActivity().getIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().finish();
                startActivity(intent);
                break;
            }
            case R.id.imageViewCloseLanguage:{
                dismiss();
                break;
            }
        }
    }
}
