package co.edu.uniquindio.android.electiva.giuq.vo;

import android.media.Image;

import java.util.ArrayList;

/**
 * Clase que representa un grupo de investigación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class ResearchGroup extends User {

    /**
     * Atributo que representa las siglas de un grupo de investigación
     */
    private String acronym;
    /**
     * Atributo que representa el lider de un grupo de investigación
     */
    private Researcher leader;
    /**
     * Atributo que representa la lista de investigadores de un grupo de investigación
     */
    private ArrayList<Researcher> researchers;

    public ResearchGroup(String name, String email, String password, String urlCVLAC, String category, Image photo, ArrayList<LineOfResearch> linesOfResearch, String acronym, Researcher leader, ArrayList<Researcher> researchers) {
        super(name, email, password, urlCVLAC, category, photo, linesOfResearch);
        this.acronym = acronym;
        this.leader = leader;
        this.researchers = researchers;
    }

    /**
     * Método utilizado para gestionar el ingreso de un grupo de investigación a la aplicación
     * @return true si el grupo de investigación ingreso correctamente
     */
    @Override
    public boolean signInUser() {
        return false;
    }

    /**
     * Método utilizado para gestionar el registro de un grupo de investigación a la aplicación
     * @return true si el grupo de investigación se registro correctamente
     */
    @Override
    public boolean signUpUser() {
        return false;
    }

    /**
     * Método que permite obtener el valor del atributo acronym
     *
     * @return El valor del atributo acronym
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Método que permite asignar un valor al atributo acronym
     *
     * @param acronym Valor a ser asignado al atributo acronym
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    /**
     * Método que permite obtener el valor del atributo leader
     *
     * @return El valor del atributo leader
     */
    public Researcher getLeader() {
        return leader;
    }

    /**
     * Método que permite asignar un valor al atributo leader
     *
     * @param leader Valor a ser asignado al atributo leader
     */
    public void setLeader(Researcher leader) {
        this.leader = leader;
    }

    /**
     * Método que permite obtener el valor del atributo ic_action_researcher
     *
     * @return El valor del atributo researchers
     */
    public ArrayList<Researcher> getResearchers() {
        return researchers;
    }

    /**
     * Método que permite asignar un valor al atributo researchers
     *
     * @param researchers Valor a ser asignado al atributo researchers
     */
    public void setResearchers(ArrayList<Researcher> researchers) {
        this.researchers = researchers;
    }
}

