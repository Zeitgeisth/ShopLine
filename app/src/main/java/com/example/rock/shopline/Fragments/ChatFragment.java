package com.example.rock.shopline.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rock.shopline.DataTypes.MessageDescription;
import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.R;
import com.example.rock.shopline.RecyclerViews.ChatAdapter;
import com.example.rock.shopline.RecyclerViews.ChatListAdapter;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.GetMessage;
import com.example.rock.shopline.data.GetUser;

import java.util.ArrayList;


public class ChatFragment extends Fragment {
    RecyclerView ChatList;
    GetMessage getMessage;
    UserDescription userDescription;
    GetUser getUser;
    ArrayList<String>Names;
    ChatListAdapter chatListAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.chatlist, null);
        ChatList = view.findViewById(R.id.ChatListRecycler);
        getMessage = new GetMessage();
        getUser = new GetUser(getContext());
        ProfileFragment.getMeInterface getMeInterfaces = new ProfileFragment.getMeInterface() {
            @Override
            public void success(boolean success) {
                userDescription = getUser.getMeUserDescription();
                Constants.MyName = userDescription.getFirstName()+" "+userDescription.getLastName();
                Constants.MyEmail = userDescription.getEmail();


                getMessageInterface messageInterface = new getMessageInterface() {
                    @Override
                    public void success(boolean success) {
                        ArrayList<MessageDescription> messageDescription = getMessage.getMessageDescription();

                            Names = new ArrayList<>();
                            MessageDescription messageDescription1 = null;
                            for(int i = 0;i<messageDescription.size();i++){
                                messageDescription1 = messageDescription.get(i);
                                ArrayList<String>name = messageDescription1.getNames();
                                    for(int j=0;j<2;j++) {
                                        if (Constants.MyName.equals(name.get(j))) {
                                            continue;
                                        } else {
                                            Names.add(name.get(j));
                                        }

                                    }

                            }

                            ChatList.setHasFixedSize(true);
                            chatListAdapter = new ChatListAdapter(Names, getActivity(), messageDescription);
                            ChatList.setLayoutManager(new LinearLayoutManager(getActivity()));
                            ChatList.setAdapter(chatListAdapter);


                    }
                };

                getMessage.GetMsg(getContext(),userDescription.getId(),messageInterface);

            }
        } ;

        getUser.getMeUser(getMeInterfaces);



        return view;
    }

    public interface getMessageInterface{
        void success(boolean success);
    }

}
