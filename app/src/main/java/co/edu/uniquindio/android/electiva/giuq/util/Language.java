package co.edu.uniquindio.android.electiva.giuq.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Clase utilizada para el manejo del lenguaje de la aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class Language {

    public final static String PREFERENCES= "preferences";
    public final static String LANGUAGE_OF_PREFERENCE = "language_of_preference";
    public final static String LANGUAGE_ES = "es";
    public final static String LANGUAGE_EN = "en";

    /**
     * Método utilizado para cambiar el lenguaje de la aplicación
      * @param context
     * @param languageSelected lenguaje seleccionado por el usuario
     */
    public static void changeLanguage(Context context, String languageSelected) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LANGUAGE_OF_PREFERENCE, languageSelected);
        editor.commit();
        setLanguage(context,languageSelected);
    }

    /**
     * Método utilizado para guardar el lenguaje seleccionado por el usuario
     * @param context
     * @param languageSelected lenguaje seleccionado
     */
    public static void setLanguage(Context context,String languageSelected){
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
        Locale locale = new Locale(languageSelected);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    /**
     * Método ttilizado para obtener el lenguaje actual de la aplicación
     * @param context
     * @return lenguaje de la aplicación
     */
    public static String getLanguage(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
        String language = prefs.getString(LANGUAGE_OF_PREFERENCE,LANGUAGE_EN);
        return language;
    }


}
