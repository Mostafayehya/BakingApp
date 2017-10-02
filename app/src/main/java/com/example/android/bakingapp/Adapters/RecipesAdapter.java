package com.example.android.bakingapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {


    @Override
    public RecipesAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        public RecipeViewHolder(View itemView) {
            super(itemView);
        }

    }
}
