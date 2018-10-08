package com.example.rock.shopline.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidquery.AQuery;
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
    AQuery aQuery;
    public HomeBookRecyclerViewAdapter(Context context, ArrayList books) {
        this.context = context;
        this.books = books;
    }


    @NonNull
    @Override
    public HomeBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homebooks,null);
        HomeBookViewHolder viewHolder = new HomeBookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBookViewHolder holder, int position) {
        aQuery = new AQuery(context);
        final BookDescription book = books.get(position);
        holder.bookName.setText(book.getBookName());
        holder.Cost.setText("Rs."+book.getCost());
        aQuery.id(holder.bookImage).image(Constants.IPconfig + "/uploads/"+book.getImage(),true,true);

        holder.bookImage.setOnClickListener(new View.OnClickListener() {
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
