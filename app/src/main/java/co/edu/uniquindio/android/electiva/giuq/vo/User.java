package co.edu.uniquindio.android.electiva.giuq.vo;

import java.util.ArrayList;

/**
 * Clase que representa un usario
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public abstract class User {

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
    private String category;
    /**
     * Atributo que representa la foto de un usuario
     */
    private String photo;
    /**
     * Atributo que representa las líneas de investigación de un usuario
     */
    private ArrayList<LineOfResearch> linesOfResearch;

    public User(){

    }

    /**
     * Método constructor de la clase
     * @param name nombre del usuario
     * @param email email del usuario
     * @param password contraseña del usuario
     * @param urlCVLAC url del CVLAC del usuario
     * @param category categoría del usuario
     * @param photo   imagen de perfil del usuario
     * @param linesOfResearch lineas de investigación del usuario
     */
    public User(String name, String email, String password, String urlCVLAC, String category, String photo, ArrayList<LineOfResearch> linesOfResearch) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.urlCVLAC = urlCVLAC;
        this.category = category;
        this.photo = photo;
        this.linesOfResearch = linesOfResearch;
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
    public String getCategory() {
        return category;
    }

    /**
     * Método que permite asignar un valor al atributo category
     *
     * @param category Valor a ser asignado al atributo category
     */
    public void setCategory(String category) {
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
}
