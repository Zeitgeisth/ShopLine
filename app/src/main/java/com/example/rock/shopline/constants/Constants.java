package com.example.rock.shopline.constants;

/**
 * Created by rock on 8/13/2018.
 */

public class Constants {
    public static final String IPconfig = "http://192.168.100.45:3005";
    public static final String REGISTERURL = IPconfig + "/API/Shopline/Register/newUser";
    public static final String LOGINURL = IPconfig + "/API/Shopline/Login/User";
    public static final String POSTBOOK = IPconfig + "/API/Shopline/Add/Book";
    public static String AuthToken;
    public static final String GETALLBOOK =IPconfig + "/API/Shopline/HomePage/All";
    public static final String GETME = IPconfig + "/API/Shopline/Users/me";
    public static final String GETUSER = IPconfig + "/API/Shopline/Users/user";
    public static final String GETUSERBOOKS = IPconfig + "/API/Shopline/Books/book";
    public static final String GETMYBOOKS = IPconfig + "/API/Shopline/Books/myBook";
    public static final String ADDMYFAVOURITES = IPconfig + "/API/Shopline/add/Favourites";
    public static final String EDITBOOK = IPconfig + "/API/Shopline/add/EditBook";
    public static final String MYFAVBOOKS = IPconfig + "/API/Shopline/Books/myFav";
}
