package com.example.android.bakingapp.Fragments;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.Adapters.IngredientAdapter;
import com.example.android.bakingapp.Adapters.StepAdapter;
import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.DataModels.Step;
import com.example.android.bakingapp.IngredientsRemoteViewsFactory;
import com.example.android.bakingapp.IngredientsWidgetProvider;
import com.example.android.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafayehya on 09/10/17.
 */

public class RecipeDetailsFragment extends Fragment implements StepAdapter.RecyclerViewItemClickHandler {

    @BindView(R.id.ingredient_title)
    TextView ingredients;
    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredientsRecyclerView;
    @BindView(R.id.step_title)
    TextView steps;
    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;
    @BindView(R.id.mark_as_favourite)
    ImageView isFavouriteImageView;
    static ArrayList<Ingredient> mIngredientList;
    ArrayList<Step> mStepList;
    boolean mTwoPane;
    boolean isRecipeFavorite = false;
    StepClickHandler stepClickHandler;
    Bundle arguements;


    public interface StepClickHandler {

        void onStepClicked(int stepPosition, ArrayList<Step> mStepList);
    }

    public RecipeDetailsFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            arguements = savedInstanceState.getBundle("arguments");
            mTwoPane = savedInstanceState.getBoolean("mTwoPane");
            mStepList = savedInstanceState.getParcelableArrayList("mStepList");
            mIngredientList = savedInstanceState.getParcelableArrayList("mIngredientList");
            isRecipeFavorite = savedInstanceState.getBoolean("isRecipeFavorite");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, rootView);

        if (rootView.findViewById(R.id.recipe_details_container) != null) {
            mTwoPane = true;
        }

        arguements = getArguments();
        if (arguements != null) {
            mIngredientList = arguements.getParcelableArrayList("Ingredients");
            mStepList = arguements.getParcelableArrayList("Steps");
        }

        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ingredientsRecyclerView.setLayoutManager(ingredientsLayoutManager);

        LinearLayoutManager stepsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stepsRecyclerView.setLayoutManager(stepsLayoutManager);

        IngredientAdapter ingredientAdapter = new IngredientAdapter(getActivity(), mIngredientList);
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        StepAdapter stepAdapter = new StepAdapter(getActivity(), mStepList, true);
        stepsRecyclerView.setAdapter(stepAdapter);

        isFavouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRecipeFavorite) {
                    isRecipeFavorite = true;
                    isFavouriteImageView.setImageResource(R.drawable.loved_recipe);
                    Toast.makeText(getContext(), "Ingredients  added to widget ", Toast.LENGTH_SHORT).
                            show();
                    IngredientsRemoteViewsFactory.setIngredientList(mIngredientList);
                    AppWidgetManager widgetManager = AppWidgetManager.getInstance(getContext());
                    int[] appWidgetIds = widgetManager.getAppWidgetIds(new ComponentName(getContext(), IngredientsWidgetProvider.class));

                    widgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_list_view);
                } else {
                    isRecipeFavorite = false;
                    isFavouriteImageView.setImageResource(R.drawable.not_loved_recipe);
                    Toast.makeText(getContext(), "Ingredients removed from widget ", Toast.LENGTH_SHORT).
                            show();
                    IngredientsRemoteViewsFactory.setDefaultIngredientList();

                    AppWidgetManager widgetManager = AppWidgetManager.getInstance(getContext());
                    int[] appWidgetIds = widgetManager.getAppWidgetIds(new ComponentName(getContext(), IngredientsWidgetProvider.class));

                    widgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_list_view);
                }
            }
        });


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("arguments", arguements);
        outState.putParcelableArrayList("mIngredientList", mIngredientList);
        outState.putParcelableArrayList("mStepList", mStepList);
        outState.putBoolean("mTwoPane", mTwoPane);
        outState.putBoolean("isRecipeFavorite", isRecipeFavorite);

    }

    @Override
    public void onRecyclerViewItemClicked(int position, ArrayList<Step> mStepList) {

        stepClickHandler.onStepClicked(position, mStepList);

    }
}
