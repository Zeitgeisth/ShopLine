package com.example.rock.shopline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.Fragments.ProfileFragment;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.AddBook;
import com.example.rock.shopline.data.GetUser;

public class DetailBookActivity extends AppCompatActivity {
    BookDescription bookDetail;
    TextView BookName,Genre,Cost,Name,Email,Phone,ownerName,Description,Location;
    ImageView BookImage;
    GetUser getUser;
    UserDescription userDescription;
    Button favourites, enquire;
    AddBook addBook;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        userDescription = new UserDescription();
        getUser = new GetUser(this);
        addBook = new AddBook();
        BookName = findViewById(R.id.BookName);
        Genre = findViewById(R.id.Genre);
        Cost = findViewById(R.id.Cost);
        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Phone = findViewById(R.id.Phone);
        BookImage = findViewById(R.id.BookImage);
        ownerName = findViewById(R.id.ownerName);
        Description = findViewById(R.id.Description);
        favourites = findViewById(R.id.addtofavourites);
        enquire = findViewById(R.id.enquire);
        Location = findViewById(R.id.Location);


        bookDetail = getIntent().getParcelableExtra("BookDetail");

        Glide.with(this).load(Constants.IPconfig + "/uploads/"+bookDetail.getImage()).into(BookImage);
        BookName.setText(bookDetail.getBookName());
        Genre.setText(bookDetail.getGenre());
        Cost.setText("Rs."+bookDetail.getCost());
        Description.setText(bookDetail.getDescription());

        getUser inUser = new getUser() {
            @Override
            public void success(boolean success) {
                 userDescription = getUser.getUserDescription();
                Name.setText(userDescription.getFirstName()+" "+userDescription.getLastName());
                Email.setText(userDescription.getEmail());
                Phone.setText(userDescription.getPhone());
                Location.setText(userDescription.getLocation());
                ownerName.setText("<< View more of "+userDescription.getFirstName()+" >>");
            }
        };
        String User = bookDetail.getUserID();
        getUser.getUser(inUser, User);

        ownerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBookActivity.this, PersonBookActivity.class);
                intent.putExtra("Id",userDescription.getId());
                intent.putExtra("UserInfo",userDescription);
                startActivity(intent);
            }
        });

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  addBook.AddFavourites(getBaseContext(),bookDetail.getID() );
            }
        });

        enquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUser = new GetUser(getApplicationContext());
                final ProfileFragment.getMeInterface getMeInterface2 = new ProfileFragment.getMeInterface() {
                    @Override
                    public void success(boolean success) {
                        userDescription = getUser.getUserDescription();
                        Intent intent = new Intent(DetailBookActivity.this, ChatActivity.class);
                        intent.putExtra("Email", Email.getText());
                        intent.putExtra("homeEmail", userDescription.getEmail());
                        intent.putExtra("awayName", Name.getText() );
                        intent.putExtra("homeName", userDescription.getFirstName() + " " + userDescription.getLastName());

                        startActivity(intent);
                    }
                };
                getUser.getMeUser(getMeInterface2);

            }
        });

    }

    public interface getUser{
        void success(boolean success);

    }

}
