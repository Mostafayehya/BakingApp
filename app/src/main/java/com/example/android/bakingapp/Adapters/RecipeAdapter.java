package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.DataModels.Recipe;
import com.example.android.bakingapp.DataModels.Step;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.RecipeDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // the replacement of null with parent caused the following
        // error java.lang.IllegalStateException:
        // The specified child already has a parent. You must call removeView() on the child's parent first
        View view = mInflater.inflate(R.layout.recipe_item, null);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        String recipeName = mRecipeList.get(position).name;
        String servingsNumber = "Servings: " + Integer.toString(mRecipeList.get(position).servings);
        String stepsNumber = "Steps: " + Integer.toString(mRecipeList.get(position).stepsList.size());

        if (!mRecipeList.get(position).imagUrl.equals("")) {
            holder.mRecipeImage.setVisibility(View.VISIBLE);
            Picasso.with(context).load(mRecipeList.get(position).imagUrl).into(holder.mRecipeImage);
        }
        holder.mTextViewRecipeName.setText(recipeName);
        holder.mTextViewServings.setText(servingsNumber);
        holder.mTextViewStepsNumber.setText(stepsNumber);


    }

    @Override
    public int getItemCount() {

        return mRecipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mRecipeImage;
        TextView mTextViewRecipeName;
        TextView mTextViewServings;
        TextView mTextViewStepsNumber;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mRecipeImage = (ImageView) itemView.findViewById(R.id.recipe_image);
            mTextViewRecipeName = (TextView) itemView.findViewById(R.id.recipe_name);
            mTextViewServings = (TextView) itemView.findViewById(R.id.servings);
            mTextViewStepsNumber = (TextView) itemView.findViewById(R.id.steps);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int adapterPosition = getAdapterPosition();

            Recipe selectedRecipe = mRecipeList.get(adapterPosition);

            ArrayList<Ingredient> selectedIngredient = selectedRecipe.ingredientList;
            ArrayList<Step> selectedSteps = selectedRecipe.stepsList;

            Intent toStartRecipeDetailsActivity = new Intent(context, RecipeDetailsActivity.class);

            toStartRecipeDetailsActivity.putExtra("Ingredients", selectedIngredient);
            toStartRecipeDetailsActivity.putExtra("Steps", selectedSteps);

            //used the context as a refer to the MainActivity as it is the activity served by this adapter
            context.startActivity(toStartRecipeDetailsActivity);

        }
    }

    public void setRecipeArrayList(ArrayList<Recipe> list) {
        mRecipeList = list;
        notifyDataSetChanged();

    }
}