package com.example.rock.shopline.RecyclerViews;

/**
 * Created by rock on 10/3/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DetailBookActivity;
import com.example.rock.shopline.R;
import com.example.rock.shopline.ViewHolders.HomeBookViewHolder;
import com.example.rock.shopline.constants.Constants;

import java.util.ArrayList;
public class PersonBookRecyclerViewAdapter extends RecyclerView.Adapter<HomeBookViewHolder> {
    Bitmap image;
    Context context;
    ArrayList<BookDescription> books;
    public PersonBookRecyclerViewAdapter(Context context, ArrayList books) {
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
        final BookDescription book = books.get(position);
        holder.bookName.setText(book.getBookName());
        holder.Cost.setText("Rs."+book.getCost());
        Glide.with(context).load(Constants.IPconfig + "/uploads/"+book.getImage()).into(holder.bookImage);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }



}

