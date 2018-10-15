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

    public String getImageFlag() {
        return ImageFlag;
    }

    public void setImageFlag(String imageFlag) {
        ImageFlag = imageFlag;
    }

    private String ImageFlag;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String ID;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Description;



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
        parcel.writeString(Description);
        parcel.writeString(ID);
        parcel.writeString(ImageFlag);
    }

    public BookDescription(Parcel in){

        BookName = in.readString();
        Cost = in.readString();
        Genre = in.readString();
        Image = in.readString();
        UserID = in.readString();
        Description = in.readString();
        ID = in.readString();
        ImageFlag = in.readString();
    }

}
