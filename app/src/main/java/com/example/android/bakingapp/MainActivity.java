package com.example.android.bakingapp;

import android.content.Context;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.bakingapp.Adapters.RecipeAdapter;
import com.example.android.bakingapp.DataModels.Recipe;
import com.example.android.bakingapp.Utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Context context;
    ArrayList<Recipe> mRecipeList;
    @BindView(R.id.recipes_recycler_view)
    RecyclerView recipesRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.no_internet_error_message)
    TextView mErrorTextView;
    RecipeAdapter recipeAdapter;
    public final String RECIPES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        recipeAdapter = new RecipeAdapter(this);

        recipesRecyclerView.setAdapter(recipeAdapter);


    }


    private void showErrorMessage() {
                /* First, hide the currently visible data */
        recipesRecyclerView.setVisibility(View.INVISIBLE);
               /* Then, show the error */
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    public class fetchRecipesDataTask extends AsyncTask<String, Void, ArrayList<Recipe>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

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
            URL movieRequestUrl = NetworkUtils.buildUrl(urlStringToBeRequested);

            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                mRecipeList.clear();
                mRecipeList.addAll(OpenMovieJsonUtils
                        .getArrayListOfMoviesFromJson(MainActivity.this, jsonRecipeResponse));

                return movieList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // TODO  add the movie list to the DB .
        @Override
        protected void onPostExecute(ArrayList<Movie> m) {
            if (m != null) {
                showMoviesDataView();
                movieAdapter.setMoviesList(m);
            }
            super.onPostExecute(m);
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
