package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.DataModels.Step;
import com.example.android.bakingapp.Fragments.RecipeDetailsFragment;

import java.util.ArrayList;

/**
 * Created by mostafayehya on 05/10/17.
 */

// you must declare your activity in the manifest before using it
public class RecipeDetailsActivity extends AppCompatActivity {

    ArrayList<Step> mStepList;
    ArrayList<Ingredient> mIngredientList;
    Bundle receivedBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState !=null){
            receivedBundle = savedInstanceState.getBundle("receivedBundle");
        }
        setContentView(R.layout.activity_recipe_details);

        Intent intentStartedThisActivity = getIntent();
         receivedBundle = new Bundle();

        if (intentStartedThisActivity != null) {
            if (intentStartedThisActivity.hasExtra("Steps")) {
                receivedBundle = intentStartedThisActivity.getExtras();
            }
        }

        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        recipeDetailsFragment.setArguments(receivedBundle);
        getSupportFragmentManager().beginTransaction().add(R.id.master_list_fragment,recipeDetailsFragment).commit();



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBundle("receivedBundle" , receivedBundle);
    }
}
