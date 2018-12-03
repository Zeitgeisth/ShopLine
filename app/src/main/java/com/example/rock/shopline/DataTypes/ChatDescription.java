package com.example.rock.shopline.DataTypes;

/**
 * Created by rock on 11/30/2018.
 */



public class ChatDescription {
    private String name;

    public ChatType.type getUserType() {
        return userType;
    }

    public void setUserType(ChatType.type userType) {
        this.userType = userType;
    }

    private ChatType.type userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

}
