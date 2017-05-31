package co.edu.uniquindio.android.electiva.giuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Clase que representa un grupo de investigación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

@IgnoreExtraProperties
public class ResearchGroup extends User implements Parcelable {

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

    /**
     * Método constuctor de la clase
     * @param name nombre grupo de investigación
     * @param email email grupo de investigación
     * @param password contraseña grupo de investigación
     * @param urlCVLAC urlCVLAC grupo de investigación
     * @param category categoría grupo de investigación
     * @param photo logo grupo de investigación
     * @param linesOfResearch líneas de investigación grupo de investigación
     * @param state estad grupo de investigación
     * @param acronym siglas grupo de investigación
     * @param leader lider grupo de investigación
     * @param researchers integrantes grupo de investigación
     */
    public ResearchGroup(String name, String email, String password, String urlCVLAC, int category, String photo, ArrayList<LineOfResearch> linesOfResearch,Boolean state, String acronym, Researcher leader, ArrayList<Researcher> researchers) {
        super(name, email, password, urlCVLAC, category, photo, linesOfResearch,state);
        this.acronym = acronym;
        this.leader = leader;
        this.researchers = researchers;
    }

    /**
     * Constructor vacío requerido para utilizar Firebase
     */
    public ResearchGroup(){

    }

    /**
     * Constructor utilizado para leer el Parcel
     * @param in parcel a leer
     */
    protected ResearchGroup(Parcel in) {
        super(in);
        this.acronym = in.readString();
        this.leader = in.readParcelable(Researcher.class.getClassLoader());
        this.researchers=in.createTypedArrayList(Researcher.CREATOR);

    }


    /**
     * Método encargado de crear el grupo de investigación con base al Parcel recibido,
     * también es necesario para enviar array para la lectura de arrays enviadas
     * por medio del Parcel
     */
    public static final Creator<ResearchGroup> CREATOR = new Creator<ResearchGroup>() {
        @Override
        public ResearchGroup createFromParcel(Parcel in) {
            return new ResearchGroup(in);
        }

        @Override
        public ResearchGroup[] newArray(int size) {
            return new ResearchGroup[size];
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
        dest.writeString(acronym);
        dest.writeParcelable(leader, flags);
        dest.writeTypedList(researchers);
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

