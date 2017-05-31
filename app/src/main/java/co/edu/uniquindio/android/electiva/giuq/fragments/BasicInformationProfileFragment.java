package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;

/**
 * Fragmento encargado de mostrar la información básica de los usuarios
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class BasicInformationProfileFragment extends Fragment implements View.OnClickListener {

    /**
     * Atributo que representa el TextView variousOne
     */
    @BindView(R.id.textViewVariousOneInformationProfile)
    protected TextView textViewVariousOne;

    /**
     * Atributo que representa el TextView variousTwo
     */
    @BindView(R.id.textViewVariousTwoInformationProfile)
    protected TextView textViewVariousTwo;

    /**
     * Atributo que representa el TextView variousTwo
     */
    @BindView(R.id.textViewVariousThreeInformationProfile)
    protected TextView textViewVariousThree;

    /**
     * Atributo que representa el TextView  email
     */
    @BindView(R.id.textViewEmailBasicInformationProfile)
    protected TextView textViewEmail;

    /**
     * Atributo que representa el TextView  nombre
     */
    @BindView(R.id.textViewNameBasicInformationProfile)
    protected TextView textViewName;

    /**
     * Atributo que representa la vista para compartir contenido en twitter
     */
    @BindView(R.id.twitterShare)
    protected View viewTwitterShare;

    /**
     * Atributo que representa la vista para compartir contenido en facebook
     */
    @BindView(R.id.facebookShare)
    protected View viewFacebookShare;

    /**
     * Atributo que representa el diálogo de facebook
     */
    private ShareDialog shareDialogFacebook;

    /**
     * Atributo utilizado para autenticar un usuario en twitter
     */
    private TwitterAuthClient twitterAuthClient;

    /**
     * Atributo utilizado para validar si el usuario ya esta conectado en facebook
     */
    private Boolean validateFacebook;

    /**
     * Atributo que representa una llave string nombre
     */
    private static final String NAME ="name";

    /**
     * Atributo que representa una llave string nombre
     */
    private static final String VARIOUS_TWO ="various_two";

    /**
     * Atributo que representa una llave string nombre
     */
    private static final String VARIOUS_ONE ="various_one";

    /**
     * Atributo que representa una llave string nombre
     */
    private static final String EMAIL ="email";

    /**
     * Atributo que representa una llave string nombre
     */
    private static final String URLCVLAC ="url_cvlac";

    /**
     * Atributo que representa el nombre de un usuario
     */
    private String name;

    /**
     * Atributo que representa varios atributos de un usuario
     */
    private String variousOne;

    /**
     * Atributo que representa varios atributos de un usuario
     */
    private String variousTwo;

    /**
     * Atributo que representa la urlCVLAC
     */
    private String urlCVLAC;

    /**
     * Atributo que representa el email de un usario
     */
    private String email;

    /**
     * Atributo que representa el menú que se despliega del float action button
     */
    private FABToolbarLayout morph;

    /**
     * Es obligatorio un constructor vacío para instanciar el fragmento
     */
    public BasicInformationProfileFragment() {

    }

    /**
     * Método que permite crear una nueva instancia del fragmento
     * @param name nombre de un usuario
     * @param variousOne varios atributos de un usuario
     * @param variousTwo varios atributos de un usuario
     * @param email email de un usuario
     * @return instancia
     */
    public static BasicInformationProfileFragment newInstance(String name, String variousOne,String variousTwo,String email,String urlCVLAC) {
        BasicInformationProfileFragment fragment = new BasicInformationProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NAME, name);
        bundle.putString(VARIOUS_ONE, variousOne);
        bundle.putString(VARIOUS_TWO, variousTwo);
        bundle.putString(EMAIL, email);
        bundle.putString(URLCVLAC,urlCVLAC);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Método encargado de crear el fragmento
     *
     * @param savedInstanceState infomación a ser recepcionada
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validateFacebook=false;
        this.name= (getArguments() != null) ? getArguments().getString(NAME) : "";
        this.variousOne= (getArguments() != null) ? getArguments().getString(VARIOUS_ONE) : "";
        this.variousTwo= (getArguments() != null) ? getArguments().getString(VARIOUS_TWO): "";
        this.email= (getArguments() != null) ? getArguments().getString(EMAIL) : "";
        this.urlCVLAC= (getArguments() != null) ? getArguments().getString(URLCVLAC) : "";
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
        View view = inflater.inflate(R.layout.fragment_basic_information_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Método llamado después de que se ha completado el onCreate
     * Se utiliza para inicializaciones finales y para modificar elementos de la interfaz
     *
     * @param savedInstanceState información a ser recepcionada
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewName.setText(name);
        textViewEmail.setText(email);
        textViewVariousOne.setText(variousOne);
        textViewVariousTwo.setText(variousTwo);
        textViewVariousThree.setText(urlCVLAC);
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        morph = (FABToolbarLayout) getView().findViewById(R.id.fabtoolbar);
        fab.setOnClickListener(this);
        viewFacebookShare.setOnClickListener(this);
        viewTwitterShare.setOnClickListener(this);
        shareDialogFacebook = new ShareDialog(getActivity());
    }

    /**
     * Método encargado de escuchar los eventos
     *
     * @param v control que ejecuta el evento
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            morph.show();
        }
        morph.hide();
        switch (v.getId()) {
            case R.id.facebookShare:{
                if(!validateFacebook){
                    loginFacebook();
                }else{
                    shareFacebook();
                }
                break;
            }

            case R.id.twitterShare: {
                break;
            }

        }
    }

    /**
     * Método utilizado para obtener resultados de los fragmentos
     * @param requestCode Código de solicitu
     * @param resultCode Un código de resultado que puede ser RESULT_OK si la operación se realizó correctamente o RESULT_CANCELED si el usuario canceló la operación o esta falló por algún motivo.
     * @param data  información del resultado.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getActivity().RESULT_OK){
            validateFacebook=true;
            Bundle bundle = data.getExtras();
            String fbData = bundle.toString();
        }else
        {
            Log.e("Error","I have no idea what is happening :"+ data.getExtras().toString());
        }
    }


    /**
     * Método utilizado para loguear un usuario de facebook
     */
    public void loginFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
    }

    /**
     * Método utilizado para compartir información en facebook
     */
    public void shareFacebook(){
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(urlCVLAC
                    ))
                    .setQuote(name+"\n"+variousOne+"\n"+variousTwo)

                    .setShareHashtag(new ShareHashtag.Builder()

                            .setHashtag("#GIUQ")

                            .build()).build();

            shareDialogFacebook.show(content);

        }
    }

}
