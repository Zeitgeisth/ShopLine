package com.example.rock.shopline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.data.AddBook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AddBookActivity extends AppCompatActivity {
    EditText BookName,Cost,Genre;
    ImageView BookImage;
    Button Submit;
    Bitmap Image;
    public static final int REQUEST_GALLERY = 100;
    Uri selectedImageUri;
    AddBook addBook;

    BookDescription bookDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        bookDescription = new BookDescription();
        BookImage = findViewById(R.id.BookImage);
        BookName = findViewById(R.id.BookName);
        Cost = findViewById(R.id.Cost);
        Genre = findViewById(R.id.Genre);
        Submit = findViewById(R.id.Submit);

        addBook = new AddBook();

        BookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pictureIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(pictureIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(pictureIntent, REQUEST_GALLERY);
                }
            }
        });
        final BookInterface bookInterface = new BookInterface() {
            @Override
            public void success(boolean success) {
                if(success) {
                    Intent intent = new Intent(AddBookActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookDescription.setBookName(BookName.getText().toString());
                bookDescription.setCost(Cost.getText().toString());
                bookDescription.setGenre(Genre.getText().toString());
                bookDescription.setImage(ImagetoString(Image));
                Log.i("Image",""+ImagetoString(Image));

                addBook.PostAddBook(bookDescription, bookInterface, getBaseContext());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_GALLERY){

            if(resultCode == RESULT_OK){

                selectedImageUri = data.getData();
                try {
                    Image = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
                    BookImage.setImageBitmap(Image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }

    }
    private String ImagetoString(Bitmap bitmap) {
        if(bitmap!=null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bArray = bos.toByteArray();
            Log.i("Size", bArray.length + "");
            return Base64.encodeToString(bArray, Base64.DEFAULT);
        }
        else{
            Toast.makeText(this,"Image is required", Toast.LENGTH_LONG);
        }
        return null;

    }

    public interface BookInterface{
        void  success(boolean success);
    }
}
