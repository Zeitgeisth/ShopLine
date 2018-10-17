package com.example.rock.shopline.RecyclerViews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.R;
import com.example.rock.shopline.ViewHolders.MyProfileBookHolder;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.DeleteBook;

import java.util.ArrayList;

/**
 * Created by rock on 10/13/2018.
 */

public class MyFavAdapter extends RecyclerView.Adapter<MyProfileBookHolder> {
    Context context;
    DeleteBook deleteBook;

    public MyFavAdapter(Context context, ArrayList<BookDescription> bookDescriptions) {
        this.context = context;
        this.bookDescriptions = bookDescriptions;
    }

    ArrayList<BookDescription> bookDescriptions;

    @NonNull
    @Override
    public MyProfileBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myfavadapter,null,false);
        MyProfileBookHolder bookHolder = new MyProfileBookHolder(view);
        return bookHolder;
    }
    HomeActivity.ShowBooks favListener = new HomeActivity.ShowBooks() {
        @Override
        public void success(boolean success) {
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    };

    @Override
    public void onBindViewHolder(@NonNull MyProfileBookHolder holder, int position) {
        final BookDescription bookDescription = bookDescriptions.get(position);
        Glide.with(context).load(Constants.IPconfig + "/uploads/"+bookDescription.getImage()).into(holder.imageView);
        holder.BookName.setText(bookDescription.getBookName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);
                dialog.setTitle("Delete Book");
                dialog.setMessage("Are You Sure You Want to Delete " + bookDescription.getBookName());
                dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteBook = new DeleteBook(context);
                        Log.i("abcde",""+bookDescription.getID());
                        deleteBook.deleteBook(Constants.REMOVEFAV, bookDescription.getID(), favListener);
                    }
                });

                dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return bookDescriptions.size();
    }
}
