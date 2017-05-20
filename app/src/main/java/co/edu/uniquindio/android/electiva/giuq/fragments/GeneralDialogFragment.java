package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.LoginActivity;

/**
 * Fragmento utilizado para mostrar diversos diálogos
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class GeneralDialogFragment extends DialogFragment {

    /**
     * Atributo que representa la imagen para cerrar el diálogo
     */
    @BindView(R.id.imageViewCloseGeneralDialog)
    protected ImageView imageViewClose;

    /**
     * Atributo que representa el TextView título
     */
    @BindView(R.id.textViewTitleGeneralDialog)
    protected TextView textViewTitle;

    /**
     * Atributo que representa el TextView cuerpo
     */
    @BindView(R.id.textViewBodyGeneralDialog)
    protected TextView textViewBody;

    /**
     * Atributo que representa una llave string de título de diálogo
     */
    public static final String TITLE="title";
    /**
     * Atributo que representa una llave string de cuerpo de diálogo
     */
    public static final String BODY="body";
    /**
     * Atributo que representa una llave string de tipo de diálogo
     */
    public static final String TYPE="type";
    /**
     * Atributo que representa el tipo de díálogo a mostrar
     */
    private String type;
    /**
     * Atributo que representa el título del diálogo
     */
    private String title;
    /**
     * Atributo que representa el cuerpo del diálogo
     */
    private String body;

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public GeneralDialogFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static GeneralDialogFragment newInstance(String title,String body,String type){
        GeneralDialogFragment  fragment = new GeneralDialogFragment ();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        bundle.putString(BODY,body);
        bundle.putString(TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Método encargado de crear el fragmento
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.title = (getArguments() != null) ? getArguments().getString(TITLE) : "";
        this.body = (getArguments() != null) ? getArguments().getString(BODY) : "";
        this.type=(getArguments() != null) ? getArguments().getString(TYPE) : "";

    }

    /**
     * Método encargado de cargar la vista asociada al fragmento
     * @param inflater
     * @param container
     * @param savedInstanceState información a ser recepcionada
     * @return la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_dialog, container, false);
        ButterKnife.bind(this,view);
        return  view;
    }

    /**
     * Método llamado después de que se ha completado el onCreate
     * Se utiliza para inicializaciones finales y para modificar elementos de la interfaz
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewTitle.setText(title);
        textViewBody.setText(body);
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * Método llamado al cerrar el diálogo
     */
    @Override
    public void dismiss() {
        super.dismiss();
        if(type.equals("DONE")){
            goToLoginActivity(getView());
        }
    }

    /**
     * Método que se encarga de ir del diálogo GeneralDialog a la actividad LoginActivity
     *
     * @param v View que gestiona el evento
     */
    public void goToLoginActivity(View v) {
        Intent intent = new Intent(this.getContext(),LoginActivity.class);
        startActivity(intent);
    }


}
