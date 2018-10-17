package com.example.rock.shopline.data;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rock.shopline.HomeActivity;
import com.example.rock.shopline.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rock on 10/17/2018.
 */

public class DeleteBook {
    public DeleteBook(Context context) {
        this.context = context;
    }

    Context context;

    public void deleteBook(String url, final String Id, final HomeActivity.ShowBooks success) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", Id);
            final String mRequestBody = jsonObject.toString();
            final StringRequest delBook = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    success.success(true);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })

            {
                @Override
                public String getBodyContentType() {
                    return "application/json;charset utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding", mRequestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("x-auth-token", Constants.AuthToken);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", Id);
                    return params;
                }
            };

            Volley.newRequestQueue(context).add(delBook);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
