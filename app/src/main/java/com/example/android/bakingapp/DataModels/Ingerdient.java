package com.example.android.bakingapp.DataModels;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class Ingerdient {

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


}
