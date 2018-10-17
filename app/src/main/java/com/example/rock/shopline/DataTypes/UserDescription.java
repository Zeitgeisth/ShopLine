package com.example.rock.shopline.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rock on 9/30/2018.
 */

public class UserDescription implements Parcelable {
    public UserDescription(Parcel in) {
        FirstName = in.readString();
        LastName = in.readString();
        Phone = in.readString();
        Email = in.readString();
        FavBooks = in.createStringArray();
        Book = in.createStringArray();
        Location = in.readString();
        Id = in.readString();
    }

    public static final Creator<UserDescription> CREATOR = new Creator<UserDescription>() {
        @Override
        public UserDescription createFromParcel(Parcel in) {
            return new UserDescription(in);
        }

        @Override
        public UserDescription[] newArray(int size) {
            return new UserDescription[size];
        }
    };

    public UserDescription() {

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String FirstName;
    private String LastName;
    private String Phone;
    private String Email;
    private String FavBooks[];

    public String[] getBook() {
        return Book;
    }

    public void setBook(String[] book) {
        Book = book;
    }

    private String Book[];

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    private String Location;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String Id;

    public String[] getFavBooks() {
        return FavBooks;
    }

    public void setFavBooks(String[] favBooks) {
        FavBooks = favBooks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(FirstName);
        parcel.writeString(LastName);
        parcel.writeString(Phone);
        parcel.writeString(Email);
        parcel.writeStringArray(FavBooks);
        parcel.writeStringArray(Book);
        parcel.writeString(Location);
        parcel.writeString(Id);
    }
}
