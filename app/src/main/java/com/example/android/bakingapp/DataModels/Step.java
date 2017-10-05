package com.example.android.bakingapp.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mostafayehya on 02/10/17.
 */

public class Step implements Parcelable {

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

    public Step(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    private Step(Parcel in) {

        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoUrl);
        parcel.writeString(thumbnailUrl);
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel parcel) {
            return new Step(parcel);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

}
