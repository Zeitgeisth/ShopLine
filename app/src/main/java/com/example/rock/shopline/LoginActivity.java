package com.example.rock.shopline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    Button login, register;
    CheckBox seepassword;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Test comment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.Login);
        register = findViewById(R.id.Register);
        seepassword = findViewById(R.id.Seepassword);
        password = findViewById(R.id.Password);

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
                    Log.i("aa", "a");
                    Registerprocess();
                }
            }
        });

    }


    public void Registerprocess() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}

