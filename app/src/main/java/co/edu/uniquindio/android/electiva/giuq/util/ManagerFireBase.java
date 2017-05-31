package co.edu.uniquindio.android.electiva.giuq.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

/**
 * Clase utilizada para la conexión con la base de datos
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class ManagerFireBase {

    /**
     * Atributo que representa referencia a la base de datos
     */
    private DatabaseReference database;

    /**
     * Instancia de la clase
     */
    private static ManagerFireBase managerFireBase;

    /**
     * Atributo que representa la lista de investigadores activos
     */
    private ArrayList<Researcher>activeResearchers;

    /**
     * Atributo que representa la lista de investigadores pendientes por agregar
     */
    private ArrayList<Researcher>pendingResearchers;

    /**
     * Atributo que representa la lista de grupos de investigación activos
     */
    private ArrayList<ResearchGroup>activeResearchGroups;

    /**
     * Atributo que representa la lista de grupos de investigación pendientes por agregar
     */
    private ArrayList<ResearchGroup>pendingResearchGroups;


    /**
     * Método constructor de la clase
     */
    private ManagerFireBase(){
        database= FirebaseDatabase.getInstance().getReference();
        activeResearchers = new ArrayList<>();
        pendingResearchers  = new ArrayList<>();
        activeResearchGroups = new ArrayList<>();
        pendingResearchGroups  = new ArrayList<>();
    }

    /**
     * Método que permite crear una nueva instancia
     * @return instancia
     */
    public static ManagerFireBase instance (){
        if(managerFireBase == null){
            managerFireBase= new ManagerFireBase();
        }
        return managerFireBase;
    }


    /**
     * Método utilizado para cargar los investigadores
     * @param context contexto de la aplicación
     */
   public void  loadResearchers(Context context){
       final ProgressDialog progress = ProgressDialog.show(context,context.getResources().getString(R.string.loading),context.getResources().getString(R.string.wait), true);
       database.child("researchers").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               activeResearchers.clear();
               pendingResearchers.clear();
               for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   Researcher researcher = postSnapshot.getValue(Researcher.class);
                   if(researcher.isState()){
                      activeResearchers.add(researcher);
                   }else{
                      pendingResearchers.add(researcher);
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
     * Método utilizado para cargar los grupos de investigación
     * @param context contexto de la aplicación
     */
   public void loadResearchGroups(Context context){
       final ProgressDialog progress = ProgressDialog.show(context,context.getResources().getString(R.string.loading),context.getResources().getString(R.string.wait), true);
       database.child("researchgroups").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               activeResearchGroups.clear();
               pendingResearchGroups.clear();
               for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   ResearchGroup researchGroup = postSnapshot.getValue(ResearchGroup.class);
                   if(researchGroup.isState()){
                       activeResearchGroups.add(researchGroup);
                   }else{
                       pendingResearchGroups.add(researchGroup);
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
     * Método utilizado para agregar un investigador a la base de datos
     * @param researcher investigador a ser agregado
     */
    public void addResearcher(Researcher researcher){
        pendingResearchers.add(researcher);
        database.child("researchers").push().setValue(researcher);
    }

    /**
     * Método utilizado para agregar un grupo de investigación a la base de datos
     * @param researchGroup grupo de investigación a ser agregado
     */
    public void addResearchGroup(ResearchGroup researchGroup){
        pendingResearchGroups.add(researchGroup);
        database.child("researchgroups").push().setValue(researchGroup);
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
     * Método que permite obtener el valor del atributo pendingResearchers
     *
     * @return El valor del atributo pendingResearchers
     */
    public ArrayList<Researcher> getPendingResearchers() {
        return pendingResearchers;
    }

    /**
     * Método que permite obtener el valor del atributo activeResearchGroup
     *
     * @return El valor del atributo activeResearchGroup
     */
    public ArrayList<ResearchGroup> getActiveResearchGroups() {
        return activeResearchGroups;
    }

    /**
     * Método que permite obtener el valor del atributo pendingResearchGroups
     *
     * @return El valor del atributo pendingResearchGroups
     */
    public ArrayList<ResearchGroup> getPendingResearchGroups() {
        return pendingResearchGroups;
    }
}
