package com.example.rock.shopline.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rock.shopline.R;

/**
 * Created by rock on 10/12/2018.
 */

public class MyProfileBookHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView BookName;
    public Button edit, delete;
    public MyProfileBookHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.BookImage);
        BookName = itemView.findViewById(R.id.BookName);
        edit = itemView.findViewById(R.id.Edit);
        delete = itemView.findViewById(R.id.Delete);
    }
}
