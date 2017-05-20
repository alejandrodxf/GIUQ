package co.edu.uniquindio.android.electiva.giuq.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.uniquindio.android.electiva.giuq.R;
import co.edu.uniquindio.android.electiva.giuq.util.Language;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageDialogFragment extends DialogFragment {


    /**
     * Atributo que representa el estado de la línea de investigación
     */
    @BindView(R.id.radioGroupLanguage)
    protected RadioGroup radioGroupLanguage;


    @BindView(R.id.buttonSelectLanguage)
    protected Button buttonSelectLanguage;


    public LanguageDialogFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language_dialog, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonSelectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupLanguage.getCheckedRadioButtonId();
                switch (selectedId){
                    case R.id.radioButtonEnglish:{
                        Language.changeLanguage(getContext(),Language.LANGUAGE_EN);
                        break;
                    }
                    case R.id.radioButtonSpanish:{
                        Language.changeLanguage(getContext(),Language.LANGUAGE_ES);
                        break;
                    }
                }
                Intent intent = getActivity().getIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().finish();
                startActivity(intent);
            }
        });
    }
}
