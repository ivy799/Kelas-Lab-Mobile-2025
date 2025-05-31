package com.example.tp_8;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Note implements Parcelable {
    private int Id;
    private String Title;
    private String Description;
    private String Time;

    public Note(String title, String description, String time, int id) {
        Title = title;
        Description = description;
        Time = time;
        Id = id;
    }

    protected Note(Parcel in) {
        Title = in.readString();
        Description = in.readString();
        Time = in.readString();
        Id = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public Note() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Description);
        dest.writeString(Time);
        dest.writeInt(Id);
    }
}
