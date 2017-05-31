package co.edu.uniquindio.android.electiva.giuq;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import co.edu.uniquindio.android.electiva.giuq.util.ManagerFireBase;
import co.edu.uniquindio.android.electiva.giuq.util.Validations;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;
import co.edu.uniquindio.android.electiva.giuq.vo.User;

/**
 * Controlador Aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 *
 */

public class ControllerApplication extends Application {

    /**
     * Atributo que representa la lista de investigadores activos
     */
    private ArrayList<Researcher> activeResearchers;
    /**
     * Atributo que representa la lista de investigadores pendientes de ser aprobados
     */
    private ArrayList<Researcher> pendingResearchers;
    /**
     * Atributo que representa la lista de grupos de investigación activos
     */
    private ArrayList<ResearchGroup> activeResearchGroups;
    /**
     * Atributo que representa la lista de grupos de investigación pendientes de ser aprobados
     */
    private ArrayList<ResearchGroup> pendingResearchGroups;
    /**
     * Atributo que hace la conexión con la base de datos
     */
    private ManagerFireBase managerFireBase;

    private Boolean loadDatabase;


    /**
     * Método que se encarga de inicializar las variables principales de la aplicación
     */
    @Override
    public void onCreate() {
        super.onCreate();
        loadDatabase=false;
        activeResearchers = new ArrayList<>();
        pendingResearchers = new ArrayList<>();
        activeResearchGroups = new ArrayList<>();
        pendingResearchGroups = new ArrayList<>();
        managerFireBase = ManagerFireBase.instance();

    }

    /**
     * Método encargado de agregar un investigador a la base de datos
     * @param researcher investigador a ser agregado
     */
    public void addResearcher(Researcher researcher) {
        pendingResearchers.add(researcher);
        managerFireBase.addResearcher(researcher);
    }

    /**
     * Método encargado de agregar un grupo de investigación a la base de datos
     * @param researchGroup grupo de investigación a ser agregado
     */
    public void addResearchGroup(ResearchGroup researchGroup) {
        pendingResearchGroups.add(researchGroup);
        managerFireBase.addResearchGroup(researchGroup);

    }

    /**
     * Método encargado de cargar los usuarios de la base de datos
     * @param context
     */
    public void loadUsers(Context context) {
        managerFireBase.loadResearchers(context);
        managerFireBase.loadResearchGroups(context);
        activeResearchers = managerFireBase.getActiveResearchers();
        pendingResearchers = managerFireBase.getPendingResearchers();
        activeResearchGroups=managerFireBase.getActiveResearchGroups();
        pendingResearchGroups=managerFireBase.getPendingResearchGroups();
        loadDatabase=true;
    }

    /**
     * Método encargado de loguear un investigador
     * @param email email investigador
     * @param password password investigador
     * @return null si no se logro loguear de lo contrario retorna el investigador
     */
    public Researcher loginResearcher(String email, String password) {
        for (Researcher researcher : activeResearchers) {
            if (researcher.getEmail().equals(email) && researcher.getPassword().equals(password)) {
                return researcher;
            }
        }
        return null;
    }

    /**
     * Método encargado de loguear un grupo de investigación
     * @param email email grupo de investigación
     * @param password password grupo de investigación
     * @return null si no se logro loguear, de lo contrario retorna el grupo de investigación
     */
    public ResearchGroup loginResearchGroup(String email,String password){
        for (ResearchGroup researchGroup: activeResearchGroups) {
            if (researchGroup.getEmail().equals(email) && researchGroup.getPassword().equals(password)) {
                return researchGroup;
            }
        }
        return null;
    }

