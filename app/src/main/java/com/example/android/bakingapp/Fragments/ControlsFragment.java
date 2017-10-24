package com.example.android.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafayehya on 14/10/17.
 */

public class ControlsFragment extends Fragment {

    @BindView(R.id.next_step_button)
    Button nextButton;
    @BindView(R.id.previous_step_button)
    Button previousButton;

    OnControlsClickListener mCallBack;

    public interface OnControlsClickListener {

        void onNextButtonClicked();

        void onPreviousButtonClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            mCallBack = (OnControlsClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnControlsClickListener");
        }
    }

    public ControlsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_controls, null);
        ButterKnife.bind(this,rootView);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onNextButtonClicked();

            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallBack.onPreviousButtonClicked();
            }
        });
        return rootView;
    }


}