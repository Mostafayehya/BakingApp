package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.DataModels.Step;

import java.util.ArrayList;

/**
 * Created by mostafayehya on 08/10/17.
 */

public class StepDetailsActivity extends AppCompatActivity {

    ArrayList<Step> mStepList;
    int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);



    }
}
