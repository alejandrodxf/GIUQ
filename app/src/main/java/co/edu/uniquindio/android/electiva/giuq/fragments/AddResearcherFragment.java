package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;

/**
 * Fragmento utilizado para agregar investigadores a un grupo de investigación
 */
public class AddResearcherFragment extends DialogFragment {


    /**
     * Atributo que representa la imagen para cerrar el diálogo
     */
    @BindView(R.id.imageViewCloseAddResearcher)
    protected ImageView imageViewClose;

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
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),R.layout.spinner_item,COUNTRIES );
        AutoCompleteTextView textView = (AutoCompleteTextView) getView().findViewById(R.id.autoCompleteTextViewResearcher);
        textView.setAdapter(adapter);
    }



    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain","Space","Spain","Space","Spain","Space","Spain","Space","Spain","Space","Spain","Space","Spain","Space"
    };
}
