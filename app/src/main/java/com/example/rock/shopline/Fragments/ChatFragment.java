package com.example.rock.shopline.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.R;
import com.example.rock.shopline.data.GetMessage;
import com.example.rock.shopline.data.GetUser;

/**
 * Created by rock on 10/9/2018.
 */

public class ChatFragment extends Fragment {
    RecyclerView ChatList;
    GetMessage getMessage;
    UserDescription userDescription;
    GetUser getUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.chatlist, null);
        getMessage = new GetMessage();
        getUser = new GetUser(getContext());
        ProfileFragment.getMeInterface getMeInterfaces = new ProfileFragment.getMeInterface() {
            @Override
            public void success(boolean success) {
                userDescription = getUser.getUserDescription();
                Log.i("abcdef",userDescription.getId());

            }
        } ;

        getUser.getMeUser(getMeInterfaces);


        getMessageInterface messageInterface = new getMessageInterface() {
            @Override
            public void success(boolean success) {

            }
        };

//        getMessage.GetMsg(getContext(),meDescription.getId(),messageInterface);
        return view;
    }

    public interface getMessageInterface{
        void success(boolean success);
    }

}
