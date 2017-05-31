package co.edu.uniquindio.android.electiva.giuq.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearchGroupActivity;
import co.edu.uniquindio.android.electiva.giuq.activities.NewResearcherActivity;
import co.edu.uniquindio.android.electiva.giuq.activities.ProfileActivity;
import co.edu.uniquindio.android.electiva.giuq.vo.AcademicTitle;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

import static co.edu.uniquindio.android.electiva.giuq.R.string.active;
import static co.edu.uniquindio.android.electiva.giuq.fragments.AcademicTitleFragment.ACADEMIC_TITLE;
import static co.edu.uniquindio.android.electiva.giuq.fragments.LineOfResearchFragment.LINE_OF_RESEARCH;
import static co.edu.uniquindio.android.electiva.giuq.fragments.ListResearchGroupsFragment.LIST_OF_RESEARCHGROUPS;
import static co.edu.uniquindio.android.electiva.giuq.fragments.ListResearchersFragment.LIST_OF_RESEARCHERS;
import static co.edu.uniquindio.android.electiva.giuq.fragments.MembersResearchGroupFragment.MEMBERS_RESEARCH_GROUP;
import static co.edu.uniquindio.android.electiva.giuq.fragments.ListUsersSearchFragment.LIST_USERS;


/**
 * Adaptador de listas
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.AdapterRecyclerViewViewHolder>{

    /**
     * Atributo que representa el contexto
     */
    private static  Context context;
    /**
     * Atributo que representa una lista de items
     */
    private List items;
    /**
     * Atributo que representa el tipo, es decir, el fragmento donde va a ser desplegada la lista
     */
    private String type;

    /**
     * Método constructor del adaptador
     * @param items lista de items
     * @param fragment fragmento
     * @param type tipo de vista
     */
    public AdapterRecyclerView(List items, Fragment fragment,String type) {
        this.items = items;
        this.type =type;
    }


    /**
     * Se llama cuando se necesita un nuevo RecyclerView
     * @param parent El ViewGroup en la que se añadirá la nueva vista después de que se une a una posición adaptador.
     * @param viewType El tipo de vista de la nueva vista.
     * @return Un nuevo ViewHolder
     */
    @Override
    public AdapterRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        context=parent.getContext();
        switch (type){
            case LINE_OF_RESEARCH:{
               view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_of_research_card, parent, false);
                break;
            }
            case ACADEMIC_TITLE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.academic_title_card, parent, false);
                break;
            }
            case MEMBERS_RESEARCH_GROUP:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.members_research_group_card, parent, false);
                break;
            }
            case LIST_USERS:
            case LIST_OF_RESEARCHGROUPS:
            case LIST_OF_RESEARCHERS:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_image_card, parent, false);
                break;
            }
        }
        return new AdapterRecyclerViewViewHolder(view);

    }

    /**
     * Llamado por RecyclerView para mostrar los datos en la posición especificada.
     * @param holder El ViewHolder que debe ser actualizado para representar el contenido del elemento en la posición de la lista
     * @param position La posición del elemento dentro de la lista
     */
    @Override
    public void onBindViewHolder(AdapterRecyclerViewViewHolder holder, int position) {
        switch (type){
            case LINE_OF_RESEARCH:{
                LineOfResearch lineOfResearch = (LineOfResearch) items.get(position);
                holder.binItem(lineOfResearch);
                break;
            }
            case ACADEMIC_TITLE:{
                AcademicTitle academicTitle = (AcademicTitle) items.get(position);
                holder.binItem(academicTitle);
                break;
            }
            case MEMBERS_RESEARCH_GROUP:{
                Researcher researcher= (Researcher)items.get(position);
                holder.binItem(researcher,false);
                break;
            }
            case LIST_OF_RESEARCHERS:{
                Researcher researcher= (Researcher)items.get(position);
                holder.binItem(researcher,true);
                break;
            }
            case LIST_OF_RESEARCHGROUPS:{
                ResearchGroup researchGroup= (ResearchGroup) items.get(position);
                holder.binItem(researchGroup);
                break;
            }
            case LIST_USERS:{
                if(items.get(position) instanceof Researcher){
                    Researcher researcher= (Researcher)items.get(position);
                    holder.binItem(researcher,true);
                }else{
                    ResearchGroup researchGroup= (ResearchGroup) items.get(position);
                    holder.binItem(researchGroup);
                }
            }
        }


    }

    /**
     * Método encargado de devolver el número de elementos que se encuentran en el adaptador
     * @return número total de elementos del adaptador
     */
    @Override
    public int getItemCount() {
        return items.size();
    }


    /**
     * Clase utilizada para representar la vista de los elementos del adaptador
     */
    public static class AdapterRecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item
        public ImageView imageViewUserImageCard;
        public TextView textViewNameUserImageCard;
        public TextView textViewLinesOfResearchUserImageCard;
        public TextView textViewLineOfResearch;
        public TextView textViewactive;
        public TextView textViewAcademicTitle;
        public TextView textViewInstitution;
        public TextView textViewGraduationDate;
        public TextView textViewResearcherMember;
        public TextView textViewCategoryResearcherMember;
        public String stateTrue;
        public String stateFalse;

        /**
         * Método constructor de la vista
         * @param v vista que gestiona el evento
         */
        public AdapterRecyclerViewViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            switch (v.getId()){
                case R.id.line_of_research_card:{
                    textViewLineOfResearch = (TextView) v.findViewById(R.id.textViewLineOfResearch);
                    textViewactive = (TextView) v.findViewById(R.id.textViewActiveLineOfResearch);
                    stateTrue = v.getResources().getString(active);
                    stateFalse =v.getResources().getString(R.string.inactive);
                    break;
                }
                case R.id.academic_title_card:{
                    textViewAcademicTitle = (TextView) v.findViewById(R.id.textViewAcademicTitle);
                    textViewInstitution = (TextView)v.findViewById(R.id.textViewInstitution);
                    textViewGraduationDate=(TextView)v.findViewById(R.id.textViewGraduationDate);
                    break;
                }
                case R.id.members_research_group_card:{
                    textViewResearcherMember=(TextView) v.findViewById(R.id.textViewResearcherMember);
                    textViewCategoryResearcherMember=(TextView) v.findViewById(R.id.textViewCategoryResearcherMember);
                    break;
                }

                case R.id.user_image_card:{
                    imageViewUserImageCard= (ImageView) v.findViewById(R.id.imageViewUserImageCard);
                    textViewNameUserImageCard=(TextView) v.findViewById(R.id.textViewNameUserImageCard);
                    textViewLinesOfResearchUserImageCard=(TextView)v.findViewById(R.id.textViewLinesOfResearchUserImageCard);
                break;
                }

            }

        }

        public void binItem(Researcher researcher,boolean image){
            if(!image){
                textViewResearcherMember.setText(researcher.getName());
                textViewCategoryResearcherMember.setText(context.getResources().getStringArray(R.array.category_researcher_array)[researcher.getCategory()]);
            }else {
                String text="";
                textViewNameUserImageCard.setText(researcher.getName());
                if(researcher.getLinesOfResearch()!=null) {
                    for(int i=0; i<researcher.getLinesOfResearch().size();i++) {
                        text=text+researcher.getLinesOfResearch().get(i).getLineOfResearch()+"\n";
                    }
                    textViewLinesOfResearchUserImageCard.setText(text);
                }
            }

        }


        public void binItem(ResearchGroup researchGroup){
            String text="";
            textViewNameUserImageCard.setText(researchGroup.getAcronym());
            if(researchGroup.getLinesOfResearch()!=null) {
                for(int i=0; i<researchGroup.getLinesOfResearch().size();i++) {
                    text=text+researchGroup.getLinesOfResearch().get(i).getLineOfResearch()+"\n";
                }
                textViewLinesOfResearchUserImageCard.setText(text);
            }
        }

        public void binItem(AcademicTitle academicTitle){
            textViewAcademicTitle.setText(academicTitle.getAcademicTitle());
            textViewInstitution.setText(academicTitle.getinstitution());
            textViewGraduationDate.setText(academicTitle.getDateEnd().toString());
        }

        public void binItem(LineOfResearch lineOfResearch){
            String state;
            if(lineOfResearch.getstate()==true){
                state=stateTrue;
            }else{
                state=stateFalse;
            }
            textViewLineOfResearch.setText(lineOfResearch.getLineOfResearch());
            textViewactive.setText(state);
        }


        @Override
        public void onClick(View v) {
            if(context instanceof NewResearcherActivity) {
                ((NewResearcherActivity) context).onItemPosition(getAdapterPosition());
            }else if(context instanceof NewResearchGroupActivity){
                ((NewResearchGroupActivity) context).onItemPosition(getAdapterPosition());
            }else if(context instanceof ProfileActivity){
                ((ProfileActivity)context).onItemPosition(getAdapterPosition());
            }
        }
    }
}
