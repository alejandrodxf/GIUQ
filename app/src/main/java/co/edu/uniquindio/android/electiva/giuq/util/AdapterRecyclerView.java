package co.edu.uniquindio.android.electiva.giuq.util;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.vo.AcademicTitle;
import co.edu.uniquindio.android.electiva.giuq.vo.LineOfResearch;
import co.edu.uniquindio.android.electiva.giuq.vo.ResearchGroup;
import co.edu.uniquindio.android.electiva.giuq.vo.Researcher;

import static co.edu.uniquindio.android.electiva.giuq.R.string.active;
import static co.edu.uniquindio.android.electiva.giuq.fragments.AcademicTitleFragment.ACADEMIC_TITLE;
import static co.edu.uniquindio.android.electiva.giuq.fragments.LineOfResearchFragment.LINE_OF_RESEARCH;
import static co.edu.uniquindio.android.electiva.giuq.fragments.MembersResearchGroupFragment.MEMBERS_RESEARCH_GROUP;


/**
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.AdapterRecyclerViewViewHolder> {

    /**
     * Atributo que representa una lista de títulos
     */
    private List items;
    private String type;
    private static OnClickAdapterRecyclerView listener;


    public AdapterRecyclerView(List items, Fragment fragment,String type) {
        this.items = items;
        this.type =type;
        listener = (OnClickAdapterRecyclerView) fragment;
    }


    @Override
    public AdapterRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
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
        }
        AdapterRecyclerViewViewHolder adapter = new AdapterRecyclerViewViewHolder(view);
        return adapter;
    }

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
                holder.binItem(researcher);
                break;
            }
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class AdapterRecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Campos respectivos de un item
        public TextView textViewLineOfResearch;
        public TextView textViewactive;
        public TextView textViewAcademicTitle;
        public TextView textViewInstitution;
        public TextView textViewGraduationDate;
        public TextView textViewResearcherMember;
        public TextView textViewCategoryResearcherMember;
        public String stateTrue;
        public String stateFalse;


        public AdapterRecyclerViewViewHolder(View v) {
            super(v);
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

            }

        }

        public void binItem(Researcher researcher){
            textViewResearcherMember.setText(researcher.getName());
            textViewCategoryResearcherMember.setText(researcher.getCategory());
        }

        public void binItem(ResearchGroup researchGroup){

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
            listener.onClickPosition(getAdapterPosition());
        }
    }

    public interface OnClickAdapterRecyclerView{

        public void onClickPosition(int pos);

    }
}
