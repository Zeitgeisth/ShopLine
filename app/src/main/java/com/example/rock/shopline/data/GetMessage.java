package com.example.rock.shopline.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rock.shopline.ChatActivity;
import com.example.rock.shopline.DataTypes.MessageDescription;
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
    String url = Constants.GETMSG;
    ArrayList<MessageDescription>messageDescriptions = new ArrayList<>();
    MessageDescription messageDescription;

    public ArrayList<MessageDescription> getMessageDescription(){
        return messageDescriptions;
    }

    public void GetMsg(Context context, String ID, ChatFragment.getMessageInterface success)
    {
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
                JSONArray array = response;
                for(int i = 0; i<array.length() ;i++){

                    try {
                        JSONObject object = array.getJSONObject(i);
                        messageDescription = new MessageDescription();
                        messageDescription.setID(object.getString("_id"));
                        Log.i("MessageDescripiton:",object.getString("_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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



}
