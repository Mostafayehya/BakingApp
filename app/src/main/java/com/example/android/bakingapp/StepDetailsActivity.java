package com.example.android.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.android.bakingapp.Adapters.StepAdapter;
import com.example.android.bakingapp.DataModels.Step;
import com.example.android.bakingapp.Fragments.ControlsFragment;
import com.example.android.bakingapp.Fragments.PlayerFragment;
import com.example.android.bakingapp.Fragments.StepDescriptionFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafayehya on 08/10/17.
 */

public class StepDetailsActivity extends AppCompatActivity implements ControlsFragment.OnControlsClickListener {

    ArrayList<Step> mStepList;
    int viewedStepPosition = 0;
    String currentVideoUrl;
    String currentVideoThumbnail;
    String selectedStepDescription = "";
    PlayerFragment newPlayerFragment;
    StepDescriptionFragment newStepDescriptionFragment;
    ControlsFragment mControlsFragment;
    FragmentManager fragmentManager = getSupportFragmentManager();
    @BindView(R.id.exo_player_fragment)
    FrameLayout exoPlayerFragmentContainer;
    @BindView(R.id.step_description_fragment)
    FrameLayout descriptionFragmentContainer;
    @BindView(R.id.controls_fragment)
    FrameLayout controlsFragmentContainer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        ButterKnife.bind(this);
        Intent intentStartedThisActivity = getIntent();

        if (intentStartedThisActivity.hasExtra(StepAdapter.STEP_LIST_KEY)) {
            mStepList = intentStartedThisActivity
                    .getParcelableArrayListExtra(StepAdapter.STEP_LIST_KEY);

            viewedStepPosition = intentStartedThisActivity.
                    getIntExtra(StepAdapter.CLICKED_POSITION_KEY, 0);

            currentVideoUrl = mStepList.get(viewedStepPosition).videoUrl;
            currentVideoThumbnail = mStepList.get(viewedStepPosition).thumbnailUrl;

        }
        if (savedInstanceState != null) {
            mStepList = savedInstanceState.getParcelableArrayList("mStepList");
            viewedStepPosition = savedInstanceState.getInt("viewedStepPosition");
            currentVideoUrl = savedInstanceState.getString("currentVideoUrl");
            currentVideoThumbnail = savedInstanceState.getString("currentVideoThumbnail");
            newPlayerFragment = (PlayerFragment) fragmentManager.getFragment(savedInstanceState, "newPlayerFragment");
            newStepDescriptionFragment = (StepDescriptionFragment) fragmentManager.getFragment(savedInstanceState, "newStepDescriptionFragment");
            mControlsFragment = (ControlsFragment) fragmentManager.getFragment(savedInstanceState, "mControlsFragment");


        } else {

            //**************** player fragment****************//

            newPlayerFragment = new PlayerFragment();

            //handling steps with/without video
            newPlayerFragment.setStepVideoUrl(currentVideoUrl);
            newPlayerFragment.setThumbnailUrl(currentVideoThumbnail);
            fragmentManager
                    .beginTransaction()
                    .add(R.id.exo_player_fragment, newPlayerFragment)
                    .commit();

            if (!currentVideoUrl.equals("") || !currentVideoThumbnail.equals("")) {
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


        //**************** Controls fragment****************//

        mControlsFragment = new ControlsFragment();
        fragmentManager
                .beginTransaction()
                .add(R.id.controls_fragment, mControlsFragment)
                .commit();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && !currentVideoUrl.equals("")) {
            descriptionFragmentContainer.setVisibility(View.GONE);
            controlsFragmentContainer.setVisibility(View.GONE);
        } else if (currentVideoUrl.equals("")) {
            exoPlayerFragmentContainer.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("mStepList", mStepList);
        outState.putInt("viewedStepPosition", viewedStepPosition);
        outState.putString("currentVideoUrl", currentVideoUrl);
        outState.putString("currentVideoThumbnail", currentVideoThumbnail);

        fragmentManager.putFragment(outState, "newPlayerFragment", newPlayerFragment);
        fragmentManager.putFragment(outState, "newStepDescriptionFragment", newStepDescriptionFragment);
        fragmentManager.putFragment(outState, "ControlsFragment", mControlsFragment);
    }

    @Override
    public void onPreviousButtonClicked() {
        if (viewedStepPosition == 0) {
            viewedStepPosition = mStepList.size() - 1;
        } else {
            --viewedStepPosition;
        }

        newPlayerFragment = new PlayerFragment();
        currentVideoUrl = mStepList.get(viewedStepPosition)
                .videoUrl;
        currentVideoThumbnail =mStepList.get(viewedStepPosition).thumbnailUrl;
        newPlayerFragment.setStepVideoUrl(currentVideoUrl);
        newPlayerFragment.setThumbnailUrl(currentVideoThumbnail);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.exo_player_fragment, newPlayerFragment)
                .commit();
        if (currentVideoUrl.equals("")) {
            exoPlayerFragmentContainer.setVisibility(View.GONE);
        } else {
            exoPlayerFragmentContainer.setVisibility(View.VISIBLE);
        }

        newStepDescriptionFragment = new StepDescriptionFragment();
        selectedStepDescription = mStepList.get(viewedStepPosition).description;
        newStepDescriptionFragment.setDescription(selectedStepDescription);

        fragmentManager
                .beginTransaction()
                .replace(R.id.step_description_fragment, newStepDescriptionFragment)
                .commit();


    }

    @Override
    public void onNextButtonClicked() {

        if (viewedStepPosition == mStepList.size() - 1) {
            viewedStepPosition = 0;
        } else {
            ++viewedStepPosition;
        }

        newPlayerFragment = new PlayerFragment();
        currentVideoUrl = mStepList.get(viewedStepPosition)
                .videoUrl;
        currentVideoThumbnail=mStepList.get(viewedStepPosition).thumbnailUrl;
        newPlayerFragment.setStepVideoUrl(currentVideoUrl);
        newPlayerFragment.setThumbnailUrl(currentVideoThumbnail);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.exo_player_fragment, newPlayerFragment)
                .commit();
        if (currentVideoUrl.equals("")) {
            exoPlayerFragmentContainer.setVisibility(View.GONE);
        } else {
            exoPlayerFragmentContainer.setVisibility(View.VISIBLE);
        }

        newStepDescriptionFragment = new StepDescriptionFragment();
        selectedStepDescription = mStepList.get(viewedStepPosition).description;
        newStepDescriptionFragment.setDescription(selectedStepDescription);

        fragmentManager
                .beginTransaction()
                .replace(R.id.step_description_fragment, newStepDescriptionFragment)
                .commit();
    }
}