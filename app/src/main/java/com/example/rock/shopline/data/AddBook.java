package com.example.rock.shopline.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rock.shopline.AddBookActivity;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.accounts.AccountManager.KEY_PASSWORD;

public class AddBook {
    public void PostAddBook(final BookDescription bookDescription, final AddBookActivity.BookInterface listener, final Context context){
             String url = Constants.POSTBOOK;
             JSONObject jsonBody = new JSONObject();

        try {
            Log.i("Data",""+bookDescription.getBookName());
            jsonBody.put("BookName", bookDescription.getBookName());
            jsonBody.put("Cost",bookDescription.getCost());
            jsonBody.put("Genre",bookDescription.getGenre());
            jsonBody.put("Images",bookDescription.getImage());
            jsonBody.put("Description",bookDescription.getDescription());

            final String mReguestBody = jsonBody.toString();
            StringRequest bookRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    listener.success(true);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Error",error.toString());
                    if(error.networkResponse!=null){
                        if(error.networkResponse.statusCode == 401 || error.networkResponse.statusCode == 400){
                            try {
                                String body = new String(error.networkResponse.data,"UTF-8");
                                Toast.makeText(context, body, Toast.LENGTH_LONG).show();
                                listener.success(false);

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json;charset utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                         return mReguestBody == null ? null : mReguestBody.getBytes("utf-8");
                    } catch(UnsupportedEncodingException uee){
                        VolleyLog.wtf("Unsupported Encoding", mReguestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> headers = new HashMap<>();
                    Log.i("Token",""+Constants.AuthToken);
                    headers.put("x-auth-token",Constants.AuthToken);
                    headers.put("Content-Type","application/json");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                     Map<String,String> params = new HashMap<String, String>();
                    params.put("BookName", bookDescription.getBookName());
                    params.put("Cost",bookDescription.getCost());
                    params.put("Genre",bookDescription.getGenre());
                    params.put("Genre",bookDescription.getGenre());


                    return params;
                }
            };

            Volley.newRequestQueue(context).add(bookRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void AddFavourites(final Context context, String ID){
        String url = Constants.ADDMYFAVOURITES;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonObject.toString();
        StringRequest addToFavourites = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 String msg = response;
                 Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    if(error.networkResponse.statusCode == 400){
                        try {
                            String body = new String(error.networkResponse.data,"UTF-8");
                            Toast.makeText(context,body,Toast.LENGTH_LONG).show();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json;charset utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch(UnsupportedEncodingException uee){
                    VolleyLog.wtf("Unsupported Encoding", mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                Log.i("Token",""+Constants.AuthToken);
                headers.put("x-auth-token",Constants.AuthToken);
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        Volley.newRequestQueue(context).add(addToFavourites);

    }


}
