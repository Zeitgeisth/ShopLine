package com.example.rock.shopline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.RecyclerViews.HomeBookRecyclerViewAdapter;
import com.example.rock.shopline.RecyclerViews.PersonBookRecyclerViewAdapter;
import com.example.rock.shopline.data.GetPersonBook;
import com.example.rock.shopline.data.GetUser;

import java.util.ArrayList;

public class PersonBookActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    GetPersonBook getPersonBook;
    ArrayList<BookDescription>booklist = new ArrayList<>();
    PersonBookRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_book);
        progressBar = findViewById(R.id.homeProgress);
        recyclerView = findViewById(R.id.homeRecycler);
        progressBar = findViewById(R.id.homeProgress);
        progressBar.setVisibility(View.VISIBLE);
        getPersonBook = new GetPersonBook(this);

        String Id = getIntent().getStringExtra("Id");

        BookInterface bookInterface = new BookInterface() {
            @Override
            public void success(Boolean success) {
                if(success){
                    progressBar.setVisibility(View.INVISIBLE);
                    booklist = getPersonBook.getBooks();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new PersonBookRecyclerViewAdapter(getApplicationContext(), booklist);
                    recyclerView.setAdapter(adapter);
                }
            }
        };

        getPersonBook.getBook(bookInterface,Id);








    }

    public interface BookInterface {
        void success(Boolean success);
    }
}
