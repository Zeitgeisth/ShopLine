package com.example.rock.shopline;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DataTypes.ChatDescription;
import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.Fragments.ProfileFragment;
import com.example.rock.shopline.RecyclerViews.ChatAdapter;
import com.example.rock.shopline.constants.Constants;
import com.example.rock.shopline.data.GetUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class ChatActivity extends AppCompatActivity {
    TextView Name;
    RecyclerView chatRecycler;
    ChatDescription chatDescription;
    ImageButton Send;
    TextInputEditText messages;
    String homeEmail = " ";
    String awayEmail;
    ChatAdapter adapter;
    String socketName, homeName, awayName;
    private ArrayList<ChatDescription> mMessages = new ArrayList<>();


    GetUser getUser1 = new GetUser(this);

    private Socket socket;

    {
        try {
            socket = IO.socket("http://192.168.100.45:3000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        awayEmail = getIntent().getStringExtra("Email");
        homeEmail = getIntent().getStringExtra("homeEmail");
        awayName = getIntent().getStringExtra("awayName");
        homeName = getIntent().getStringExtra("homeName");


        Name = findViewById(R.id.AwayEmail);
        chatRecycler = findViewById(R.id.ChatRecycler);

        Send = findViewById(R.id.Send);
        messages = findViewById(R.id.Messages);
        Name.setText(awayName);

        socket.connect();

//        final ProfileFragment.getMeInterface getMeInterface2 = new ProfileFragment.getMeInterface() {
//            @Override
//            public void success(boolean success) {
//                UserDescription userDescription = getUser1.getUserDescription();
//                homeEmail = userDescription.getEmail();
//                socket.connect();
//
//                if(homeEmail.compareTo(awayEmail)>0){
//                    socketName = awayEmail + homeEmail;
//                }else{
//                    socketName = homeEmail +  awayEmail;
//                }
//
//                socket.emit("newuser", socketName);
//
//            }
//        };
//        getUser1.getMeUser(getMeInterface2);


        if(homeEmail.compareTo(awayEmail)>0){
                    socketName = awayEmail + homeEmail;
                }else{
                    socketName = homeEmail +  awayEmail;
                }
               socket.emit("newuser", socketName);

        socket.on("message", handleMessage);


        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

    }

    private void sendMessage(){
        String message = messages.getText().toString();
        Log.v("amessage",""+message);
        messages.setText("");
        chatDescription = new ChatDescription();
        chatDescription.setName(homeName);
        chatDescription.setMsg(message);
        addMessage(chatDescription);
        socket.emit("name",homeName);
        socket.emit("message",message);
    }

    private void addMessage(ChatDescription message){
        mMessages.add(message);
        chatRecycler.setHasFixedSize(true);
        adapter = new ChatAdapter(getApplicationContext(), mMessages);
        chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        chatRecycler.setAdapter(adapter);
        // adapter.notifyItemInserted(0);
        scrollToBottom();
    }

    private void scrollToBottom(){
        chatRecycler.scrollToPosition(adapter.getItemCount() -1);
    }


    private Emitter.Listener handleMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    chatDescription = new ChatDescription();
                    String message = null;
                    String name = null;
                    try {
                        chatDescription.setMsg(data.getString("message"));
                        chatDescription.setName(data.getString("nick"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    addMessage(chatDescription);
                }
            });

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        socket.disconnect();
    }
}
