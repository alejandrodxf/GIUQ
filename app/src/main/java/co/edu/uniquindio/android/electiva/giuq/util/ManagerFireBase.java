package co.edu.uniquindio.android.electiva.giuq.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Clase utilizada para la conexión con la base de datos
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class ManagerFireBase {

    private DatabaseReference database;
    private static ManagerFireBase managerFireBase;
    private ArrayList<Researcher>activeResearchers;
    private ArrayList<Researcher>pendingResearchers;
    private ArrayList<Researcher>researchers;


    private ManagerFireBase(){
        database= FirebaseDatabase.getInstance().getReference();
        activeResearchers = new ArrayList<>();
        pendingResearchers  = new ArrayList<>();
        researchers=new ArrayList<>();
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



   public void  loadResearchers(Context context){
       final ProgressDialog progress = ProgressDialog.show(context,"Cargando","Espere", true);
       database.child("researchers").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               activeResearchers.clear();
               pendingResearchers.clear();
               for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   Researcher researcher = postSnapshot.getValue(Researcher.class);
                   Log.d("investigador",researcher.getName()+"");
                   if(researcher.isState()){
                      activeResearchers.add(researcher);
                       Log.d("activos",researcher.getName()+"");
                   }else{
                      pendingResearchers.add(researcher);
                       Log.d("pendientes",researcher.getName()+"");
                   }
               }
               progress.dismiss();
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {
           }
       });
   }



    /**
     * Método utilizado para agregar investigador a la base de datos
     * @param researcher investigador a ser agregado
     */
    public void addResearcher(Researcher researcher){
        pendingResearchers.add(researcher);
        database.child("researchers").push().setValue(researcher);
    }

    public ArrayList<Researcher> getActiveResearchers() {
        return activeResearchers;
    }

    public ArrayList<Researcher> getPendingResearchers() {
        return pendingResearchers;
    }
}
