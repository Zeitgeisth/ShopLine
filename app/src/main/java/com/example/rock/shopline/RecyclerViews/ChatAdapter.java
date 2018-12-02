package com.example.rock.shopline.RecyclerViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rock.shopline.DataTypes.ChatDescription;
import com.example.rock.shopline.R;

import java.util.ArrayList;

/**
 * Created by rock on 11/30/2018.
 */


    public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
        Context context;

        public ChatAdapter(Context context, ArrayList arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        ArrayList arrayList;
        @NonNull
        @Override
        public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.msgadapter, null);
            ChatViewHolder holder = new ChatViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
            String beforeName = " ";
            ChatDescription chatDescription = (ChatDescription) arrayList.get(position);
            if(position>0) {
                ChatDescription chatDescriptionbefore = (ChatDescription) arrayList.get(position - 1);
                beforeName = chatDescriptionbefore.getName();
            }

            holder.name.setText(chatDescription.getName());
            if(beforeName == chatDescription.getName()){
                holder.name.setVisibility(View.GONE);

            }else{
                holder.name.setVisibility(View.VISIBLE);
            }
                holder.textViewHome.setText(chatDescription.getMsg());

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ChatViewHolder extends RecyclerView.ViewHolder{
            TextView textViewHome,name;
            public ChatViewHolder(View itemView) {
                super(itemView);

                textViewHome = itemView.findViewById(R.id.TextVeiwwHome);
                name = itemView.findViewById(R.id.Name);
            }
        }

    }