    /**
     * Método encargado de verificar si ya existe un correo electrónico en la base de datos
     * @param email email a ser verificado
     * @return true si ya existe el correo en la base de datos de lo contrario false
     */
    public boolean searchEmail(String email) {
        if (searchEmailResearcher(email, activeResearchers)) {
            return true;
        } else if (searchEmailResearcher(email, pendingResearchers)) {
            return true;
        }else if(searchEmailResearchGroup(email,activeResearchGroups)){
            return true;
        }else if(searchEmailResearchGroup(email,pendingResearchGroups)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Método encargado de verificar si ya existe un correo electrónico en alguna de las listas de los grupos de investigación
     * @param email email a verificar
     * @param researchGroups lista de grupos de investigación
     * @return true si ya el correo en la base de datos de lo contrario false
     */
    public boolean searchEmailResearchGroup(String email,ArrayList<ResearchGroup>researchGroups){
        for(ResearchGroup researchGroup:researchGroups){
            if(researchGroup.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    /**
     * Método encargado de verificar si ya existe un correo electrónico en alguna de las listas de investigadores
     * @param email email a verificar
     * @param researchers lista de investigadores
     * @return true si ya el correo en la base de datos de lo contrario false
     */
    public boolean searchEmailResearcher(String email, ArrayList<Researcher> researchers) {
        for (Researcher researcher :researchers) {
            if (researcher.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método utilizado para obtener los nombres de los investigadores activos
     * @return lista de nombres de los investigadores activos
     */
    public String [] namesResearchers(){
        String [] names = new String [activeResearchers.size()];
        for(int i=0; i<activeResearchers.size();i++){
            names[i]=activeResearchers.get(i).getName()+"\n("+activeResearchers.get(i).getResearchGroup()+")";
        }
        return names;
    }

    /**
     * Método utilizado para buscar por grupo de investigación, investigador, o por línea de investigación
     * @param nameResearcher nombre investigador
     * @param nameResearchGroup nombres grupo de investigación
     * @param lineOfResearch línea de investigación
     * @return lista de usuarios encontrados que cumplen con las condiciones enviadas por parámetros
     */
    public ArrayList<User> search(String nameResearcher,String nameResearchGroup,String lineOfResearch){
        ArrayList<User> users = new ArrayList<>();
        boolean validateNameResearcher,validateNameResearchGroup,validateLineOfResearch;
        validateNameResearcher=Validations.validateFields(nameResearcher);
        validateNameResearchGroup=Validations.validateFields(nameResearchGroup);
        validateLineOfResearch=Validations.validateFields(lineOfResearch);
        if(validateNameResearcher||validateLineOfResearch){
           users=searchResearcherByNameOrLineOfResearch(nameResearcher,lineOfResearch,users,validateNameResearcher,validateLineOfResearch);
        }
        if(validateNameResearchGroup||validateLineOfResearch){
          users=searchResearchGroupByNameOrLineOfResearch(nameResearchGroup,lineOfResearch,users,validateNameResearchGroup,validateLineOfResearch);
        }

        return users;
    }

    /**
     * Método utilizado para buscar por investigador, o por línea de investigación
     * @param nameResearcher nombre investigador
     * @param lineOfResearch línea de investigación
     * @return lista de usuarios encontrados que cumplen con las condiciones enviadas por parámetros
     */
    public ArrayList<User> searchResearcherByNameOrLineOfResearch(String nameResearcher,String lineOfResearch,ArrayList<User> users,boolean validateName,boolean validateLine){
        for(Researcher researcher : activeResearchers){
            if(validateName&&researcher.getName().contains(nameResearcher)){
                users.add(researcher);
            }
            if (validateLine){
                for(LineOfResearch lineOfResearchT : researcher.getLinesOfResearch()){
                    if(lineOfResearchT.getLineOfResearch().contains(lineOfResearch)){
                        users.add(researcher);
                        break;
                    }
                }
            }
        }
        return users;
    }

    /**
     * Método utilizado para buscar por grupo de investigación, o por línea de investigación
     * @param nameResearchGroup nombre grupo de investigación
     * @param lineOfResearch línea de investigación
     * @return lista de usuarios encontrados que cumplen con las condiciones enviadas por parámetros
     */
    public ArrayList<User> searchResearchGroupByNameOrLineOfResearch(String nameResearchGroup,String lineOfResearch,ArrayList<User> users,boolean validateName,boolean validateLine){
        for(ResearchGroup researchGroup : activeResearchGroups){
            if(validateName&&researchGroup.getName().contains(nameResearchGroup)){
                users.add(researchGroup);
            }
            if (validateLine){
                for(LineOfResearch lineOfResearchT : researchGroup.getLinesOfResearch()){
                    if(lineOfResearchT.getLineOfResearch().contains(lineOfResearch)){
                        users.add(researchGroup);
                        break;
                    }
                }
            }
        }
        return users;
    }

    /**
     * Método que permite obtener el valor del atributo activeResearchers
     *
     * @return El valor del atributo activeResearchers
     */
    public ArrayList<Researcher> getActiveResearchers() {
        return activeResearchers;
    }

    /**
     * Método que permite obtener el valor del atributo activeResearchGroups
     *
     * @return El valor del atributo activeResearchGroups
     */
    public ArrayList<ResearchGroup> getActiveResearchGroups() {
        return activeResearchGroups;
    }

    /**
     * Método que permite obtener el valor del atributo loadDatabase
     *
     * @return El valor del atributo loadDatabase
     */
    public Boolean getLoadDatabase() {
        return loadDatabase;
    }

    /**
     * Método que permite asignar un valor al atributo loadDatabase
     *
     * @param loadDatabase Valor a ser asignado al atributo loadDatabase
     */
    public void setLoadDatabase(Boolean loadDatabase) {
        this.loadDatabase = loadDatabase;
    }
}
