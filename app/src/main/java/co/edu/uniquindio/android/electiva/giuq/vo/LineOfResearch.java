package co.edu.uniquindio.android.electiva.giuq.vo;

/**
 * Clase que representa una linea de investigación
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
public class LineOfResearch {

    /**
     * Atributo que representa una línea de investigación
     */
    private String lineOfResearch;
    /**
     * Atributo que representa si el investigador todavía trabaja en esa línea de investigación
     */
    private Boolean state;

    /**
     * Método constructor de la clase
     * @param lineOfResearch línea de investigación
     * @param state línea de investigación true si esta activa y false si esta inactiva
     */
    public LineOfResearch(String lineOfResearch, Boolean state) {
        this.lineOfResearch = lineOfResearch;
        this.state = state;
    }

    /**
     * Método que permite obtener el valor del atributo lineOfResearch
     *
     * @return El valor del atributo lineOfResearch
     */
    public String getLineOfResearch() {
        return lineOfResearch;
    }

    /**
     * Método que permite asignar un valor al atributo lineOfResearch
     *
     * @param lineOfResearch Valor a ser asignado al atributo lineOfResearch
     */
    public void setLineOfResearch(String lineOfResearch) {
        this.lineOfResearch = lineOfResearch;
    }

    /**
     * Método que permite obtener el valor del atributo state
     *
     * @return El valor del atributo state
     */
    public Boolean getstate() {
        return state;
    }

    /**
     * Método que permite asignar un valor al atributo state
     *
     * @param state Valor a ser asignado al atributo state
     */
    public void setstate(Boolean state) {
        this.state = state;
    }
}
