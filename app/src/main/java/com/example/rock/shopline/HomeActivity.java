package com.example.rock.shopline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.RecyclerViews.HomeBookRecyclerViewAdapter;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.AddBook;
import com.example.rock.shopline.data.AuthService;
import com.example.rock.shopline.data.GetBook;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ImageButton Add;
    ProgressBar progressBar;
    RecyclerView homeBooks;
    HomeBookRecyclerViewAdapter adapter;
    ArrayList<BookDescription>booklist;
    GetBook getBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeBooks = findViewById(R.id.homeRecycler);
        homeBooks.setHasFixedSize(true);
        getBook = new GetBook(this);
        progressBar = findViewById(R.id.homeProgress);
        progressBar.setVisibility(View.VISIBLE);


        ShowBooks showBooks = new ShowBooks() {
            @Override
            public void success(boolean success) {
                      if(success){
                          progressBar.setVisibility(View.INVISIBLE);
                          booklist = getBook.getBooks();
                          homeBooks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                          adapter = new HomeBookRecyclerViewAdapter(getApplicationContext(), booklist);
                          homeBooks.setAdapter(adapter);
                      }
            }
        };

        getBook.getBook(showBooks);


        Add = findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AddBookActivity.class);
                startActivity(intent);

            }
        });


    }

    public interface ShowBooks{
        void success(boolean success);

    }
}
