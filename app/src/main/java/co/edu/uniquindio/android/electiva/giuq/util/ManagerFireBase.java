package co.edu.uniquindio.android.electiva.giuq.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Clase utuilizada para la conexión con la base de datos
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

    /**
     * Método utilizado para agregar grupos de investigación a la base de datos
     * @param researchGroup grupo de investigación a ser agregado
     */
    public void addResearchGroup(ResearchGroup researchGroup){
        database.push().setValue(researchGroup);
    }

  /*database.child("Researchers").addListenerForSingleValueEvent(
              new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {
                      for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                          String name = (String) messageSnapshot.child("name").getValue();
                          names.add(name);
                      }
                  }

                  @Override
                  public void onCancelled(DatabaseError databaseError) {
                      //handle databaseError
                  }
              });
      return names;
  }*/


    /**
     * Método utilizado para agregar investigador a la base de datos
     * @param researcher investigador a ser agregado
     */
    public void addResearcher(Researcher researcher){
        database.child("Researchers").push().setValue(researcher);
    }
}
