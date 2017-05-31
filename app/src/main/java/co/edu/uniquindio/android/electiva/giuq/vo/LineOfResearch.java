package co.edu.uniquindio.android.electiva.giuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Clase que representa una linea de investigación
 *
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */
@IgnoreExtraProperties
public class LineOfResearch implements Parcelable {

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
     * Constructor vacío requerido para utilizar Firebase
     */
    public LineOfResearch(){

    }

    /**
     * Constructor utilizado para leer el Parcel
     * @param in parcel a leer
     */
    protected LineOfResearch(Parcel in) {
        lineOfResearch = in.readString();
        state = in.readByte() != 0;
    }

    /**
     * Método encargado de crear la línea de investigación con base al Parcel recibido,
     * también es necesario para enviar array para la lectura de arrays enviadas
     * por medio del Parcel
     */
    public static final Creator<LineOfResearch> CREATOR = new Creator<LineOfResearch>() {
        @Override
        public LineOfResearch createFromParcel(Parcel in) {
            return new LineOfResearch(in);
        }

        @Override
        public LineOfResearch[] newArray(int size) {
            return new LineOfResearch[size];
        }
    };

    /**
     * Método que se usa cuando existen parcelables hijos
     *
     * @return retorna cero al no tener hijos
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Método que permite escribir un parcel.
     * @param dest  Parcel donde se va escribir
     * @param flags Indica como deberia ser escrito el parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lineOfResearch);
        dest.writeByte((byte) (state ? 1 : 0));
    }

    /**
     * Método que permite obtener el valor del creator
     * @return El CREATOR
     */
    public static Creator<LineOfResearch> getCREATOR() {
        return CREATOR;
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
