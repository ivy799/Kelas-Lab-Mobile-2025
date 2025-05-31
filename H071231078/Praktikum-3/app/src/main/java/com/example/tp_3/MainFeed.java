package com.example.tp_3;

import android.os.Parcel;
import android.os.Parcelable;

public class MainFeed implements Parcelable {
    private String name, username, feed, bio, time;
    private Integer profile, likes, comments, shares, image;

    public MainFeed(String name, String username, String feed, String bio, String time, Integer profile, Integer likes, Integer comments, Integer shares, Integer image) {
        this.name = name;
        this.username = username;
        this.feed = feed;
        this.bio = bio;
        this.time = time;
        this.profile = profile;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.image = image;
    }

    protected MainFeed(Parcel in) {
        name = in.readString();
        username = in.readString();
        feed = in.readString();
        bio = in.readString();
        time = in.readString();
        if (in.readByte() == 0) {
            profile = null;
        } else {
            profile = in.readInt();
        }
        if (in.readByte() == 0) {
            likes = null;
        } else {
            likes = in.readInt();
        }
        if (in.readByte() == 0) {
            comments = null;
        } else {
            comments = in.readInt();
        }
        if (in.readByte() == 0) {
            shares = null;
        } else {
            shares = in.readInt();
        }
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(feed);
        dest.writeString(bio);
        dest.writeString(time);
        if (profile == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(profile);
        }
        if (likes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likes);
        }
        if (comments == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(comments);
        }
        if (shares == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(shares);
        }
        if (image == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(image);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainFeed> CREATOR = new Creator<MainFeed>() {
        @Override
        public MainFeed createFromParcel(Parcel in) {
            return new MainFeed(in);
        }

        @Override
        public MainFeed[] newArray(int size) {
            return new MainFeed[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
