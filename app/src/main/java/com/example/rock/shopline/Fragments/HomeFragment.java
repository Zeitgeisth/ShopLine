package com.example.rock.shopline.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rock.shopline.AddBookActivity;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.R;
import com.example.rock.shopline.RecyclerViews.HomeBookRecyclerViewAdapter;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.GetBook;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by rock on 10/5/2018.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    ProgressBar progressBar;
    RecyclerView homeBooks;
    HomeBookRecyclerViewAdapter adapter;
    ArrayList<BookDescription> booklist;
    GetBook getBook;
    SwipeRefreshLayout Refresh;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_home, null);

        homeBooks = view.findViewById(R.id.homeRecycler);
        homeBooks.setHasFixedSize(true);
        getBook = new GetBook(getActivity().getBaseContext());
        progressBar = view.findViewById(R.id.homeProgress);
        progressBar.setVisibility(View.VISIBLE);
        Refresh = view.findViewById(R.id.Refresh);


        homeBooks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


               if(dy>80){
                   ((HomeActivity)getActivity()).getLinearLayout().setVisibility(View.GONE);
              }
                else if(dy<-50){
                    ((HomeActivity)getActivity()).getLinearLayout().setVisibility(View.VISIBLE);
               }
            }
        });


        final HomeActivity.ShowBooks showBooks = new HomeActivity.ShowBooks() {
            @Override
            public void success(boolean success) {
                if(success){
                    progressBar.setVisibility(View.INVISIBLE);
                    booklist = getBook.getBooks();
                    homeBooks.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),3));
                    adapter = new HomeBookRecyclerViewAdapter(getActivity().getApplicationContext(), booklist);
                    homeBooks.setAdapter(adapter);
                    Refresh.setRefreshing(false);

                }
            }
        };
        Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBook.getBook(showBooks, Constants.GETALLBOOK);
                Toast.makeText(getActivity().getBaseContext(),"refreshed",Toast.LENGTH_LONG).show();
            }
        });


        getBook.getBook(showBooks, Constants.GETALLBOOK);




        return view;
    }
}
