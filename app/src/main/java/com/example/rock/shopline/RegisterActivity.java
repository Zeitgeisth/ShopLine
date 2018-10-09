package com.example.rock.shopline;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.rock.shopline.data.AuthService;

public class RegisterActivity extends AppCompatActivity {
    AuthService authService;
    TextInputEditText Firstname, Lastname, Email, Password, Retypepassword, Phone, Location;
    RadioGroup Gender;
    String Firstnamevalue, LocationValue, LastnameValue, Emailvalue, Passwordvalue, Retypepasswordvalue, Phonevalue;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        authService = new AuthService();
        Firstname = findViewById(R.id.Firstname);
        Location = findViewById(R.id.Location);
        Lastname = findViewById(R.id.Lastname);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Retypepassword = findViewById(R.id.Retypepassword);
        Phone = findViewById(R.id.Phone);


        registerButton = findViewById(R.id.registerButton);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
            }
        });

    }

    public void registeruser(){
            Firstnamevalue = Firstname.getText().toString();
            LastnameValue = Lastname.getText().toString();
            Emailvalue = Email.getText().toString();
            Passwordvalue = Password.getText().toString();
            Retypepasswordvalue = Retypepassword.getText().toString();
            Phonevalue = Phone.getText().toString();
            LocationValue = Location.getText().toString();

            RegisterInterface registerInterface = new RegisterInterface() {
                @Override
                public void success(Boolean success) {
                         if(success){
                             Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                             startActivity(intent);
                             finish();

                         }
                }
            };

        if(validatorRegisterInput()) {
            if (Passwordvalue.equals(Retypepasswordvalue)) {
                authService.registerUser(Firstnamevalue, LastnameValue, Passwordvalue, Emailvalue, Phonevalue,LocationValue, getApplicationContext(), registerInterface);
            } else {
                Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Fields required",Toast.LENGTH_SHORT).show();
        }

    }

    public interface RegisterInterface {
        void success(Boolean success);
    }

    public Boolean validatorRegisterInput(){
        if(Firstname.getText().toString().trim().equalsIgnoreCase("")){
            Firstname.setError("Firstname is required");
            return false;
        }
        if(Lastname.getText().toString().trim().equalsIgnoreCase("")){
            Lastname.setError("Lastname is required");
            return false;
        }
        if(Password.getText().toString().trim().equalsIgnoreCase("")){
            Password.setError("Password is required");
            return false;
        }
        if(Retypepassword.getText().toString().trim().equalsIgnoreCase("")){
            Retypepassword.setError("Password is required");
            return false;
        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(Email.getText().toString().trim().equalsIgnoreCase("") || !(Email.getText().toString().trim().matches(emailPattern))){
            Email.setError("Email is required or invalid");
            return false;
        }
        if(Phone.getText().toString().trim().equalsIgnoreCase("")){
            Phone.setError("Phone is required");
            return false;
        }
        if(Location.getText().toString().trim().equalsIgnoreCase("")){
            Phone.setError("Location is required");
            return false;
        }


        return true;
    }


}


