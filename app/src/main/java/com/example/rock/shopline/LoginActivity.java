package com.example.rock.shopline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;

import com.example.rock.shopline.data.AuthService;

public class LoginActivity extends AppCompatActivity {
    Button register;
    CheckBox seepassword;
    TextInputEditText username, password;
    Boolean loginpage = false;
    String usernamevalue , passwordvalue;
    AuthService authService;
    SharedPreferences preferences;
    ProgressBar progressBar;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.Login);
        register = findViewById(R.id.Register);
        seepassword = findViewById(R.id.Seepassword);
        password = findViewById(R.id.Password);
        username = findViewById(R.id.Username);
        progressBar = findViewById(R.id.loginProgress);
        progressBar.setVisibility(View.INVISIBLE);
        preferences = getSharedPreferences("previousLogin", MODE_PRIVATE);

        authService = new AuthService();
        if (preferences.getBoolean("loggedIn", false)){
            username.setText(preferences.getString("email",""));
            password.setText(preferences.getString("password",""));
        }

        seepassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    // show password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.getText().length());
                } else {
                    // hide password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.getText().length());
                }
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.Register) {
                    Registerprocess();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            LoginInterface loginInterface = new LoginInterface() {
                @Override
                public void success(Boolean success) {
                    if(success){
                        progressBar.setVisibility(View.INVISIBLE);

                             Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                             startActivity(intent);
                             finish();
                    }
                }
            };

            @Override
            public void onClick(View view) {
                usernamevalue = username.getText().toString().trim();
                passwordvalue = password.getText().toString().trim();
                if(view.getId() == R.id.Login){
                    if(validateLogin()){
                        preferences.edit().putBoolean("loggedIn",true).apply();
                        preferences.edit().putString("email", usernamevalue).apply();
                        preferences.edit().putString("password", passwordvalue).apply();
                        progressBar.setVisibility(View.VISIBLE);
                        login.setVisibility(View.GONE);
                        authService.LoginUser(usernamevalue, passwordvalue, getBaseContext(), loginInterface, progressBar, login);
                    }
                }
            }
        });

    }


    public void Registerprocess() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public boolean validateLogin(){
        if(username.getText().toString().trim().equalsIgnoreCase("")){
            username.setError("Field is required");
            return false;
        }
        if(password.getText().toString().trim().equalsIgnoreCase("")){
            password.setError("Password is required");
            return false;
        }

        return true;
    }
    public interface LoginInterface{
        void success(Boolean success);
    }


}

