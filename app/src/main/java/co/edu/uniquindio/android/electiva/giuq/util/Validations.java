package co.edu.uniquindio.android.electiva.giuq.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

/**
 * Clase utilizada para manejar las validaciones
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public final class Validations {

    /**
     * Método encargado de validar campos en general
     * @param field campo a validar
     * @return true si el campo es correcto, de lo contrario false
     */
    public static boolean validateFields(String field){
        field=field.replaceAll(" ","");
        if(field.length()==0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Método encargado de validar si la contraseña es correcta
     * @param password contraseña ingresada por el usuario
     * @return true si la contraseña es correcta, de lo contrario false
     */
    public static boolean validatePassword(String password){
        if(password.length()<6||password.contains(" ")){
            return false;
        }else{
            return true;

        }

    }

    /**
     * Método encargado de validar el email
     * @param email email ingresado por el usuario
     * @return true si el email es correcto, de lo contrario false
     */
    public static boolean validateEmail(String email){
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * Método encargado de validar la url del CVLAC
     * @param url url del CVLAC ingresada por el usuario
     * @return true si la url es correcta, de lo contrario false
     */
    public static boolean validateUrl(String url){
        if(!Patterns.WEB_URL.matcher(url).matches()){
            return false;
        }else{
            return true;
        }
    }

    public static boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }




}
