package co.edu.uniquindio.android.electiva.giuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Clase que representa un usario
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

@IgnoreExtraProperties
public abstract class User implements Parcelable {

    /**
     * Atributo que representa el nombre de un usuario
     */
    private String name;
    /**
     * Atributo que representa el email de un usuario
     */
    private String email;
    /**
     * Atributo que representa la contraseña de un usuario
     */
    private String password;
    /**
     * Atributo que representa la url del CVLAC de un usuario
     */
    private String urlCVLAC;
    /**
     * Atributo que representa la categoría de un usuario
     */
    private int category;
    /**
     * Atributo que representa la foto de un usuario
     */
    private String photo;
    /**
     * Atributo que representa las líneas de investigación de un usuario
     */
    private ArrayList<LineOfResearch> linesOfResearch;
    /**
     * Atributo que representa si el usuario esta activo o inactivo
     */
    private boolean state;


    /**
     * Constructor vacío requerido para utilizar Firebase
     */
    public User(){

    }

    /**
     * Método constructor de la clase
     *
     * @param name nombre del usuario
     * @param email email del usuario
     * @param password contraseña del usuario
     * @param urlCVLAC url del CVLAC del usuario
     * @param category categoría del usuario
     * @param photo   imagen de perfil del usuario
     * @param linesOfResearch lineas de investigación del usuario
     */
    public User(String name, String email, String password, String urlCVLAC, int category, String photo, ArrayList<LineOfResearch> linesOfResearch,boolean state) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.urlCVLAC = urlCVLAC;
        this.category = category;
        this.photo = photo;
        this.linesOfResearch = linesOfResearch;
        this.state=state;
    }


    /**
     * Constructor utilizado para leer el Parcel
     * @param in parcel a leer
     */
    protected User(Parcel in) {
        name = in.readString();
        email=in.readString();
        password=in.readString();
        urlCVLAC=in.readString();
        category=in.readInt();
        photo=in.readString();
        linesOfResearch=in.createTypedArrayList(LineOfResearch.CREATOR);
        state=in.readByte() != 0;

    }

    /**
     * Método que permite escribir un parcel.
     * @param dest  Parcel donde se va escribir
     * @param flags Indica como deberia ser escrito el parcel
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(urlCVLAC);
        dest.writeInt(category);
        dest.writeString(photo);
        dest.writeTypedList(linesOfResearch);
        dest.writeByte((byte) (state ? 1 : 0));
    }


    /**
     * Método utilizado para gestionar el ingreso de un usuario
     * @return true si el usuario ingreso correctamente
     */
    public abstract boolean signInUser();

    /**
     * Método utilizado para gestionar el registro de un usuario
     * @return true si el usuario se registro correctamente
     */
    public abstract boolean signUpUser();

    /**
     * Método que permite obtener el valor del atributo name
     *
     * @return El valor del atributo name
     */
    public String getName() {
        return name;
    }

    /**
     * Método que permite asignar un valor al atributo name
     *
     * @param name Valor a ser asignado al atributo name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método que permite obtener el valor del atributo password
     *
     * @return El valor del atributo password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método que permite asignar un valor al atributo password
     *
     * @param password Valor a ser asignado al atributo password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método que permite obtener el valor del atributo email
     *
     * @return El valor del atributo email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que permite asignar un valor al atributo email
     *
     * @param email Valor a ser asignado al atributo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que permite obtener el valor del atributo linesOfResearch
     *
     * @return El valor del atributo linesOfResearch
     */
    public ArrayList<LineOfResearch> getLinesOfResearch() {
        return linesOfResearch;
    }

    /**
     * Método que permite asignar un valor al atributo linesOfResearch
     *
     * @param linesOfResearch Valor a ser asignado al atributo linesOfResearch
     */
    public void setLinesOfResearch(ArrayList<LineOfResearch> linesOfResearch) {
        this.linesOfResearch = linesOfResearch;
    }

    /**
     * Método que permite obtener el valor del atributo urlCVLAC
     *
     * @return El valor del atributo urlCVLAC
     */
    public String getUrlCVLAC() {
        return urlCVLAC;
    }

    /**
     * Método que permite asignar un valor al atributo urlCVLAC
     *
     * @param urlCVLAC Valor a ser asignado al atributo urlCVLAC
     */
    public void setUrlCVLAC(String urlCVLAC) {
        this.urlCVLAC = urlCVLAC;
    }

    /**
     * Método que permite obtener el valor del atributo category
     *
     * @return El valor del atributo category
     */
    public int getCategory() {
        return category;
    }

    /**
     * Método que permite asignar un valor al atributo category
     *
     * @param category Valor a ser asignado al atributo category
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * Método que permite obtener el valor del atributo photo
     *
     * @return El valor del atributo photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Método que permite asignar un valor al atributo photo
     *
     * @param photo Valor a ser asignado al atributo photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Método que permite obtener el valor del atributo state
     *
     * @return El valor del atributo state
     */
    public boolean isState() {
        return state;
    }

    /**
     * Método que permite asignar un valor al atributo state
     *
     * @param state Valor a ser asignado al atributo state
     */
    public void setState(boolean state) {
        this.state = state;
    }
}
