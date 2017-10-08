package com.example.android.bakingapp.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class Recipe implements Parcelable {

    //these are made public so i can directly access them from the adapter as they are in a different package ,
    // i need to find a better design for this
    public int id;
    public String name;
    public ArrayList<Ingredient> ingredientList;
    public ArrayList<Step> stepsList;
    public int servings;
    public String imagUrl;

    public Recipe() {
        id = 0;
        name = "";
        ingredientList = new ArrayList<>();
        stepsList = new ArrayList<>();
        servings = 0;
        imagUrl = "";
    }

    public Recipe(int id, String name, ArrayList<Ingredient> ingerdientlist, ArrayList<Step> stepsList, int servings, String imagUrl) {
        this.id = id;
        this.name = name;
        this.ingredientList = ingerdientlist;
        this.stepsList = stepsList;
        this.servings = servings;
        this.imagUrl = imagUrl;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public ArrayList<Step> getStepsList() {
        return stepsList;
    }

    private Recipe(Parcel in) {

        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        imagUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(servings);
        parcel.writeString(imagUrl);

    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }
}