package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearchGroupActivity;

/**
 * Fragmento utilizado para agregar investigadores a un grupo de investigación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class AddResearcherFragment extends DialogFragment {

    /**
     * Atributo que representa el campo investigador
     */
    @BindView(R.id.autoCompleteTextViewResearcher)
    protected AutoCompleteTextView autoCompleteTextViewResearcher;

    /**
     * Atributo que representa el botón agregar investigador
     */
    @BindView(R.id.buttonAddResearcher)
    protected Button buttonAddResearcher;

    /**
     * Atributo que representa la imagen para cerrar el diálogo
     */
    @BindView(R.id.imageViewCloseAddResearcher)
    protected ImageView imageViewClose;

    /**
     * Atributo que representa la posición del investigador seleccionado
     */
    private int positionResearcher;

    /**
     * Atributo que representa el nombre del investigador seleccionado
     */
    private String nameSelection;

    /**
     * Atributo que representa la lista de nombres de los investigadores
     */
    private String [] names;
    /**
     * Atributo que representa el oyente del diálogo
     */
    private AddResearcherListener listener;

    /**
     * Atributo encargado de validar que si se halla seleccionado un investigador
     */
    private Boolean validateResearcher;

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public AddResearcherFragment() {

    }

    /**
     * Método encargado de crear el fragmento
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positionResearcher=0;
        validateResearcher=false;
        setStyle(STYLE_NO_TITLE, 0);
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
        View view =inflater.inflate(R.layout.fragment_add_researcher, container, false);
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
        buttonAddResearcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPosition();
                sendResearcherPosition();

            }
        });
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        names =namesResearchers();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),R.layout.spinner_item,names);
        autoCompleteTextViewResearcher.setAdapter(adapter);
        autoCompleteTextViewResearcher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                nameSelection = (String) parent.getItemAtPosition(pos);
                validateResearcher=true;
            }
        });
    }

    /**
     * Método utilizado para obtener la posición del investigador seleccionad
     */
    public void searchPosition(){
        if(nameSelection!=null) {
            for (int i = 0; i < names.length; i++) {
                if (nameSelection.equals(names[i])) {
                    positionResearcher = i;
                    break;
                }
            }
        }
    }

    /**
     * Método utilizado para obtener los nombres de los investigadores
     * @return una lista de nombres de los investigadores
     */
    public String [] namesResearchers(){
       return ((NewResearchGroupActivity)getActivity()).namesResearchers();
    }


    /**
     * Método utilizado para enviar la posición del investigador a la actividad
     */
    public void sendResearcherPosition() {
        if (validateResearcher) {
            listener.sendResearcherPosition(positionResearcher);
            cleanDialog();
        }
    }

    /**
     * Método utilizado para limpiar los campos del diálogo
     */
    public void cleanDialog(){
        autoCompleteTextViewResearcher.setText("");
        validateResearcher=false;
    }

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface AddResearcherListener {
        void sendResearcherPosition(int position);
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
                listener = (AddResearcherListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz AddResearcherListener");
            }
        }
    }
}
