package com.example.android.bakingapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by mostafayehya on 19/10/17.
 */

public class IngredietnsRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientsRemoteViewsFactory(this.getApplicationContext());
    }
}
