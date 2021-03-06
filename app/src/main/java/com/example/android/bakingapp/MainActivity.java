package com.example.android.bakingapp;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.bakingapp.Adapters.RecipeAdapter;
import com.example.android.bakingapp.DataModels.Recipe;
import com.example.android.bakingapp.Utilities.MySharedPref;
import com.example.android.bakingapp.Utilities.NetworkUtils;
import com.example.android.bakingapp.Utilities.OpenRecipeJsonUtils;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Context context;
    ArrayList<Recipe> mRecipeList = new ArrayList<>();
    @BindView(R.id.recipes_recycler_view)
    RecyclerView recipesRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.no_internet_error_message)
    TextView mErrorTextView;
    RecipeAdapter recipeAdapter;
    public final String RECIPES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    public static final String RECIPES_KEY = "recipes_key";
    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        context = this;

        //Check if the device is a tablet or a phone
        if (findViewById(R.id.tablet_main_layout) != null) {
            mTwoPane = true;

            //tablet Potrait case
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                recipesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }
            //tablet landscape case
            else {
                recipesRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            }

        } else {
            mTwoPane = false;

            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ||
                    this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        }


        recipeAdapter = new RecipeAdapter(this);

        recipesRecyclerView.setHasFixedSize(true);


        recipesRecyclerView.setAdapter(recipeAdapter);

        if (!isOnline()) {
            showErrorMessage();
        }

        if (savedInstanceState == null) {

            requestRecipesFromTheInternet();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPES_KEY, mRecipeList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mRecipeList = savedInstanceState.getParcelableArrayList(RECIPES_KEY);
        recipeAdapter.setRecipeArrayList(mRecipeList);
    }

    private void showErrorMessage() {
                /* First, hide the currently visible data */
        recipesRecyclerView.setVisibility(View.INVISIBLE);
               /* Then, show the error */
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRecipesDataView() {

        mErrorTextView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        recipesRecyclerView.setVisibility(View.VISIBLE);
    }

    public void requestRecipesFromTheInternet() {

        new fetchRecipesDataTask().execute(RECIPES_URL);
    }

    public class fetchRecipesDataTask extends AsyncTask<String, Void, ArrayList<Recipe>> {
        ArrayList<Recipe> temporaryRecipeList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            temporaryRecipeList = new ArrayList<>();
            if (isOnline()) {
                recipesRecyclerView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
            } else {
                showErrorMessage();
            }
        }

        /*params is the the paramaters sent to this function by execute() method */
        @Override
        protected ArrayList<Recipe> doInBackground(String... params) {
  /* If there's no urls , there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String urlStringToBeRequested = params[0];
            URL recipeRequestUrl = NetworkUtils.buildUrl(urlStringToBeRequested);

            try {
                String jsonRecipeResponse = NetworkUtils.getResponseFromHttpUrl(recipeRequestUrl);

                temporaryRecipeList.clear();
                temporaryRecipeList.addAll(OpenRecipeJsonUtils.getArrayListOfRecipesFromJson(context, jsonRecipeResponse));

                return temporaryRecipeList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> r) {
            if (r != null) {
                showRecipesDataView();
                mRecipeList = r;
                recipeAdapter.setRecipeArrayList(mRecipeList);

                if (mRecipeList.size() >= 0) {
                    MySharedPref.setUpMySharedPreferences(getApplicationContext(), "widget");
                    MySharedPref.SaveLastRecipe(mRecipeList.get(0));
                }


            }
            super.onPostExecute(r);

        }

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}