package com.example.android.bakingapp.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class Ingerdient implements Parcelable {

    int quantity;
    String measurment;
    String name;

    public Ingerdient() {
        quantity = 0;
        measurment = "";
        name = "";

    }

    public Ingerdient(int quantity, String measurment, String name) {
        this.quantity = quantity;
        this.measurment = measurment;
        this.name = name;
    }

    private Ingerdient(Parcel in) {

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

    public static final Parcelable.Creator<Ingerdient> CREATOR = new Parcelable.Creator<Ingerdient>() {
        @Override
        public Ingerdient createFromParcel(Parcel parcel) {
            return new Ingerdient(parcel);
        }

        @Override
        public Ingerdient[] newArray(int size) {
            return new Ingerdient[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }


}
