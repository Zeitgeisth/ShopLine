package com.example.rock.shopline.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DetailBookActivity;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.R;
import com.example.rock.shopline.ViewHolders.HomeBookViewHolder;
import com.example.rock.shopline.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rock on 9/28/2018.
 */

public class HomeBookRecyclerViewAdapter extends RecyclerView.Adapter<HomeBookViewHolder> {
    Bitmap image;
    Context context;
    ArrayList<BookDescription>books;
    public HomeBookRecyclerViewAdapter(Context context, ArrayList books) {
        this.context = context;
        this.books = books;
    }


    @NonNull
    @Override
    public HomeBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homebooks,null, false);
        HomeBookViewHolder viewHolder = new HomeBookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBookViewHolder holder, int position) {
        final BookDescription book = books.get(position);
        holder.bookName.setText(book.getBookName());
        holder.Cost.setText("Rs."+book.getCost());
        holder.Category.setText("Genre: " + book.getGenre());
        Glide.with(context).load(Constants.IPconfig + "/uploads/"+book.getImage())
                .into(holder.bookImage);

        holder.clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBookActivity.class);
                intent.putExtra("BookDetail", book);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }


}
