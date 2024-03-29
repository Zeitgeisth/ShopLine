package com.example.rock.shopline.RecyclerViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rock.shopline.DataTypes.ChatType;
import com.example.rock.shopline.DataTypes.MessageDetails;
import com.example.rock.shopline.R;

import java.util.ArrayList;

/**
 * Created by rock on 11/30/2018.
 */


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;

    public ChatAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    ArrayList arrayList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new user1ViewHolder(LayoutInflater.from(context).inflate(R.layout.message_user1, parent, false));
            case 1:
                return new user2ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.message_user2_icon, parent, false));
            case 2:
                return new user2NoImageViewHolder(LayoutInflater.from(context).inflate(R.layout.message_user2_noicon, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageDetails detail = (MessageDetails) arrayList.get(position);
        Log.i("msg",detail.getUserType()+"");
        
        switch (holder.getItemViewType()) {
            case 0:
                user1ViewHolder holder1 = (user1ViewHolder) holder;
                holder1.textViewHome.setText(detail.getMessage());
                break;
            case 1:
                user2ImageViewHolder holder2 = (user2ImageViewHolder) holder;
                holder2.name.setText(detail.getName());
                holder2.image.setBackgroundResource(R.mipmap.user);
                holder2.text.setText(detail.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (((MessageDetails) arrayList.get(position)).getUserType() == ChatType.type.USER1)
            return 0;
        else if (((MessageDetails) arrayList.get(position)).getUserType() == ChatType.type.USER2_IMG)
            return 1;
        else
            return 2;

    }

    /*
     *
     * View holder classes
     *
     * */
    class user1ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHome;

        public user1ViewHolder(View itemView) {
            super(itemView);
            textViewHome = itemView.findViewById(R.id.user1_msg_text);
        }
    }

    class user2NoImageViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public user2NoImageViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.user2_msg);
        }
    }


    class user2ImageViewHolder extends RecyclerView.ViewHolder {
        TextView text, name;
        View image;

        public user2ImageViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.user2_msg);
            name = itemView.findViewById(R.id.user2_name);
            image = itemView.findViewById(R.id.user2_image);
        }
    }
}





