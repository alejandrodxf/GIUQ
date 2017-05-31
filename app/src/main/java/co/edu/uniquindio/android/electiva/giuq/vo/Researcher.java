package co.edu.uniquindio.android.electiva.giuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Clase que representa un investigador
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

@IgnoreExtraProperties
 public class Researcher  extends User implements Parcelable {

    /**
     * Atributo que representa la nacionalidad de un investigador
     */
    private int nationality;
    /**
     * Atributo que representa el grupo de investigación al que pertenece un investigador
     */
    private String researchGroup;
    /**
     * Atributo que representa una lista de títulos obtenidos por un investigador
     */
    private ArrayList<AcademicTitle> academicTitles;

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
    public Researcher(String name, String email, String password, String urlCVLAC, int category, String photo, ArrayList<LineOfResearch> linesOfResearch, boolean state, int nationality, String researchGroup, ArrayList<AcademicTitle> academicTitles,boolean genre) {
        super(name, email, password, urlCVLAC, category, photo, linesOfResearch,state);
        this.nationality = nationality;
        this.researchGroup = researchGroup;
        this.academicTitles = academicTitles;
        this.genre=genre;
    }

    /**
     * Constructor utilizado para leer el Parcel
     * @param in parcel a leer
     */
    protected Researcher(Parcel in) {
        super(in);
        this.nationality = in.readInt();
        this.researchGroup = in.readString();
        this.academicTitles=in.createTypedArrayList(AcademicTitle.CREATOR);
        this.genre=in.readByte() != 0;
    }

    /**
     * Método encargado de crear el investigador con base al Parcel recibido,
     * también es necesario para enviar array para la lectura de arrays enviadas
     * por medio del Parcel
     */
    public static final Creator<Researcher> CREATOR = new Creator<Researcher>() {
        @Override
        public Researcher createFromParcel(Parcel in) {
            return new Researcher(in);
        }

        @Override
        public Researcher[] newArray(int size) {
            return new Researcher[size];
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
        super.writeToParcel(dest, flags);
        dest.writeInt(nationality);
        dest.writeString(researchGroup);
        dest.writeTypedList(academicTitles);
        dest.writeByte((byte) (genre ? 1 : 0));
    }


    /**
     * Método que permite obtener el valor del creator
     * @return El CREATOR
     */
    public static Creator<Researcher> getCREATOR() {
        return CREATOR;
    }

    /**
     * Constructor vacío requerido para utilizar Firebase
     */
    public Researcher(){

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
    public int getNationality() {
        return nationality;
    }

    /**
     * Método que permite asignar un valor al atributo nationality
     *
     * @param nationality Valor a ser asignado al atributo nationality
     */
    public void setNationality(int nationality) {
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
