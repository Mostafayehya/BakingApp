package com.example.android.bakingapp.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.R;

import java.util.ArrayList;

/**
 * Created by mostafayehya on 08/10/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    Context mContext;
    ArrayList<Ingredient> mIngredientList = new ArrayList<>();
    LayoutInflater mInflater;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients) {

        mContext = context;
        mIngredientList = ingredients;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        if (mIngredientList.size() == 0) {
            return 1;
        } else
            return mIngredientList.size();
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.ingredient_item, null);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {

        String ingredientName = mIngredientList.get(position).name;
        String ingredientQuantity = Integer.toString(mIngredientList.get(position).quantity) +
                " " +
                mIngredientList.get(position).measurment;

        holder.ingredientName.setText(ingredientName);
        holder.ingredientQuantity.setText(ingredientQuantity);
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        TextView ingredientQuantity;

        public IngredientViewHolder(View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientQuantity = itemView.findViewById(R.id.ingredient_quantity);
        }
    }

    public void setmIngredientList(ArrayList<Ingredient> newlist) {
        mIngredientList = newlist;
        notifyDataSetChanged();
    }
}
