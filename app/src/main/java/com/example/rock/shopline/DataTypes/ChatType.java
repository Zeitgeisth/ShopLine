package com.example.rock.shopline.DataTypes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 12/3/2018.
 */

public class ChatType implements Parcelable {
    protected ChatType(Parcel in) {
    }

    public static final Creator<ChatType> CREATOR = new Creator<ChatType>() {
        @Override
        public ChatType createFromParcel(Parcel in) {
            return new ChatType(in);
        }

        @Override
        public ChatType[] newArray(int size) {
            return new ChatType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public enum type{
        USER1, USER2_IMG, USER2_NOIMG
    }
}
