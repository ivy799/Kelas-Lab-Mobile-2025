package com.example.tp_4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Book implements Parcelable {
    private String judul, penulis, tahunTerbit, blurb, coverUri;
    private Integer cover, rating;
    private Boolean favorite;
    private ArrayList<String> genre, review;

    public Book(String judul, String penulis, String tahunTerbit, String blurb, Integer cover, Integer rating, Boolean favorite, ArrayList<String> genre, ArrayList<String> review) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.blurb = blurb;
        this.cover = cover;
        this.rating = rating;
        this.favorite = favorite;
        this.genre = genre;
        this.review = review;
    }

    public Book(String judul, String penulis, String tahunTerbit, String blurb, String coverUri, Integer rating, Boolean favorite, ArrayList<String> genre, ArrayList<String> review) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.blurb = blurb;
        this.coverUri = coverUri;
        this.rating = rating;
        this.favorite = favorite;
        this.genre = genre;
        this.review = review;
    }

    protected Book(Parcel in) {
        judul = in.readString();
        penulis = in.readString();
        tahunTerbit = in.readString();
        blurb = in.readString();
        coverUri = in.readString();
        if (in.readByte() == 0) {
            cover = null;
        } else {
            cover = in.readInt();
        }
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readInt();
        }
        byte tmpFavorite = in.readByte();
        favorite = tmpFavorite == 0 ? null : tmpFavorite == 1;
        genre = in.createStringArrayList();
        review = in.createStringArrayList();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public ArrayList<String> getReview() {
        return review;
    }

    public void setReview(ArrayList<String> review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return judul.equals(book.judul);
    }

    @Override
    public int hashCode() {
        return judul.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(judul);
        parcel.writeString(penulis);
        parcel.writeString(tahunTerbit);
        parcel.writeString(blurb);
        parcel.writeString(coverUri);
        if (cover == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cover);
        }
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(rating);
        }
        parcel.writeByte((byte) (favorite == null ? 0 : favorite ? 1 : 2));
        parcel.writeStringList(genre);
        parcel.writeStringList(review);
    }
}