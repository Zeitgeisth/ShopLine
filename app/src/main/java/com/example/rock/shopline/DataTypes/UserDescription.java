package com.example.rock.shopline.DataTypes;

/**
 * Created by rock on 9/30/2018.
 */

public class UserDescription {
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
}
