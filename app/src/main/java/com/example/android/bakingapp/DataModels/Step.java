package com.example.android.bakingapp.DataModels;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class Step {

    int id;
    String shortDescription;
    String description;
    String videoUrl;
    String thumbnailUrl;

    public Step() {
        id = 0;
        shortDescription = "";
        description = "";
        videoUrl = "";
        thumbnailUrl = "";

    }

    public Step (int id , String shortDescription , String description , String videoUrl , String thumbnailUrl){
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }



}
