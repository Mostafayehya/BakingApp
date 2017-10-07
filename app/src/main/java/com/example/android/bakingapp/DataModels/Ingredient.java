package com.example.android.bakingapp.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class Ingredient implements Parcelable {

    public int quantity;
    public String measurment;
    public String name;

    public Ingredient() {
        quantity = 0;
        measurment = "";
        name = "";

    }

    public Ingredient(int quantity, String measurment, String name) {
        this.quantity = quantity;
        this.measurment = measurment;
        this.name = name;
    }

    private Ingredient(Parcel in) {

        quantity = in.readInt();
        measurment = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(quantity);
        parcel.writeString(measurment);
        parcel.writeString(name);
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }


}
