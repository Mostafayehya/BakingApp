package com.example.android.bakingapp.DataModels;

import java.util.ArrayList;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class Recipe {

    int id;
    String name;
    ArrayList<Ingerdient> ingerdientlist;
    ArrayList<Step> stepsList;
    int servings;
    String imagUrl;

    public Recipe() {
        id = 0;
        name = "";
        ingerdientlist = new ArrayList<>();
        stepsList = new ArrayList<>();
        servings = 0;
        imagUrl = "";
    }

    public Recipe(int id, String name, ArrayList<Ingerdient> ingerdientlist, ArrayList<Step> stepsList, int servings, String imagUrl) {
        this.id = id;
        this.name = name;
        this.ingerdientlist = ingerdientlist;
        this.stepsList = stepsList;
        this.servings = servings;
        this.imagUrl = imagUrl;
    }


}