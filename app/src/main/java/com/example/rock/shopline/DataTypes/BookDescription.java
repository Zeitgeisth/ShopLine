package com.example.rock.shopline.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rock on 9/20/2018.
 */

public class BookDescription implements Parcelable {
    private String BookName;
    private String Cost;
    private String Genre;
    private String Image;

    public static final Creator<BookDescription> CREATOR = new Creator<BookDescription>() {
        @Override
        public BookDescription createFromParcel(Parcel in) {
            return new BookDescription(in);
        }

        @Override
        public BookDescription[] newArray(int size) {
            return new BookDescription[size];
        }
    };

    public BookDescription() {

    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    private String UserID;

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(BookName);
        parcel.writeString(Cost);
        parcel.writeString(Genre);
        parcel.writeString(Image);
        parcel.writeString(UserID);
    }

    public BookDescription(Parcel in){

        BookName = in.readString();
        Cost = in.readString();
        Genre = in.readString();
        Image = in.readString();
        UserID = in.readString();
    }

}
