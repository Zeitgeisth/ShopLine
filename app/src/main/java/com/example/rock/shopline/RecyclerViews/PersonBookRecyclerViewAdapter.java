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

import com.androidquery.AQuery;
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
    AQuery aQuery;
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
        aQuery = new AQuery(context);
        final BookDescription book = books.get(position);
        holder.bookName.setText(book.getBookName());
        holder.Cost.setText("Rs."+book.getCost());
        aQuery.id(holder.bookImage).image(Constants.IPconfig + "/uploads/"+book.getImage(),true,true);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }



}

