package com.example.rock.shopline.RecyclerViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.R;
import com.example.rock.shopline.ViewHolders.MyProfileBookHolder;
import com.example.rock.shopline.constants.Constants;

import java.util.ArrayList;

/**
 * Created by rock on 10/12/2018.
 */

public class MyProfileBookAdapter extends RecyclerView.Adapter<MyProfileBookHolder> {
    Context context;

    public MyProfileBookAdapter(Context context, ArrayList<BookDescription> bookDescriptions) {
        this.context = context;
        this.bookDescriptions = bookDescriptions;
    }

    ArrayList<BookDescription> bookDescriptions;

    @NonNull
    @Override
    public MyProfileBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myprofileadapter,null,false);
        MyProfileBookHolder bookHolder = new MyProfileBookHolder(view);
        return bookHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyProfileBookHolder holder, int position) {
           BookDescription bookDescription = bookDescriptions.get(position);
           Glide.with(context).load(Constants.IPconfig + "/uploads/"+bookDescription.getImage()).into(holder.imageView);
           holder.BookName.setText(bookDescription.getBookName());
    }

    @Override
    public int getItemCount() {
        return bookDescriptions.size();
    }
}
