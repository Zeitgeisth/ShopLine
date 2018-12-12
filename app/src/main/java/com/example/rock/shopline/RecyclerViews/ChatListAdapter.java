package com.example.rock.shopline.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rock.shopline.ChatActivity;
import com.example.rock.shopline.DataTypes.MessageDescription;
import com.example.rock.shopline.DataTypes.MessageDetails;
import com.example.rock.shopline.DetailBookActivity;
import com.example.rock.shopline.Fragments.ChatFragment;
import com.example.rock.shopline.R;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.GetMessage;

import java.util.ArrayList;

/**
 * Created by rock on 12/5/2018.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder> {

    GetMessage getMessage;

    public ChatListAdapter(ArrayList<String> names, Context context, ArrayList<MessageDescription> messages) {
        this.names = names;
        this.context = context;
        this.messages = messages;
    }

    ArrayList<String>names;
    ArrayList<MessageDescription> messages;
    Context context;


    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlechatlist,null);
        ChatListViewHolder holder = new ChatListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListViewHolder holder, final int position) {
            final String name = names.get(position);



            holder.NameText.setText(name);



            MessageDescription messageDescription = messages.get(position);
            Log.i("abcefgh",messageDescription.getSeen()+"");
            if(Constants.MyName.equals(messageDescription.getSender())){
                holder.NameText.setTextColor(-16777216);
            }else{
                if(messageDescription.getSeen()==false){
                    holder.NameText.setTextColor(-16776961);
                }
            }


        String awayEmail = null;
        String email[] = messageDescription.getRoom().split(" ");
        for(int i =0; i<2 ;i++){
            if(email[i].equals(Constants.MyEmail)) {
                continue;
            }else awayEmail = email[i];
        }




        final String finalAwayEmail = awayEmail;
        holder.NameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String room;
                    getMessage = new GetMessage();
                    ChatFragment.getMessageInterface getMessageInterface = new ChatFragment.getMessageInterface() {
                        @Override
                        public void success(boolean success) {
                            ArrayList<MessageDescription> messageDescription = getMessage.getmeMessageDescription();
                            ArrayList<MessageDetails>messageDetails = messageDescription.get(0).getMessageDetail();

                            holder.NameText.setTextColor(-16777216);
                            Intent intent = new Intent(context, ChatActivity.class);
                            intent.putExtra("Messages", messageDetails);
                            intent.putExtra("FragmentFlag","Yes");
                            intent.putExtra("homeName", Constants.MyName);
                            intent.putExtra("awayName",name);
                            intent.putExtra("awayEmail", finalAwayEmail);
                            context.startActivity(intent);
                        }
                    };

                    if(Constants.MyEmail.compareTo(finalAwayEmail)>0){
                        room = finalAwayEmail +" "+ Constants.MyEmail;
                    }else{
                        room = Constants.MyEmail +" "+  finalAwayEmail;
                    }

                    getMessage.GetOneMsg(context,room,getMessageInterface,Constants.MyName,name,Constants.MyEmail,finalAwayEmail);

                }
            });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

      class ChatListViewHolder extends RecyclerView.ViewHolder {
        TextView NameText;
        public ChatListViewHolder(View itemView) {
            super(itemView);
            NameText = itemView.findViewById(R.id.namechats);
        }
    }
}
