package com.example.rock.shopline;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rock.shopline.DataTypes.ChatType;
import com.example.rock.shopline.DataTypes.MessageDetails;
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
    ImageButton Send;
    TextInputEditText messages;
    String homeEmail = " ";
    String awayEmail;
    ChatAdapter adapter;
    String socketName, homeName, awayName;
    MessageDetails messageDetails;
    private ArrayList<MessageDetails> mMessages = new ArrayList<>();


    GetUser getUser1 = new GetUser(this);

    private Socket socket;

    {
        try {
            socket = IO.socket("http://192.168.1.22:3000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatRecycler = findViewById(R.id.ChatRecycler);

        awayEmail = getIntent().getStringExtra("awayEmail");
        homeEmail = Constants.MyEmail;
        awayName = getIntent().getStringExtra("awayName");
        homeName = getIntent().getStringExtra("homeName");

        String FragmentFlag = getIntent().getStringExtra("FragmentFlag");
        if(FragmentFlag.equals("Yes")){
            mMessages = getIntent().getParcelableArrayListExtra("Messages");
            Log.i("ancdMessage",mMessages.size()+"");
            previousMessages(mMessages);
        }


        Name = findViewById(R.id.AwayEmail);


        Send = findViewById(R.id.Send);
        messages = findViewById(R.id.Messages);
        Name.setText(awayName);

        socket.connect();



            if(homeEmail.compareTo(awayEmail)>0){
                socketName = awayEmail +" "+ homeEmail;
            }else{
                socketName = homeEmail +" "+  awayEmail;
            }
            socket.emit("newuser", socketName);


        socket.emit("name",homeName);
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
        messages.setText("");
        messageDetails = new MessageDetails();
        messageDetails.setName(homeName);
        messageDetails.setMessage(message);
        addMessage(messageDetails, ChatType.type.USER1);
        socket.emit("name",homeName);
        socket.emit("message",message);
    }

    private void addMessage(MessageDetails message, ChatType.type user){
        message.setUserType(user);
        mMessages.add(message);
        chatRecycler.setHasFixedSize(true);
        adapter = new ChatAdapter(getApplicationContext(), mMessages);
        chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        chatRecycler.setAdapter(adapter);
        scrollToBottom();
    }
    private void previousMessages(ArrayList messages){
        adapter = new ChatAdapter(getApplicationContext(), messages);
        chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        chatRecycler.setHasFixedSize(true);
        chatRecycler.setAdapter(adapter);
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
                     messageDetails = new MessageDetails();
                    try {
                        messageDetails.setMessage(data.getString("message"));
                        messageDetails.setName(data.getString("nick"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    addMessage(messageDetails, ChatType.type.USER2_IMG);
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
