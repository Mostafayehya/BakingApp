package com.example.android.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafayehya on 14/10/17.
 */

public class StepDescriptionFragment extends Fragment {

    @BindView(R.id.step_description_text_view)
    TextView descriptionTextView;
    String description = "";

    public StepDescriptionFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_step_description, null);
        ButterKnife.bind(this, rootView);
        descriptionTextView.setText(description);
        return rootView;
    }

    public void setDescription(String stepDescription) {
        description = "";
        description = stepDescription;
    }


}
