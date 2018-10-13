package com.example.rock.shopline;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddBookActivity extends AppCompatActivity {
    EditText BookName, Cost, Genre, Description;
    ImageView BookImage;
    Button Submit;
    Bitmap Image;
    public static final int REQUEST_GALLERY = 100;
    public static final int REQUEST_CAMERA = 101;
    Uri selectedImageUri;
    AddBook addBook;
    Uri cameraImageUri;
    ProgressBar bookProgress;

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
        Description = findViewById(R.id.description);
        bookProgress = findViewById(R.id.bookProgress);
        bookProgress.setVisibility(View.GONE);

        addBook = new AddBook();

        BookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickerDialog();
            }
        });
        final BookInterface bookInterface = new BookInterface() {
            @Override
            public void success(boolean success) {
                if (success) {
                    bookProgress.setVisibility(View.GONE);

                    Intent intent = new Intent(AddBookActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
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
                bookDescription.setDescription(Description.getText().toString());

                if(validateBook()){
                    bookProgress.setVisibility(View.VISIBLE);
                    Submit.setVisibility(View.GONE);
                    addBook.PostAddBook(bookDescription, bookInterface, getBaseContext());
                }


            }
        });

    }

    private void showPickerDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Image upload");
        dialog.setMessage("Select your option for uploading");
        dialog.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent pictureIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (pictureIntent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(pictureIntent, REQUEST_GALLERY);

            }
        });
        dialog.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //File to save full quality image
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureName = getPictureName();
                File imageFile = new File(pictureDirectory, pictureName);
              //  cameraImageUri = Uri.fromFile(imageFile);
                cameraImageUri = FileProvider.getUriForFile(getApplicationContext(),  "com.example.rock.shopline.fileprovider", imageFile);
                cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                        cameraImageUri);
                cameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                if (cameraIntent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(cameraIntent, REQUEST_CAMERA);

            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            //TODO: Find alternative for getBitmap method. Out of memory exception
            try {
                Image = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                BookImage.setImageBitmap(Image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            try {
                Image = MediaStore.Images.Media.getBitmap(getContentResolver(), cameraImageUri);
                //int THUMBSIZE = 500;
                //Bitmap thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(cameraImageUri.getPath()),100,100);
                BookImage.setImageBitmap(Image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String ImagetoString(Bitmap bitmap) {
        if (bitmap != null) {
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bArray = bos.toByteArray();
            return Base64.encodeToString(bArray, Base64.DEFAULT);
        } else {
            Toast.makeText(this, "Image is required", Toast.LENGTH_LONG);
        }


        return null;

    }

    private String getPictureName() {
        SimpleDateFormat tst = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH);
        String timestamp = tst.format(new Date());
        return timestamp + ".jpg";
    }

    public boolean validateBook()
    {
        if(Image == null)
        {
            Toast.makeText(this, "Image is Required", Toast.LENGTH_SHORT);
            return false;
        }
        if(BookName.getText().toString().trim().equalsIgnoreCase(""))
        {
            BookName.setError("BookName is Required");
            return false;
        }
        if(Cost.getText().toString().trim().equalsIgnoreCase(""))
        {
            Cost.setError("Cost is Required");
            return false;
        }
        if(Genre.getText().toString().trim().equalsIgnoreCase(""))
        {
            Genre.setError("BookName is Required");
            return false;
        }
        if(Description.getText().toString().trim().equalsIgnoreCase(""))
        {
            Description.setError("Description is Required");
            return false;
        }


        return true;
    }

    public interface BookInterface {
        void success(boolean success);
    }
}
