package com.example.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final Boolean isloggedin=sharedPreferences.getBoolean("ISLOGGEDIN",false);
        if (isloggedin)
        {
            Intent homepageIntent = new Intent(MainActivity.this,HomePage.class);
        }
        final String required_email=sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");
        final String required_password=sharedPreferences.getString("PASSWORD","DEFAULT_PASSWORD");
        final EditText email_field = (EditText)findViewById(R.id.login_email_address);
        final EditText password_field = (EditText)findViewById(R.id.login_password);
        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_field.getText().toString();
                String password=password_field.getText().toString();
                if(email.equals(required_email)&&password.equals(required_password)){
                    sharedPreferences.edit().putBoolean("ISLOGGEDIN",false).apply();
                    Intent homepageIntent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(homepageIntent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Email address or password is incorrect",Toast.LENGTH_LONG).show();
                }


            }
        });

        Button signupButton = findViewById(R.id.button_signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(MainActivity.this,SignUp.class);
                startActivity(signupIntent);
                finish();
            }
        });

    }
}
