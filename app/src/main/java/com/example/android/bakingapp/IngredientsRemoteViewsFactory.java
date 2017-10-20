package com.example.android.bakingapp;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.DataModels.Ingredient;

import java.util.ArrayList;

/**
 * Created by mostafayehya on 18/10/17.
 */

public class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    public static ArrayList<Ingredient> mList;

    public IngredientsRemoteViewsFactory(Context appContext) {
        mContext = appContext;
        mList = new ArrayList<>();
        mList.get(0).name = "name holder";
        mList.get(0).quantity = 20;
        mList.get(0).measurment = "TSP";
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);

        rv.setTextViewText(R.id.ingredient_name, mList.get(position).name);
        rv.setTextViewText(R.id.ingredient_quantity, mList.get(position).quantity + mList.get(position).measurment);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public static void setIngredientList(ArrayList<Ingredient> ingredientList) {

        mList = ingredientList;
    }

    public static void setDefaultIngredientList() {

    }
}
