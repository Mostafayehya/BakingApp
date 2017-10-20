package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.android.bakingapp.Adapters.StepAdapter;
import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.DataModels.Step;
import com.example.android.bakingapp.Fragments.PlayerFragment;
import com.example.android.bakingapp.Fragments.RecipeDetailsFragment;
import com.example.android.bakingapp.Fragments.StepDescriptionFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.bakingapp.Adapters.StepAdapter.CLICKED_POSITION_KEY;
import static com.example.android.bakingapp.Adapters.StepAdapter.STEP_LIST_KEY;

/**
 * Created by mostafayehya on 05/10/17.
 */

// you must declare your activity in the manifest before using it
public class RecipeDetailsActivity extends AppCompatActivity implements StepAdapter.RecyclerViewItemClickHandler {

    ArrayList<Ingredient> mIngredientList;
    Bundle receivedBundle;

    ArrayList<Step> mStepList;
    int viewedStepPosition = 0;
    String videoUrl = "";
    String selectedStepDescription = "";
    FragmentManager fragmentManager;
    @BindView(R.id.exo_player_fragment)
    FrameLayout exoPlayerFragmentContainer;
    @BindView(R.id.step_description_fragment)
    FrameLayout descriptionFragmentContainer;
    @BindView(R.id.controls_fragment)
    FrameLayout controlsFragmentContainer;
    RecipeDetailsFragment recipeDetailsFragment;
    StepDescriptionFragment newStepDescriptionFragment;
    PlayerFragment newPlayerFragment;


    boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_details);
        Intent intentStartedThisActivity = getIntent();


        if (intentStartedThisActivity != null) {
            if (intentStartedThisActivity.hasExtra("Steps")) {
                receivedBundle = intentStartedThisActivity.getExtras();
                mIngredientList = intentStartedThisActivity.getParcelableArrayListExtra("Ingredients");
                mStepList = intentStartedThisActivity.getParcelableArrayListExtra("Steps");
            }
        }

        //Two pane mode
        if (findViewById(R.id.recipe_details_container) != null) {
            mTwoPane = true;
            ButterKnife.bind(this);
            controlsFragmentContainer.setVisibility(View.GONE);

            if (savedInstanceState != null) {
                viewedStepPosition = savedInstanceState.getInt("viewedStepPosition");
                mStepList = savedInstanceState.getParcelableArrayList("mStepList");
                mIngredientList = savedInstanceState.getParcelableArrayList("mIngredientList");
                newPlayerFragment = (PlayerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "newPlayerFragment");
                newStepDescriptionFragment = (StepDescriptionFragment) getSupportFragmentManager()
                        .getFragment(savedInstanceState, "newStepDescriptionFragment");
            } else {


                RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
                recipeDetailsFragment.setArguments(receivedBundle);
                getSupportFragmentManager().beginTransaction().add(R.id.master_list_fragment, recipeDetailsFragment).commit();

                fragmentManager = getSupportFragmentManager();

                //**************** player fragment****************//

                newPlayerFragment = new PlayerFragment();
                videoUrl = mStepList.get(viewedStepPosition).videoUrl;

                //handling steps with/without video

                newPlayerFragment.setStepVideoUrl(videoUrl);
                fragmentManager
                        .beginTransaction()
                        .add(R.id.exo_player_fragment, newPlayerFragment)
                        .commit();
                if (!videoUrl.equals("")) {
                    exoPlayerFragmentContainer.setVisibility(View.VISIBLE);

                } else {
                    exoPlayerFragmentContainer.setVisibility(View.GONE);
                }

                //**************** Description fragment****************//

                newStepDescriptionFragment = new StepDescriptionFragment();
                selectedStepDescription =
                        mStepList.get(viewedStepPosition).description;

                newStepDescriptionFragment.setDescription(selectedStepDescription);

                fragmentManager
                        .beginTransaction()
                        .add(R.id.step_description_fragment, newStepDescriptionFragment)
                        .commit();
            }

        } else {

            //Single pane case

            mTwoPane = false;

//            if (savedInstanceState != null) {
//
//                recipeDetailsFragment = (RecipeDetailsFragment) getSupportFragmentManager().
//                        getFragment(savedInstanceState, "recipeDetailsFragment");
//            } else {

            recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(receivedBundle);
            getSupportFragmentManager().beginTransaction().add(R.id.master_list_fragment, recipeDetailsFragment).commit();
//            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBundle("receivedBundle", receivedBundle);
        outState.putParcelableArrayList("mStepList", mStepList);
        outState.putParcelableArrayList("mIngredientList", mIngredientList);
        outState.putBoolean("mTwoPane", mTwoPane);
        outState.putInt("viewedStepPosition", viewedStepPosition);
        outState.putString("currentVideoUrl", videoUrl);
//        if (recipeDetailsFragment != null)
//            getSupportFragmentManager().putFragment(outState, "recipeDetailsFragment", recipeDetailsFragment);
        if (newPlayerFragment != null)
            getSupportFragmentManager().putFragment(outState, "newPlayerFragment", newPlayerFragment);
        if (newStepDescriptionFragment != null)
            getSupportFragmentManager().putFragment(outState, "newStepDescriptionFragment", newStepDescriptionFragment);

    }

