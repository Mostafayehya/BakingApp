package com.example.android.bakingapp;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.DataModels.Recipe;
import com.example.android.bakingapp.Utilities.MySharedPref;


class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;

    Recipe recipe=null;

    public RecipeRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
        recipe=new Recipe();
        MySharedPref.setUpMySharedPreferences(mContext,"widget");
    }

    @Override
    public void onCreate() {

    }

    //called on start and when notifyAppWidgetViewDataChanged is called
    @Override
    public void onDataSetChanged() {

        recipe= MySharedPref.RetriveLastRecipe();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return recipe.ingredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {


        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        views.setTextViewText(R.id.ingredient_name_widget, recipe.ingredientList.get(position).name);
        views.setTextViewText(R.id.ingredient_measure_widget, recipe.ingredientList.get(position).measurment);
        views.setTextViewText(R.id.ingredient_quantity_widget,  recipe.ingredientList.get(position).quantity+"");

        return views;
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
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}