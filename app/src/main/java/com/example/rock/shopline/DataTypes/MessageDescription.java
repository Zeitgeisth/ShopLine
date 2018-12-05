package com.example.rock.shopline.DataTypes;

import java.util.ArrayList;

/**
 * Created by rock on 12/5/2018.
 */

public class MessageDescription {
    String ID;
    String Room;

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

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
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

    String RoomId;
    String Message;
    ArrayList<String>userId;
    ArrayList<String>Names;
}
