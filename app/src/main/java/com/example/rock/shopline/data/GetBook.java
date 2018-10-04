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
import com.example.rock.shopline.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class GetBook{
    public GetBook(Context context) {
        this.context = context;
    }

    Context context;
    String url = Constants.GETALLBOOK;

    public ArrayList<BookDescription> getBooks() {
        return allBooks;
    }


    final ArrayList<BookDescription>allBooks = new ArrayList<>();


    public void getBook(final HomeActivity.ShowBooks success) {


        final JsonArrayRequest getAllBooks = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                BookDescription bookDescription;
                JSONArray Books = response;
                Log.i("Response",""+Books);
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

            }
        }) {
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

