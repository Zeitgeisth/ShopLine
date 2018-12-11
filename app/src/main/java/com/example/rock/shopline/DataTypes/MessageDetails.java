package com.example.rock.shopline.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rock on 12/7/2018.
 */

public class MessageDetails implements Parcelable  {
    String Name;

    public MessageDetails(Parcel in) {
        Name = in.readString();
        Message = in.readString();
        Date = in.readString();
        userType = (ChatType.type) in.readValue(ChatType.class.getClassLoader());
    }

    public static final Creator<MessageDetails> CREATOR = new Creator<MessageDetails>() {
        @Override
        public MessageDetails createFromParcel(Parcel in) {
            return new MessageDetails(in);
        }

        @Override
        public MessageDetails[] newArray(int size) {
            return new MessageDetails[size];
        }
    };

    public MessageDetails() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ChatType.type getUserType() {
        return userType;
    }

    public void setUserType(ChatType.type userType) {
        this.userType = userType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String Message;
    private ChatType.type userType;
    String Date;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Message);
        parcel.writeString(Date);
        parcel.writeValue(userType);
    }
}
