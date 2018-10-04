package com.example.rock.shopline.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.DataTypes.UserDescription;
import com.example.rock.shopline.DetailBookActivity;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rock on 9/30/2018.
 */

public class GetUser {
    public GetUser(Context context) {
        this.context = context;
    }

    Context context;

    String url = Constants.GETUSER;

    public UserDescription getUserDescription() {
        return userDescription;
    }

    UserDescription userDescription;



    public void getUser(final DetailBookActivity.getUser success, final String User) {

        JSONObject jsonBody = new JSONObject();
        try {
            Log.i("User",""+User);
            jsonBody.put("userId", User);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonBody.toString();

        final JsonObjectRequest getAllBooks = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONObject User = response;
                Log.i("User",""+response);
                try {
                    userDescription = new UserDescription();
                    userDescription.setFirstName(User.getString("firstName"));
                    userDescription.setLastName(User.getString("lastName"));
                    userDescription.setEmail(User.getString("email"));
                    userDescription.setPhone(User.getString("phone"));
                    userDescription.setId(User.getString("_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                success.success(true);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Error",""+error);
                if(error.networkResponse!=null){
                    if(error.networkResponse.statusCode == 401 || error.networkResponse.statusCode == 400){
                        try {
                            String body = new String(error.networkResponse.data,"UTF-8");
                            Toast.makeText(context, body, Toast.LENGTH_LONG).show();
                            success.success(false);

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        })


        {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

                    JSONObject result = null;

                    if (jsonString != null && jsonString.length() > 0)
                        result = new JSONObject(jsonString);

                    return Response.success(result,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
            @Override
            public String getBodyContentType() {
                return "application/json;charset utf-8";
            }

            @Override
            public byte[] getBody() {

                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
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


        Volley.newRequestQueue(context).add(getAllBooks);

    }
}
