package co.edu.uniquindio.android.electiva.giuq.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class ManagerFireBase {

    private DatabaseReference database;
    private static ManagerFireBase managerFireBase;

    private ManagerFireBase(){

        database= FirebaseDatabase.getInstance().getReference();

    }

    public static ManagerFireBase instance (){

        if(managerFireBase == null){

            managerFireBase= new ManagerFireBase();

        }

        return managerFireBase;

    }

    public void addResearcher(Researcher researcher){

        database.push().setValue(researcher);

    }
}
