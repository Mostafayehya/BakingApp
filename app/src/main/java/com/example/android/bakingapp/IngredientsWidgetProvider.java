package com.example.android.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.bakingapp.DataModels.Ingredient;
import com.example.android.bakingapp.Fragments.RecipeDetailsFragment;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    public static String SEND_DATA_TO_PROVIDER_ACTION = "com.example.android.IngredientsWidgetProvider.SEND_DATA_TO_PROVIDER_ACTION";
   static ArrayList<Ingredient> mIngredientList;

    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);

        AppWidgetManager mgr = AppWidgetManager.getInstance(context);

        if (intent.getAction().equals(SEND_DATA_TO_PROVIDER_ACTION)) {
            mIngredientList = intent.getParcelableArrayListExtra(RecipeDetailsFragment.INGREDIENT_LIST_KEY);
            IngredientsRemoteViewsFactory.setIngredientList(mIngredientList);

            // starting the service to create the RemoteViews .
            Intent remoteViewsServiceIntent = new Intent(context, IngredietnsRemoteViewsService.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_provider);
            views.setRemoteAdapter(R.id.appwidget_list_view, remoteViewsServiceIntent);

        }
    }


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent remoteViewsServiceIntent = new Intent(context, IngredietnsRemoteViewsService.class);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_provider);
        views.setRemoteAdapter(R.id.appwidget_list_view, remoteViewsServiceIntent);
        IngredientsRemoteViewsFactory.setIngredientList(mIngredientList);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.layout.widget_list_item, pendingIntent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them


        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

