package com.example.rock.shopline.data;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rock.shopline.LoginActivity;
import com.example.rock.shopline.RegisterActivity;
import com.example.rock.shopline.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rock on 8/13/2018.
 */

public class AuthService {

    public AuthService() {
    }

    public void registerUser(String firstName, String lastName, String password, String email, String phone, String location, final Context context, final RegisterActivity.RegisterInterface listener, final Button registerButton, final ProgressBar registerProgress){
        String url = Constants.REGISTERURL;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
            jsonBody.put("password", password);
            jsonBody.put("email", email);
            jsonBody.put("phone", phone);
            jsonBody.put("location", location);


            final String mReguestBody = jsonBody.toString();
            StringRequest registerRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    listener.success(true);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Error",error.toString());
                    if(error.networkResponse!=null){
                        if(error.networkResponse.statusCode == 400){
                            try {
                                String body = new String(error.networkResponse.data,"UTF-8");
                                Toast.makeText(context, body, Toast.LENGTH_LONG).show();
                                registerProgress.setVisibility(View.GONE);
                                registerButton.setVisibility(View.VISIBLE);
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
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try{
                        return mReguestBody == null ? null : mReguestBody.getBytes("utf-8");
                    }
                    catch(UnsupportedEncodingException uee){
                        VolleyLog.wtf("Unsupported Encoding", mReguestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    if(response.statusCode==200){
                        String token = response.headers.get("x-auth-token");
                        Log.i("Token",""+token);
                        Constants.AuthToken = token;
                    }
                    return super.parseNetworkResponse(response);
                }
            };
            Volley.newRequestQueue(context).add(registerRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void editUser(String firstName, String lastName, String email, String phone, String location,String Id, final Context context, final RegisterActivity.RegisterInterface listener, final Button registerButton, final ProgressBar registerProgress){
        String url = Constants.EDITURL;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
            jsonBody.put("email", email);
            jsonBody.put("phone", phone);
            jsonBody.put("location", location);
            jsonBody.put("id",Id);


            final String mReguestBody = jsonBody.toString();
            StringRequest registerRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    listener.success(true);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Error",error.toString());
                    if(error.networkResponse!=null){
                        if(error.networkResponse.statusCode == 400){
                            try {
                                String body = new String(error.networkResponse.data,"UTF-8");
                                Toast.makeText(context, body, Toast.LENGTH_LONG).show();
                                registerProgress.setVisibility(View.GONE);
                                registerButton.setVisibility(View.VISIBLE);
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
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try{
                        return mReguestBody == null ? null : mReguestBody.getBytes("utf-8");
                    }
                    catch(UnsupportedEncodingException uee){
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
            };
            Volley.newRequestQueue(context).add(registerRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    public void LoginUser(String EmailorPhone, String Password, final Context context, final LoginActivity.LoginInterface listener, final ProgressBar registerProgress, final Button Login){
        String url = Constants.LOGINURL;
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email",EmailorPhone);
            jsonBody.put("password",Password);
            Log.i("username",EmailorPhone);
            Log.i("password",Password);
           final String nRequestBody = jsonBody.toString();

           StringRequest loginRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   String token = response;
                   Constants.AuthToken = token;
                   Log.i("Token", token);
                   listener.success(true);
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   if(error.networkResponse!= null){
                       if(error.networkResponse.statusCode==400){
                           try {
                               String body = new String(error.networkResponse.data, "UTF-8");
                               Toast.makeText(context, body, Toast.LENGTH_LONG).show();
                               Login.setVisibility(View.VISIBLE);
                               registerProgress.setVisibility(View.GONE);
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
                   return "application/json; charset=utf-8";
               }

               @Override
               public byte[] getBody() throws AuthFailureError {
                   try {
                       return nRequestBody==null ? null : nRequestBody.getBytes("utf-8");
                   } catch (UnsupportedEncodingException uee) {
                       VolleyLog.wtf("Unsupported Encoding", nRequestBody, "utf-8");
                       return null;
                   }
               }
               @Override
               protected Response<String> parseNetworkResponse(NetworkResponse response) {
                   if( response.statusCode==200){

                   }
                   return super.parseNetworkResponse(response);
               }
           };
           Volley.newRequestQueue(context).add(loginRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
