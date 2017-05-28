package co.edu.uniquindio.android.electiva.giuq;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import co.edu.uniquindio.android.electiva.giuq.util.ManagerFireBase;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Controlador Aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class ControllerApplication extends Application{

   private ArrayList<Researcher>activeResearchers;
    private ArrayList<Researcher>pendingResearchers;
   private ManagerFireBase managerFireBase;


    @Override
    public void onCreate() {
        super.onCreate();
        activeResearchers= new ArrayList<>();
        pendingResearchers= new ArrayList<>();
        managerFireBase=ManagerFireBase.instance();

    }

    public void addResearcher(Researcher researcher)
    {
        pendingResearchers.add(researcher);
        managerFireBase.addResearcher(researcher);
    }
    
    public void addResearchGroup(ResearchGroup researchGroup){

        managerFireBase.addResearchGroup(researchGroup);

    }

    public void loadResearchers(Context context){
        managerFireBase.loadResearchers(context);
        activeResearchers=managerFireBase.getActiveResearchers();
        pendingResearchers=managerFireBase.getPendingResearchers();
    }

    public Researcher login(String email, String password){
       for(Researcher researcher :activeResearchers){
           Log.v("nombre",researcher.getName()+"");
           if(researcher.getEmail().equals(email)&&researcher.getPassword().equals(password)){
               return researcher;
           }
       }
       return null;
    }







}
