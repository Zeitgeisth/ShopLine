package com.example.rock.shopline.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rock on 11/30/2018.
 */



public class ChatDescription implements Parcelable {
    private String name;

    public ChatDescription(Parcel in) {
        name = in.readString();
        msg = in.readString();
    }

    public static final Creator<ChatDescription> CREATOR = new Creator<ChatDescription>() {
        @Override
        public ChatDescription createFromParcel(Parcel in) {
            return new ChatDescription(in);
        }

        @Override
        public ChatDescription[] newArray(int size) {
            return new ChatDescription[size];
        }
    };

    public ChatDescription() {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(msg);
    }
}
