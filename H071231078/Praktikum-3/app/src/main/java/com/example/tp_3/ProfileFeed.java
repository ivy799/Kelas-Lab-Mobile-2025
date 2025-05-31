package com.example.tp_3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ProfileFeed implements Parcelable {
    private String username, feed, likes, comments, shares, time, imageUri;
    private Integer image;

    public ProfileFeed(String username, String feed, String likes, String comments, String shares, String time, Integer image) {
        this.username = username;
        this.feed = feed;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.time = time;
        this.image = image;
    }

    public ProfileFeed(String username, String feed, String likes, String comments, String shares, String time, String imageUri) {
        this.username = username;
        this.feed = feed;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.time = time;
        this.imageUri = imageUri;
    }

    protected ProfileFeed(Parcel in) {
        username = in.readString();
        feed = in.readString();
        likes = in.readString();
        comments = in.readString();
        shares = in.readString();
        time = in.readString();
        imageUri = in.readString();
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
    }


    public static final Creator<ProfileFeed> CREATOR = new Creator<ProfileFeed>() {
        @Override
        public ProfileFeed createFromParcel(Parcel in) {
            return new ProfileFeed(in);
        }

        @Override
        public ProfileFeed[] newArray(int size) {
            return new ProfileFeed[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Boolean hasImageUri() {
        return imageUri != null && !imageUri.isEmpty();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(feed);
        parcel.writeString(likes);
        parcel.writeString(comments);
        parcel.writeString(shares);
        parcel.writeString(time);
        parcel.writeString(imageUri);
        if (image == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(image);
        }
    }
}
