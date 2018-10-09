package com.example.rock.shopline.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.R;
import com.example.rock.shopline.data.GetUser;

/**
 * Created by rock on 10/5/2018.
 */

public class ProfileFragment extends android.support.v4.app.Fragment {
    GetUser getUser;
    TextView name, phone, email, book;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_me, null);
        name = view.findViewById(R.id.FullName);
        phone = view.findViewById(R.id.Phone);
        email = view.findViewById(R.id.Email);
        book = view.findViewById(R.id.Books);

        getUser = new GetUser(getContext());
        getMeInterface getMeInterface = new getMeInterface() {
            @Override
            public void success(boolean success) {
                if(success){
                    UserDescription userDescription = getUser.getUserDescription();
                    name.setText(userDescription.getFirstName()+" "+userDescription.getLastName());
                    phone.setText(userDescription.getPhone());
                    email.setText(userDescription.getEmail());
                    book.setText(userDescription.getBook());

                }

            }
        };
        getUser.getMeUser(getMeInterface);




        return view;
    }


    public interface getMeInterface{
        void success(boolean success);
    }
}
