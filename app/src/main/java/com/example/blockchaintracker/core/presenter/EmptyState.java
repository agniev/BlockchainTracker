package com.example.blockchaintracker.core.presenter;


import android.os.Parcel;
import android.os.Parcelable;

public class EmptyState implements Parcelable {

    public EmptyState() {
    }

    protected EmptyState(Parcel in) {
    }

    public static final Creator<EmptyState> CREATOR = new Creator<EmptyState>() {
        @Override
        public EmptyState createFromParcel(Parcel in) {
            return new EmptyState(in);
        }

        @Override
        public EmptyState[] newArray(int size) {
            return new EmptyState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
