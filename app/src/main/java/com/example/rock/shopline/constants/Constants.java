package com.example.rock.shopline.constants;

/**
 * Created by rock on 8/13/2018.
 */

public class Constants {
    public static final String IPconfig = "http://192.168.1.22:3005";
    public static final String REGISTERURL = IPconfig + "/API/Shopline/Register/newUser";
    public static final String LOGINURL = IPconfig + "/API/Shopline/Login/User";
    public static final String POSTBOOK = IPconfig + "/API/Shopline/Add/Book";
    public static String AuthToken;
    public static final String GETALLBOOK =IPconfig + "/API/Shopline/HomePage/All";
    public static final String GETME = IPconfig + "/API/Shopline/Users/me";
    public static final String GETUSER = IPconfig + "/API/Shopline/Users/user";
    public static final String GETUSERBOOKS = IPconfig + "/API/Shopline/Books/book";
}
