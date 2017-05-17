package co.edu.uniquindio.android.electiva.giuq;

import android.app.Application;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.giuq.util.ManagerFireBase;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Controlador Aplicación
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class ControllerApplication extends Application{


    ArrayList<Researcher>pending;
    ManagerFireBase managerFireBase;


    @Override
    public void onCreate() {
        super.onCreate();
        pending=new ArrayList<>();
        managerFireBase=ManagerFireBase.instance();

    }

    public void  addResearcher(Researcher researcher) {
        pending.add(researcher);
        managerFireBase.addResearcher(researcher);
    }



}
