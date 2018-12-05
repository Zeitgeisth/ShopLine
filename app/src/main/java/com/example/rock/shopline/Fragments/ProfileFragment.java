package com.example.rock.shopline.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.R;
import com.example.rock.shopline.RecyclerViews.MyFavAdapter;
import com.example.rock.shopline.RecyclerViews.MyProfileBookAdapter;
import com.example.rock.shopline.RegisterActivity;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.GetBook;
import com.example.rock.shopline.data.GetUser;

import java.util.ArrayList;

public class ProfileFragment extends android.support.v4.app.Fragment {
    GetUser getUser;
    TextView name, phone, email, book, Favourites, Location;
    ProgressBar profileProgress, favouriteProgress;
    GetBook getBook;
    RecyclerView myBooksRecycler, myFavBooksRecycler;
    MyProfileBookAdapter myProfileBookAdapter;
    MyFavAdapter myFavBookAdapter;
    ImageButton EditInfo;
    UserDescription userDescription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_me, null);
        name = view.findViewById(R.id.FullName);
        phone = view.findViewById(R.id.Phone);
        email = view.findViewById(R.id.Email);
        book = view.findViewById(R.id.Books);
        profileProgress = view.findViewById(R.id.profileProgress);
        myBooksRecycler = view.findViewById(R.id.myBookRecycler);
        Favourites = view.findViewById(R.id.Favourite);
        favouriteProgress = view.findViewById(R.id.FavProgress);
        myFavBooksRecycler = view.findViewById(R.id.FavRecycler);
        Location = view.findViewById(R.id.Location);
        EditInfo = view.findViewById(R.id.MyEdit);

        userDescription = new UserDescription();

        getBook = new GetBook(getContext());

        getUser = new GetUser(getContext());

        EditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.putExtra("EditInfo", true);
                intent.putExtra("MyInfo", userDescription);
                startActivity(intent);
            }
        });

        final getMeInterface getMeInterface = new getMeInterface() {
            @Override
            public void success(boolean success) {
                if(success){
                    userDescription = getUser.getUserDescription();
                    name.setText(userDescription.getFirstName()+" "+userDescription.getLastName());
                    phone.setText(userDescription.getPhone());
                    email.setText(userDescription.getEmail());
                    Location.setText(userDescription.getLocation());
                    if(userDescription.getBook() != null){
                        if(userDescription.getBook().length == 1){
                            book.setText("You Have "+userDescription.getBook().length+ " " + "book");
                        }
                        else {
                            book.setText("You Have "+userDescription.getBook().length+ " " + "books");
                        }
                    }


                    if(userDescription.getFavBooks() != null){
                        if(userDescription.getFavBooks().length == 1){
                            Favourites.setText("You Have "+userDescription.getFavBooks().length+ " " + "favourite");
                        }
                        else Favourites.setText("You Have "+userDescription.getFavBooks().length+ " " + "favourites");

                    }


                    final ProfileFragment.getMeInterface myInterface = new getMeInterface() {
                        @Override
                        public void success(boolean success) {
                            profileProgress.setVisibility(View.GONE);
                            ArrayList<BookDescription>myBooks = getBook.getBooks();
                            myBooksRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            myProfileBookAdapter = new MyProfileBookAdapter(getActivity(), myBooks);
                            myBooksRecycler.setAdapter(myProfileBookAdapter);
                        }
                    };

                    profileProgress.setVisibility(View.VISIBLE);
                    getBook.getMyBook(myInterface, Constants.GETMYBOOKS);


                    final ProfileFragment.getMeInterface getFavInterface = new getMeInterface() {
                        @Override
                        public void success(boolean success) {
                            favouriteProgress.setVisibility(View.GONE);
                            ArrayList<BookDescription>myBooks = getBook.getAllFavBooks();
                            myFavBooksRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            myFavBookAdapter = new MyFavAdapter(getActivity(), myBooks);
                            myFavBooksRecycler.setAdapter(myFavBookAdapter);
                        }
                    };
                    favouriteProgress.setVisibility(View.VISIBLE);
                    getBook.getMyFavBooks(getFavInterface, Constants.MYFAVBOOKS);

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
