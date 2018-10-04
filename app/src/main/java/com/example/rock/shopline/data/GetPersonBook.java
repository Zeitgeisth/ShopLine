package com.example.rock.shopline.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rock.shopline.DataTypes.BookDescription;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.PersonBookActivity;
import com.example.rock.shopline.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rock on 10/3/2018.
 */


public class GetPersonBook{
    public GetPersonBook(Context context) {
        this.context = context;
    }

    Context context;
    String url = Constants.GETUSERBOOKS;

    public ArrayList<BookDescription> getBooks() {
        return allBooks;
    }


    final ArrayList<BookDescription>allBooks = new ArrayList<>();


    public void getBook(final PersonBookActivity.BookInterface success, String Id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserId",Id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonObject.toString();

        final JsonArrayRequest getAllBooks = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                BookDescription bookDescription;
                JSONArray Books = response;
                Log.i("Response2",""+Books);
                for (int i = 0; i < Books.length(); i++) {
                    try {
                        JSONObject Book = Books.getJSONObject(i);
                        bookDescription = new BookDescription();
                        bookDescription.setBookName(Book.getString("BookName"));
                        bookDescription.setGenre(Book.getString("Genre"));
                        bookDescription.setCost(Book.getString("Cost"));
                        bookDescription.setImage(Book.getString("Images"));
                        bookDescription.setUserID(Book.getString("UserId"));

                        allBooks.add(bookDescription);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                success.success(true);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("IDDD",""+error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", Constants.AuthToken);
                headers.put("Content-Type", "application/json");
                return headers;
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
            public String getBodyContentType() {
                return "application/json;charset utf-8";
            }
        };


        Volley.newRequestQueue(context).add(getAllBooks);

    }

}