//    @Override
//    public void onStepClicked(int stepPosition, ArrayList<Step> mStepList) {
//
//        if (mTwoPane) {
//
//            viewedStepPosition = stepPosition;
//            this.mStepList = mStepList;
//
//            fragmentManager = getSupportFragmentManager();
//
//            //**************** player fragment****************//
//
//            newPlayerFragment = new PlayerFragment();
//            currentVideoUrl = mStepList.get(viewedStepPosition).currentVideoUrl;
//
//            //handling steps with/without video
//
//            if (!currentVideoUrl.equals("")) {
//                newPlayerFragment.setStepVideoUrl(currentVideoUrl);
//                fragmentManager
//                        .beginTransaction()
//                        .replace(R.id.exo_player_fragment, newPlayerFragment)
//                        .commit();
//            } else {
//                exoPlayerFragmentContainer.setVisibility(View.GONE);
//            }
//
//            //**************** Description fragment****************//
//
//            newStepDescriptionFragment = new StepDescriptionFragment();
//            selectedStepDescription =
//                    mStepList.get(viewedStepPosition).description;
//
//            newStepDescriptionFragment.setDescription(selectedStepDescription);
//
//            fragmentManager
//                    .beginTransaction()
//                    .replace(R.id.step_description_fragment, newStepDescriptionFragment)
//                    .commit();
//
//        } else {
//            Intent toStartStepDetailActivity = new Intent(this, StepDetailsActivity.class);
//            toStartStepDetailActivity.putExtra(CLICKED_POSITION_KEY, stepPosition);
//            toStartStepDetailActivity.putExtra(STEP_LIST_KEY, mStepList);
//            startActivity(toStartStepDetailActivity);
//        }
//
//
//    }

    @Override
    public void onRecyclerViewItemClicked(int position, ArrayList<Step> mStepList) {

        if (mTwoPane) {

            viewedStepPosition = position;
            this.mStepList = mStepList;

            fragmentManager = getSupportFragmentManager();

            //**************** player fragment****************//

            newPlayerFragment = new PlayerFragment();
            videoUrl = mStepList.get(viewedStepPosition).videoUrl;

            //handling steps with/without video

            newPlayerFragment.setStepVideoUrl(videoUrl);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.exo_player_fragment, newPlayerFragment)
                    .commit();
            if (!videoUrl.equals("")) {
                exoPlayerFragmentContainer.setVisibility(View.VISIBLE);

            } else {
                exoPlayerFragmentContainer.setVisibility(View.GONE);
            }

            //**************** Description fragment****************//

            newStepDescriptionFragment = new StepDescriptionFragment();
            selectedStepDescription =
                    mStepList.get(viewedStepPosition).description;

            newStepDescriptionFragment.setDescription(selectedStepDescription);

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.step_description_fragment, newStepDescriptionFragment)
                    .commit();

        } else {
            Intent toStartStepDetailActivity = new Intent(this, StepDetailsActivity.class);
            toStartStepDetailActivity.putExtra(CLICKED_POSITION_KEY, position);
            toStartStepDetailActivity.putExtra(STEP_LIST_KEY, mStepList);
            startActivity(toStartStepDetailActivity);
        }
    }
}
