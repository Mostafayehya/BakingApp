package com.example.android.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.Adapters.IngredientAdapter;
import com.example.android.bakingapp.Adapters.StepAdapter;
import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.DataModels.Step;
import com.example.android.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafayehya on 09/10/17.
 */

public class RecipeDetailsFragment extends Fragment {

    @BindView(R.id.ingredient_title)
    TextView ingredients;
    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredientsRecyclerView;
    @BindView(R.id.step_title)
    TextView steps;
    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;

    ArrayList<Ingredient> mIngredientList;
    ArrayList<Step> mStepList;

    OnStepClickListener mCallBack;

    public interface OnStepClickListener {

        void onStepSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallBack = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnStepClickListener");

        }
    }

    public RecipeDetailsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(getActivity(), rootView); // i guessed this : D not learnt it


        IngredientAdapter ingredientAdapter = new IngredientAdapter(getActivity(), mIngredientList);
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        StepAdapter stepAdapter = new StepAdapter(getActivity(), mStepList, false);
        stepsRecyclerView.setAdapter(stepAdapter);


        stepsRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = view.getVerticalScrollbarPosition();
                mCallBack.onStepSelected(position);
            }
        });

        return rootView;
    }
}
