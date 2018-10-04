package com.example.rock.shopline.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rock.shopline.R;

public class HomeBookViewHolder extends RecyclerView.ViewHolder {
    public ImageView bookImage;
    public TextView bookName,Cost;

    public HomeBookViewHolder(View itemView) {
        super(itemView);

        bookImage = itemView.findViewById(R.id.BookImage);
        bookName = itemView.findViewById(R.id.BookName);
        Cost = itemView.findViewById(R.id.Cost);
    }
}
