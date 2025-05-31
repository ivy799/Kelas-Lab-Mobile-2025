package com.example.tp_3;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable {
    private String title, time;
    private int image;

    public Story(String title, int image, String time) {
        this.title = title;
        this.image = image;
        this.time = time;
    }

    protected Story(Parcel in) {
        title = in.readString();
        image = in.readInt();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(image);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
