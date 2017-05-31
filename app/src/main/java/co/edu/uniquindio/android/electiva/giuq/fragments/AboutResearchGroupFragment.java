package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearchGroupActivity;

/**
 * Fragmento utilizado para obtener la información básica de un grupo de investigación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class AboutResearchGroupFragment extends Fragment {

    /**
     * Atributo que representa el campo name
     */
    @BindView(R.id.textInputLayoutNameResearchGroup)
    protected TextInputLayout textInputLayoutName;

    /**
     * Atributo que representa el campo de texto name
     */
    @BindView(R.id.editTextNameResearchGroup)
    protected EditText editTextName;

    /**
     * Atributo que representa el campo acronym
     */
    @BindView(R.id.textInputLayoutAcronymResearchGroup)
    protected TextInputLayout textInputLayoutAcronym;

    /**
     * Atributo que representa el campo de texto acronym
     */
    @BindView(R.id.editTextAcronymResearchGroup)
    protected EditText editTextAcronym;

    /**
     * Atributo que representa el campo leader
     */
    @BindView(R.id.autoCompleteTextViewLeaderResearchGroup)
    protected AutoCompleteTextView autoCompleteTextViewLeader;

    /**
     * Atributo que representa el campo url CVLAC
     */
    @BindView(R.id.textInputLayoutUrlCvlacResearchGroup)
    protected TextInputLayout textInputLayoutUrlCvlac;

    /**
     * Atributo que representa el campo de texto url CVLAC
     */
    @BindView(R.id.editTextUrlCvlacResearchGroup)
    protected EditText editTextUrlCvlac;

    /**
     * Atributo que representa el campo email
     */
    @BindView(R.id.textInputLayoutEmailResearchGroup)
    protected TextInputLayout textInputLayoutEmail;

    /**
     * Atributo que representa el campo de texto email
     */
    @BindView(R.id.editTextEmailResearchGroup)
    protected EditText editTextEmail;

    /**
     * Atributo que representa el campo password
     */
    @BindView(R.id.textInputLayoutPasswordResearchGroup)
    protected TextInputLayout textInputLayoutPassword;

    /**
     * Atributo que representa el campo de texto password
     */
    @BindView(R.id.editTextPasswordResearchGroup)
    protected EditText editTextPassword;

    /**
     * Atributo que representa el seleccionador de categoría
     */
    @BindView(R.id.spinnerCategoryResearchGroup)
    protected Spinner spinnerCategory;

    /**
     * Atributo que representa los nombres de los investigadores activos
     */
    private String []names;

    /**
     * Atributo que representa el nombre de un investigador seleccionado
     */
    private String nameSelection;

    /**
     * Atributo que representa la posición del lider del grupo de investigación en la base de datos
     */
    private int positionLeader;

    /**
     * Atributo encargado de validar que si se halla seleccionado un lider para el grupo de investigación
     */
    private Boolean validateResearcherLeader;

    /**
     * Atributo que representa el oyente del fragmento
     */
    private AboutResearchGroupListener listener;



    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public AboutResearchGroupFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static AboutResearchGroupFragment newInstance() {
        AboutResearchGroupFragment  fragment = new AboutResearchGroupFragment ();
        Bundle bundle = new Bundle();
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
        validateResearcherLeader=false;
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
        View view = inflater.inflate(R.layout.fragment_about_research_group, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    /**
     * Método llamado después de que se ha completado el onCreate
     * Se utiliza para inicializaciones finales y para modificar elementos de la interfaz
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        names=namesResearchers();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),R.layout.spinner_item,names);
        autoCompleteTextViewLeader.setAdapter(adapter);
        autoCompleteTextViewLeader.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                nameSelection = (String) parent.getItemAtPosition(pos);
                validateResearcherLeader=true;
            }
        });
        ArrayAdapter<CharSequence> adapterSpinnerCategory = ArrayAdapter.createFromResource(this.getContext(), R.array.category_research_group_array,R.layout.spinner_item);
        spinnerCategory.setAdapter(adapterSpinnerCategory);
    }

    /**
     * Método encargado de enviar la información básica del grupo de investigación a la actividad
     * @return true si los campos están correctos y completos, de lo contrario false
     */
    public boolean sendAboutResearchGroup(){
        String name= editTextName.getText().toString();
        String acronym = editTextAcronym.getText().toString();
        int category =spinnerCategory.getSelectedItemPosition();
        if(nameSelection!=null||validateResearcherLeader) {
            searchPosition();
            validateResearcherLeader=false;
        }else{
            return false;
        }
        String urlCvlac = editTextUrlCvlac.getText().toString();
        String email= editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        boolean validateName=validateFields(name,textInputLayoutName,getResources().getString(R.string.name_group));
        boolean validateAcronym=validateFields(acronym,textInputLayoutAcronym,getResources().getString(R.string.acronym));
        boolean validateEmail=validateEmail(email);
        boolean validateUrlCvlac=validateUrlCvlac(urlCvlac);
        boolean validatePassword= validatePassword(password);
        listener.sendAboutResearchGroup(name,acronym,category,positionLeader,urlCvlac,email,password);
        if(!validateName||!validateAcronym||!validateEmail||!validateUrlCvlac||!validatePassword)
        {
            return false;
        }else{
            return true;
        }

    }
    /**
     * Método encargado de validar campos en general
     * @param field campo a validar
     * @return true si el campo es correcto, de lo contrario false
     */
    public boolean validateFields(String field ,TextInputLayout textInputLayout,String label){
        boolean validate =((NewResearchGroupActivity)getActivity()).validateFields(field);
        if(!validate){
            textInputLayout.setError(label+" "+getResources().getString(R.string.error_field));
        }else{
            textInputLayout.setError(null);
        }
        return validate;
    }

    /**
     * Método encargado de validar si la contraseña es correcta
     * @param password contraseña ingresada por el usuario
     * @return true si la contraseña es correcta, de lo contrario false
     */
    public boolean validatePassword(String password){
        boolean validate = ((NewResearchGroupActivity)getActivity()).validatePassword(password);
        if(!validate){
            textInputLayoutPassword.setError(getResources().getString(R.string.error_password));
        }else{
            textInputLayoutPassword.setError(null);
        }
        return validate;
    }

    /**
     * Método encargado de validar la url del CVLAC
     * @param urlCvlac url del CVLAC ingresada por el usuario
     * @return true si la url es correcta, de lo contrario false
     */
    public boolean validateUrlCvlac(String urlCvlac){
        boolean validate = ((NewResearchGroupActivity)getActivity()).validateUrlCvlac(urlCvlac);
        if(!validate){
            textInputLayoutUrlCvlac.setError(getResources().getString(R.string.error_url_cvlac));

        }else{
            textInputLayoutUrlCvlac.setError(null);
        }
        return validate;
    }

    /**
     * Método encargado de validar el email
     * @param email email ingresado por el usuario
     * @return true si el email es correcto, de lo contrario false
     */
    public boolean validateEmail(String email){
        boolean validate =((NewResearchGroupActivity)getActivity()).validateEmail(email);
        if (!validate) {
            textInputLayoutEmail.setError(getResources().getString(R.string.error_email));
        }else
        {
            textInputLayoutEmail.setError(null);
        }
        return validate;
    }

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface AboutResearchGroupListener {
        void sendAboutResearchGroup(String name,String acronym,int category,int leader,String urlCvlac,String email,String password);
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
                listener = (AboutResearchGroupListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz AboutResearchGroupListener");
            }

        }
    }

    /**
     * Método utilizado para obtener la posición del investigador seleccionad
     */
    public void searchPosition(){
        for(int i=0; i<names.length;i++){
            if(nameSelection.equals(names[i])){
                positionLeader=i;
                break;
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
}
