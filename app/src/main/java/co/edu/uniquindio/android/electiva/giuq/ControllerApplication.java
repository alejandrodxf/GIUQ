package co.edu.uniquindio.android.electiva.giuq;

import android.app.Application;

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


   private ArrayList<Researcher>pendingResearcher;
   private ArrayList<ResearchGroup>pendingResearchGroup;
   private ManagerFireBase managerFireBase;




    @Override
    public void onCreate() {
        super.onCreate();
        pendingResearcher=new ArrayList<>();
        pendingResearchGroup=new ArrayList<>();
        managerFireBase=ManagerFireBase.instance();

    }



    public void  addResearcher(Researcher researcher) {
        pendingResearcher.add(researcher);
        managerFireBase.addResearcher(researcher);
    }
    
    public void addResearchGroup(ResearchGroup researchGroup){
        pendingResearchGroup.add(researchGroup);
        managerFireBase.addResearchGroup(researchGroup);

    }



}
