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
import android.widget.RadioGroup;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearcherActivity;

/**
 * Fragmento utilizado para obtener la información básica de un investigador
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class AboutResearcherFragment extends Fragment {

    /**
     * Atributo que representa el campo full name
     */
    @BindView(R.id.textInputLayoutFullNameResearcher)
    protected TextInputLayout textInputLayoutName;

    /**
     * Atributo que representa el campo password
     */
    @BindView(R.id.textInputLayoutPasswordResearcher)
    protected TextInputLayout textInputLayoutPassword;

    /**
     * Atributo que representa el campo email
     */
    @BindView(R.id.textInputLayoutEmailResearcher)
    protected TextInputLayout textInputLayoutEmail;

    /**
     * Atributo que representa el campo url CVLAC
     */
    @BindView(R.id.textInputLayoutUrlCvlacResearcher)
    protected TextInputLayout textInputLayoutUrlCvlac;

    /**
     * Atributo que representa el campo de texto name
     */
    @BindView(R.id.editTextFullNameResearcher)
    protected EditText editTextName;


    /**
     * Atributo que representa el nombre de un grupo de investigación seleccionado
     */
    private String nameSelection;

    /**
     * Atributo que representa los nombres de los grupos de investigación activos
     */
    private String []names;

    /**
     * Atributo que representa la posición ddel grupo de investigación en la base de datos
     */
    private int positionResearchGroup;

    /**
     * Atributo encargado de validar que si se halla seleccionado grupo de investigación
     */
    private Boolean validateResearchGroup;

    /**
     * Atributo que representa el seleccionador de género
     */
    @BindView(R.id.radioGroupGenre)
    protected RadioGroup radioGroupGenre;

    /**
     * Atributo que representa el seleccionador de nacionalidad
     */
    @BindView(R.id.spinnerNationalityResearcher)
    protected Spinner spinnerNationality;

    /**
     * Atributo que representa el seleccionador de categoría
     */
    @BindView(R.id.spinnerCategoryResearcher)
    protected Spinner spinnerCategory;

    /**
     * Atributo que representa el campo de texto url CVLAC
     */
    @BindView(R.id.editTextUrlCvlacResearcher)
    protected EditText editTextUrlCvlac;

    /**
     * Atributo que representa el campo de texto research group
     */
    @BindView(R.id.autoCompleteTextViewResearchGroupResearcher)
    protected AutoCompleteTextView autoCompleteTextViewResearchGroupResearcher;

    /**
     * Atributo que representa el campo de texto email
     */
    @BindView(R.id.editTextEmailResearcher)
    protected EditText editTextEmail;

    /**
     * Atributo que representa el campo de texto password
     */
    @BindView(R.id.editTextPasswordResearcher)
    protected EditText editTextPassword;


    /**
     * Atributo que representa el oyente del fragmento
     */
    private AboutResearcherListener listener;

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public AboutResearcherFragment() {
    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @return instancia
     */
    public static AboutResearcherFragment newInstance() {
        AboutResearcherFragment  fragment = new AboutResearcherFragment ();
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
        validateResearchGroup=false;

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
        View view = inflater.inflate(R.layout.fragment_about_researcher, container, false);
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
        names=namesResearchGroups();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),R.layout.spinner_item,names);
        autoCompleteTextViewResearchGroupResearcher.setAdapter(adapter);
        autoCompleteTextViewResearchGroupResearcher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                nameSelection = (String) parent.getItemAtPosition(pos);
                validateResearchGroup=true;
            }
        });
        ArrayAdapter<CharSequence> adapterSpinnerNationality = ArrayAdapter.createFromResource(this.getContext(), R.array.nationality_array, R.layout.spinner_item);
        spinnerNationality.setAdapter(adapterSpinnerNationality);
        ArrayAdapter<CharSequence> adapterSpinnerCategory = ArrayAdapter.createFromResource(this.getContext(), R.array.category_researcher_array,R.layout.spinner_item);
        spinnerCategory.setAdapter(adapterSpinnerCategory);

    }

    /**
     * Método encargado de enviar la información básica del investigador a la actividad
     * @return true si los campos están correctos y completos, de lo contrario false
     */
    public boolean sendAboutResearcher(){
        String name= editTextName.getText().toString();
        boolean genre=getGenre();
        int nationality =spinnerNationality.getSelectedItemPosition();
        int category = spinnerCategory.getSelectedItemPosition();
        if(nameSelection!=null||validateResearchGroup) {
            searchPosition();
            validateResearchGroup=false;
        }else{
            positionResearchGroup=-1;
        }
        String urlCvlac = editTextUrlCvlac.getText().toString();
        String email= editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        boolean validateName=validateFields(name,textInputLayoutName,getResources().getString(R.string.full_name));

        boolean validateEmail=validateEmail(email);
        boolean validateUrlCvlac=validateUrlCvlac(urlCvlac);
        boolean validatePassword= validatePassword(password);
        listener.sendAboutResearcher(name,genre,nationality,category,positionResearchGroup,urlCvlac,email,password);
        if(!validateName||!validateEmail||!validateUrlCvlac||!validatePassword)
        {
        return false;
        }else{
            return true;
        }

    }



    /**
     * Método encargado de obtener el género de un investigador
     * @return género del investigador true masculino, false femenino
     */
    public boolean getGenre(){
        boolean genre=true;
        int selectedId = radioGroupGenre.getCheckedRadioButtonId();
        switch (selectedId){
            case R.id.radioButtonMale:{
                genre=true;
                break;
            }
            case R.id.radioButtonFemale:{
                genre=false;
                break;
            }
        }
        return genre;
    }

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interface.
     */
    public interface AboutResearcherListener {
        void sendAboutResearcher(String name,boolean genre,int nationality,int category,int researchGroup,String urlCvlac,String email,String password);
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
                listener = (AboutResearcherListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz AboutResearcherListener");
            }

        }
    }

    /**
     * Método encargado de validar campos en general
     * @param field campo a validar
     * @return true si el campo es correcto, de lo contrario false
     */
    public boolean validateFields(String field ,TextInputLayout textInputLayout,String label){
        boolean validate =((NewResearcherActivity)getActivity()).validateFields(field);
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
        boolean validate = ((NewResearcherActivity)getActivity()).validatePassword(password);
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
        boolean validate = ((NewResearcherActivity)getActivity()).validateUrlCvlac(urlCvlac);
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
        boolean validate =((NewResearcherActivity)getActivity()).validateEmail(email);
        if (!validate) {
            textInputLayoutEmail.setError(getResources().getString(R.string.error_email));
        }else
        {
            textInputLayoutEmail.setError(null);
        }
        return validate;
    }

    /**
     * Método utilizado para obtener la posición del grupo de investigación seleccionado
     */
    public void searchPosition(){
        for(int i=0; i<names.length;i++){
            if(nameSelection.equals(names[i])){
                positionResearchGroup=i;
                break;
            }
        }
    }

    /**
     * Método utilizado para obtener los nombres de los grupos de investigación
     * @return una lista de nombres de los grupos de investigación
     */
    public String [] namesResearchGroups(){
        return ((NewResearcherActivity)getActivity()).namesResearchGroups();
    }


}
