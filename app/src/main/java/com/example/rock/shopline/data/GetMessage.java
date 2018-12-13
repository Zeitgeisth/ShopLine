package com.example.rock.shopline.data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rock.shopline.ChatActivity;
import com.example.rock.shopline.DataTypes.ChatType;
import com.example.rock.shopline.DataTypes.MessageDescription;
import com.example.rock.shopline.DataTypes.MessageDetails;
import com.example.rock.shopline.DetailBookActivity;
import com.example.rock.shopline.Fragments.ChatFragment;
import com.example.rock.shopline.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GetMessage {

    ArrayList<MessageDescription>messageDescriptions = new ArrayList<>();
    MessageDescription messageDescription;
    MessageDescription messageDescriptionme;
    ArrayList<MessageDescription> messageDescriptionMe = new ArrayList<>();
    ArrayList<String>flags;
    MessageDetails messageDetails;
    ArrayList<MessageDetails>listMessage;

    public ArrayList<MessageDescription> getMessageDescription(){
        return messageDescriptions;
    }
    public ArrayList<MessageDescription> getmeMessageDescription(){
        return messageDescriptionMe;
    }

    public void GetMsg(final Context context, String ID, final ChatFragment.getMessageInterface success)
    {
        String url = Constants.GETMSG;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("ID",ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonBody.toString();
        JsonArrayRequest getMsg = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                messageDescriptions = new ArrayList<>();
                JSONArray array = response;

                for(int i = 0; i<array.length(); i++){

                    try {
                        JSONObject object = array.getJSONObject(i);
                        messageDescription = new MessageDescription();
                        messageDescription.setID(object.getString("_id"));

                        messageDescription.setSender(object.getString("Sender"));
                        messageDescription.setSeen(object.getBoolean("seen"));

                        JSONObject roomObject = object.getJSONObject("Room");
                        messageDescription.setRoom(roomObject.getString("RoomId"));
                        JSONArray msgArray = roomObject.getJSONArray("MessageDetail");
                            listMessage = new ArrayList<>();
                        for(int j=0; j<msgArray.length(); j++){
                            messageDetails = new MessageDetails();
                            JSONObject msgObject = msgArray.getJSONObject(j);
                            messageDetails.setMessage(msgObject.getString("message"));
                            messageDetails.setName(msgObject.getString("name"));
                            messageDetails.setDate(msgObject.getString("created"));
                            if(Constants.MyName.equals(msgObject.getString("name"))){
                                messageDetails.setUserType(ChatType.type.USER1);
                            }else {
                                messageDetails.setUserType(ChatType.type.USER2_IMG);
                            }

                            listMessage.add(messageDetails);
                        }
                        messageDescription.setMessageDetail(listMessage);

                        JSONArray registerIDArray = object.getJSONArray("registerId");
                        flags = new ArrayList<>();
                        for(int j = 0;j<registerIDArray.length(); j++){
                            flags.add(registerIDArray.getString(j));
                            Log.i("abcdefName",registerIDArray.getString(j));

                        }
                        messageDescription.setUserId(flags);

                        JSONArray Names = object.getJSONArray("Name");

                        flags = new ArrayList<>();
                        for(int j =0;j<Names.length();j++){
                            flags.add(Names.getString(j));

                        }
                        messageDescription.setNames(flags);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    messageDescriptions.add(messageDescription);

                }


                success.success(true);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.networkResponse.statusCode == 400) {
                    Toast.makeText(context, "No Chat", Toast.LENGTH_SHORT);
                    Log.i("abcdeeeeee","NNo Chat");
                    success.success(false);
                }
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json;charset utf=8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return mRequestBody==null?null:mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", Constants.AuthToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        Volley.newRequestQueue(context).add(getMsg);
    }


    public void GetOneMsg(final Context context, String Room, final ChatFragment.getMessageInterface success, String homeName, final String awayName, String homeEmail, final String awayEmail)
    {
        String url = Constants.GETONEMESSAGE;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("room",Room);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonBody.toString();
        JsonArrayRequest getOneMsg = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array = response;
                Log.i("abcde",array+"");
                messageDescriptionMe = new ArrayList<>();

                for(int i = 0; i<array.length(); i++){

                    try {
                        JSONObject object = array.getJSONObject(i);
                        messageDescriptionme = new MessageDescription();
                        messageDescriptionme.setID(object.getString("_id"));

                        messageDescriptionme.setSender(object.getString("Sender"));
                        messageDescriptionme.setSeen(object.getBoolean("seen"));

                        JSONObject roomObject = object.getJSONObject("Room");
                        messageDescriptionme.setRoom(roomObject.getString("RoomId"));
                        JSONArray msgArray = roomObject.getJSONArray("MessageDetail");
                        listMessage = new ArrayList<>();
                        for(int j=0; j<msgArray.length(); j++){
                            messageDetails = new MessageDetails();
                            JSONObject msgObject = msgArray.getJSONObject(j);
                            messageDetails.setMessage(msgObject.getString("message"));
                            messageDetails.setName(msgObject.getString("name"));
                            messageDetails.setDate(msgObject.getString("created"));
                            if(Constants.MyName.equals(msgObject.getString("name"))){
                                messageDetails.setUserType(ChatType.type.USER1);
                            }else {
                                messageDetails.setUserType(ChatType.type.USER2_IMG);
                            }

                            listMessage.add(messageDetails);
                        }
                        messageDescriptionme.setMessageDetail(listMessage);

                        JSONArray registerIDArray = object.getJSONArray("registerId");
                        flags = new ArrayList<>();
                        for(int j = 0;j<registerIDArray.length(); j++){
                            flags.add(registerIDArray.getString(j));
                            Log.i("abcdefName",registerIDArray.getString(j));

                        }
                        messageDescriptionme.setUserId(flags);

                        JSONArray Names = object.getJSONArray("Name");

                        flags = new ArrayList<>();
                        for(int j =0;j<Names.length();j++){
                            flags.add(Names.getString(j));

                        }
                        messageDescriptionme.setNames(flags);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    messageDescriptionMe.add(messageDescriptionme);

                }
                success.success(true);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Errorabcd",error.toString());
                Log.i("abcdef",error.networkResponse.statusCode+"");
                    if(error.networkResponse.statusCode == 400){

                        Intent intent = new Intent(context, ChatActivity.class);
                        intent.putExtra("FragmentFlag","No");
                        intent.putExtra("homeName", Constants.MyName);
                        intent.putExtra("awayName",awayName);
                        intent.putExtra("awayEmail",awayEmail);
                        context.startActivity(intent);

                    }
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json;charset utf=8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return mRequestBody==null?null:mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", Constants.AuthToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        Volley.newRequestQueue(context).add(getOneMsg);
    }





}
