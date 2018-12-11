package com.example.rock.shopline.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by rock on 12/5/2018.
 */

public class MessageDescription implements Parcelable {
    String ID;
    String Room;

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public Boolean getSeen() {
        return Seen;
    }

    public void setSeen(Boolean seen) {
        Seen = seen;
    }

    String Sender;
    Boolean Seen;

    public MessageDescription(Parcel in) {
        ID = in.readString();
        Room = in.readString();
        RoomId = in.readString();
        Sender = in.readString();
        Seen = Boolean.valueOf(in.readString());
        userId = in.createStringArrayList();
        Names = in.createStringArrayList();
    }

    public static final Creator<MessageDescription> CREATOR = new Creator<MessageDescription>() {
        @Override
        public MessageDescription createFromParcel(Parcel in) {
            return new MessageDescription(in);
        }

        @Override
        public MessageDescription[] newArray(int size) {
            return new MessageDescription[size];
        }
    };

    public MessageDescription() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }


    public ArrayList<String> getUserId() {
        return userId;
    }

    public void setUserId(ArrayList<String> userId) {
        this.userId = userId;
    }

    public ArrayList<String> getNames() {
        return Names;
    }

    public void setNames(ArrayList<String> names) {
        Names = names;
    }

    public ChatType.type getUserType() {
        return userType;
    }

    public void setUserType(ChatType.type userType) {
        this.userType = userType;
    }

    private ChatType.type userType;

    String RoomId;
    ArrayList<String>userId;
    ArrayList<String>Names;

    public ArrayList<MessageDetails> getMessageDetail() {
        return MessageDetail;
    }

    public void setMessageDetail(ArrayList<MessageDetails> messageDetail) {
        MessageDetail = messageDetail;
    }

    ArrayList<MessageDetails>MessageDetail;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeString(Room);
        parcel.writeString(RoomId);
        parcel.writeStringList(userId);
        parcel.writeStringList(Names);
        parcel.writeString(Sender);
        parcel.writeString(String.valueOf(Seen));
    }
}
