package com.example.rock.shopline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.data.GetUser;

public class DetailBookActivity extends AppCompatActivity {

    AQuery aQuery;
    BookDescription bookDetail;
    TextView BookName,Genre,Cost,Name,Email,Phone,ownerName;
    ImageView BookImage;
    GetUser getUser;
    UserDescription userDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        userDescription = new UserDescription();
        getUser = new GetUser(this);
        aQuery = new AQuery(this);
        BookName = findViewById(R.id.BookName);
        Genre = findViewById(R.id.Genre);
        Cost = findViewById(R.id.Cost);
        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Phone = findViewById(R.id.Phone);
        BookImage = findViewById(R.id.BookImage);
        ownerName = findViewById(R.id.ownerName);


        bookDetail = getIntent().getParcelableExtra("BookDetail");

        aQuery.id(BookImage).image("http://192.168.1.27:3005/uploads/"+bookDetail.getImage(),true,true);
        BookName.setText(bookDetail.getBookName());
        Genre.setText(bookDetail.getGenre());
        Cost.setText("Rs."+bookDetail.getCost());

        getUser inUser = new getUser() {
            @Override
            public void success(boolean success) {
                 userDescription = getUser.getUserDescription();
                Name.setText(userDescription.getFirstName()+" "+userDescription.getLastName());
                Email.setText(userDescription.getEmail());
                Phone.setText(userDescription.getPhone());
                ownerName.setText(userDescription.getFirstName()+"?");

            }
        };
        String User = bookDetail.getUserID();
        getUser.getUser(inUser, User);

        ownerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBookActivity.this, PersonBookActivity.class);
                intent.putExtra("Id",userDescription.getId());
                startActivity(intent);
            }
        });

    }

    public interface getUser{
        void success(boolean success);

    }

}
