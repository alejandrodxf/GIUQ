package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
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
     * Atributo que representa la imagen login de facebook
     */
    @BindView(R.id.imageViewFacebookLogin)
    protected ImageView imageViewFacebookLogin;

    /**
     * Atributo que representa la imagen compartir de facebook
     */
    @BindView(R.id.imageViewFacebookShare)
    protected ImageView imageViewFacebookShare;

    /**
     * Atributo que representa la imagen login de twitter
     */
    @BindView(R.id.imageViewTwitterLogin)
    protected ImageView imageViewTwitterLogin;

    /**
     * Atributo que representa la imagen compartir de twitter
     */
    @BindView(R.id.imageViewTwitterShare)
    protected ImageView imageViewTwitterShare;

    private ShareDialog shareDialogFacebook;

    private TwitterAuthClient twitterAuthClient;

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
     * Atributo que representa el email de un usario
     */
    private String email;

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
    public static BasicInformationProfileFragment newInstance(String name, String variousOne,String variousTwo,String email) {
        BasicInformationProfileFragment fragment = new BasicInformationProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NAME, name);
        bundle.putString(VARIOUS_ONE, variousOne);
        bundle.putString(VARIOUS_TWO, variousTwo);
        bundle.putString(EMAIL, email);
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
        this.name= (getArguments() != null) ? getArguments().getString(NAME) : "";
        this.variousOne= (getArguments() != null) ? getArguments().getString(VARIOUS_ONE) : "";
        this.variousTwo= (getArguments() != null) ? getArguments().getString(VARIOUS_TWO): "";
        this.email= (getArguments() != null) ? getArguments().getString(EMAIL) : "";
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
        shareDialogFacebook = new ShareDialog(getActivity());
        imageViewFacebookLogin.setOnClickListener(this);
        imageViewFacebookShare.setOnClickListener(this);
        imageViewTwitterShare.setOnClickListener(this);
        imageViewTwitterLogin.setOnClickListener(this);
        twitterAuthClient = new TwitterAuthClient();
        disabledTwitterLogin();
    }

    /**
     * Método encargado de escuchar los eventos
     *
     * @param v control que ejecuta el evento
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewFacebookLogin: {
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
                break;
            }
            case R.id.imageViewFacebookShare: {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://www.uniquindio.edu.co/"
                            )).setQuote("GIUQ")
                            .setShareHashtag(new ShareHashtag.Builder().setHashtag("#GIUQ").build()).build();
                    shareDialogFacebook.show(content);
                }
                break;
            }
            case R.id.imageViewTwitterLogin:{

                Log.v("Basic","twitter");

                twitterAuthClient.authorize(getActivity(), new com.twitter.sdk.android.core.Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                         Twitter.getInstance().core.getSessionManager().getActiveSession();

                        TwitterSession session = result.data;
                        twitterAuthClient.requestEmail(session, new com.twitter.sdk.android.core.Callback<String>() {
                            @Override
                            public void success(Result<String> result) {
                                Log.e("preuba", "Twitterkit email id get success = " + result.data);
                            }

                            @Override
                            public void failure(TwitterException exception) {
                                Log.e("prueba", "Twitter kit twitter email get failed");
                                exception.printStackTrace();
                            }
                        });


                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.e("prueba", "Twitter kit twitter login failed");
                        exception.printStackTrace();
                    }
                });
                break;
            }
            case R.id.imageViewTwitterShare:{
                Log.v("entro","entro");
            }

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if(resultCode == getActivity().RESULT_OK){
          imageViewFacebookLogin.setVisibility(View.INVISIBLE);
        }else

        {
        }*/

    }

    /**
     * Método encargado de ocultar el botón de login twitter
     */
    public void disabledTwitterLogin(){
        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        if( session != null ){
            imageViewTwitterLogin.setVisibility(View.INVISIBLE);
        }
    }
}
