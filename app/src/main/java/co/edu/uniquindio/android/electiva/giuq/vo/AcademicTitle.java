package co.edu.uniquindio.android.electiva.giuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Clase que representa un título obtenido por un investigador
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

@IgnoreExtraProperties
public class AcademicTitle implements Parcelable {

    /**
     * Atributo que representa un título
     */
    private String academicTitle;
    /**
     * Atributo que representa una institución donde se cursaron los estudios
     */
    private String institution;
    /**
     * Atributo que representa una fecha de finalización de estudios
     */
    private Date dateEnd;

    /**
     * Método constructor de la clase
     * @param academicTitle título obtenido por el investigador
     * @param institution  institución en la que curso sus estudios
     * @param dateEnd      fecha de finalización de los estudios
     */
    public AcademicTitle(String academicTitle, String institution, Date dateEnd) {
        this.academicTitle = academicTitle;
        this.institution = institution;
        this.dateEnd = dateEnd;
    }

    /**
     * Constructor vacio utilizado para
     */
    public AcademicTitle(){

    }
    protected AcademicTitle(Parcel in) {
        academicTitle = in.readString();
        institution = in.readString();
        dateEnd = new Date(in.readLong());
    }

    /**
     * Método encargado de crear al título académico con base al Parcel recibido,
     * también es necesario para enviar array para la lectura de arrays enviadas
     * por medio del Parcel
     */
    public static final Creator<AcademicTitle> CREATOR = new Creator<AcademicTitle>() {
        @Override
        public AcademicTitle createFromParcel(Parcel in) {
            return new AcademicTitle(in);
        }

        @Override
        public AcademicTitle[] newArray(int size) {
            return new AcademicTitle[size];
        }
    };
    /**
     * Método que permite obtener el valor del atributo academicTitle
     *
     * @return El valor del atributo academicTitle
     */
    public String getAcademicTitle() {
        return academicTitle;
    }

    /**
     * Método que permite asignar un valor al atributo academicTitle
     *
     * @param academicTitle Valor a ser asignado al atributo academicTitle
     */
    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    /**
     * Método que permite obtener el valor del atributo institution
     *
     * @return El valor del atributo institution
     */
    public String getinstitution() {
        return institution;
    }

    /**
     * Método que permite asignar un valor al atributo institution
     *
     * @param institution Valor a ser asignado al atributo institution
     */
    public void setinstitution(String institution) {
        this.institution = institution;
    }

    /**
     * Método que permite obtener el valor del atributo dateEnd
     *
     * @return El valor del atributo dateEnd
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Método que permite asignar un valor al atributo dateEnd
     *
     * @param dateEnd Valor a ser asignado al atributo dateEnd
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

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
        dest.writeString(academicTitle);
        dest.writeString(institution);
        dest.writeLong(dateEnd.getTime());
    }

    /**
     * Método que permite obtener el valor del creator
     * @return El CREATOR
     */
    public static Creator<AcademicTitle> getCREATOR() {
        return CREATOR;
    }
}
