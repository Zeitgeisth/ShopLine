package com.example.rock.shopline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.RecyclerViews.HomeBookRecyclerViewAdapter;
import com.example.rock.shopline.RecyclerViews.PersonBookRecyclerViewAdapter;
import com.example.rock.shopline.data.GetPersonBook;
import com.example.rock.shopline.data.GetUser;
import com.github.florent37.expansionpanel.ExpansionHeader;

import java.util.ArrayList;

public class PersonBookActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    GetPersonBook getPersonBook;
    ArrayList<BookDescription>booklist = new ArrayList<>();
    PersonBookRecyclerViewAdapter adapter;
    ExpansionHeader favHeader;
    TextView AboutYou,FullName,Email,Phone,Books,Location;
    ImageButton MyEdit;

    UserDescription userDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_me);
        progressBar = findViewById(R.id.profileProgress);
        recyclerView = findViewById(R.id.myBookRecycler);
        Location = findViewById(R.id.Location);
        Books = findViewById(R.id.Books);
        progressBar.setVisibility(View.VISIBLE);
        getPersonBook = new GetPersonBook(this);
        favHeader = findViewById(R.id.FavHeader);
        favHeader.setVisibility(View.GONE);
        MyEdit = findViewById(R.id.MyEdit);
        MyEdit.setVisibility(View.GONE);

        AboutYou = findViewById(R.id.AboutYou);
        AboutYou.setVisibility(View.GONE);

        FullName = findViewById(R.id.FullName);
        Phone = findViewById(R.id.Phone);
        Email = findViewById(R.id.Email);

        String Id = getIntent().getStringExtra("Id");
        userDescription = getIntent().getParcelableExtra("UserInfo");

        FullName.setText("Name: "+ userDescription.getFirstName()+" "+userDescription.getLastName());
        Phone.setText("Phone: "+ userDescription.getPhone());
        Email.setText("Email: "+ userDescription.getEmail());
        Location.setText("Location: "+userDescription.getLocation());

        Books.setText("See Books of "+ userDescription.getFirstName());

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
