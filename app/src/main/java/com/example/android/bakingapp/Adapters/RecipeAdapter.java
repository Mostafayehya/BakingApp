package com.example.android.bakingapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.DataModels.Recipe;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.RecipeDetailsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> mRecipeList;
    private LayoutInflater mInflater;
    private Context context;

    public RecipeAdapter(Context context) {
        mRecipeList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_item, parent);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        String recipeName = mRecipeList.get(position).name;


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        public
        @BindView(R.id.recipe_name)
        TextView mTextViewRecipeName;

        public
        @BindView (R.id.servings)
        TextView mTextViewServings;

        public
        @BindView (R.id.steps)
        TextView mTextViewStepsNumber;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Activity) context);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int adapterPosition = getAdapterPosition();

            Recipe selectedRecipe = mRecipeList.get(adapterPosition);

            Intent toStartRecipeDetailsActivity = new Intent(context,RecipeDetailsActivity.class);

            toStartRecipeDetailsActivity.putExtra("Recipe",selectedRecipe);

            //used the context as a refer to the MainActivity as it is the activity served by this adapter
            context.startActivity(toStartRecipeDetailsActivity);

        }
    }

    public void setRecipeArrayList(ArrayList<Recipe> list) {
        mRecipeList.clear();
        mRecipeList.addAll(list);
        notifyDataSetChanged();

    }
}
