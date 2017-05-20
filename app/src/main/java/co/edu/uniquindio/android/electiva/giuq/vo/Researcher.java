package co.edu.uniquindio.android.electiva.giuq.vo;

import java.util.ArrayList;

/**
 * Clase que representa un investigador
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class Researcher  extends User {

    /**
     * Atributo que representa la nacionalidad de un investigador
     */
    private String nationality;
    /**
     * Atributo que representa el grupo de investigación al que pertenece un investigador
     */
    private String researchGroup;
    /**
     * Atributo que representa una lista de títulos obtenidos por un investigador
     */
    private ArrayList<AcademicTitle> academicTitles;
    /**
     * Atributo que representa si el investigador esta activo o inactivo
     */
    private boolean state;

    /**
     * Atributo que representa el género de un investigador
     * true masculino
     * false femenino
     */
    private boolean genre;
    /**
     * Método constructor de la clase
     * @param name nombre del investigador
     * @param email email del investigador
     * @param password contraseña del investigador
     * @param urlCVLAC url del CVLAC del investigador
     * @param category categoría del investigador
     * @param photo imagen de perfil del investigador
     * @param linesOfResearch lineas de investigación del investigador
     * @param nationality nacionalidad del investigador
     * @param researchGroup grupo de investigación del investigador
     * @param academicTitles titulos obtenidos por el investigador
     */



    public Researcher(String name, String email, String password, String urlCVLAC, String category, String photo, ArrayList<LineOfResearch> linesOfResearch, String nationality, String researchGroup, ArrayList<AcademicTitle> academicTitles, boolean state,boolean genre) {
        super(name, email, password, urlCVLAC, category, photo, linesOfResearch);
        this.nationality = nationality;
        this.researchGroup = researchGroup;
        this.academicTitles = academicTitles;
        this.state=state;
        this.genre=genre;
    }


    /**
     * Método utilizado para gestionar el ingreso de un investigador a la aplicación
     * @return true si el investigador ingreso correctamente
     */
    @Override
    public boolean signInUser() {
        return false;
    }

    /**
     * Método utilizado para gestionar el registro de un investigador a la aplicación
     * @return true si el investigador se registro correctamente
     */
    @Override
    public boolean signUpUser() {
        return false;
    }

    /**
     * Método que permite obtener el valor del atributo nationality
     *
     * @return El valor del atributo nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Método que permite asignar un valor al atributo nationality
     *
     * @param nationality Valor a ser asignado al atributo nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Método que permite obtener el valor del atributo researchGroup
     *
     * @return El valor del atributo researchGroup
     */
    public String getResearchGroup() {
        return researchGroup;
    }

    /**
     * Método que permite asignar un valor al atributo researchGroup
     *
     * @param researchGroup Valor a ser asignado al atributo researchGroup
     */
    public void setResearchGroup(String researchGroup) {
        this.researchGroup = researchGroup;
    }

    /**
     * Método que permite obtener el valor del atributo academicTitles
     *
     * @return El valor del atributo academicTitles
     */
    public ArrayList<AcademicTitle> getAcademicTitles() {
        return academicTitles;
    }

    /**
     * Método que permite asignar un valor al atributo academicTitles
     *
     * @param academicTitles Valor a ser asignado al atributo academicTitles
     */
    public void setAcademicTitles(ArrayList<AcademicTitle> academicTitles) {
        this.academicTitles = academicTitles;
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

    /**
     * Método que permite obtener el valor del atributo genre
     *
     * @return El valor del atributo genre
     */
    public boolean isGenre() {
        return genre;
    }

    /**
     * Método que permite asignar un valor al atributo genre
     *
     * @param genre Valor a ser asignado al atributo genre
     */
    public void setGenre(boolean genre) {
        this.genre = genre;
    }
}
