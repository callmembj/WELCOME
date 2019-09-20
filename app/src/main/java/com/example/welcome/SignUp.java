package com.example.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    Intent signUPIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUPIntent = new Intent(SignUp.this,MainActivity.class);
        final EditText fullname_field=(EditText)findViewById(R.id.signup_fullName);
        final EditText phonenumber_field=(EditText)findViewById(R.id.signup_phoneNumber);
        final EditText email_field=(EditText)findViewById(R.id.signup_email);
        final EditText password_field=(EditText)findViewById(R.id.signup_password);
        final EditText confirm_password_field=(EditText)findViewById(R.id.signup_confirm_password);
        Button signUPButton = findViewById(R.id.signup_button);
        signUPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname=fullname_field.getText().toString();
                String phoneNumber=phonenumber_field.getText().toString();
                String email=email_field.getText().toString();
                String password_1=password_field.getText().toString();
                String password_2=confirm_password_field.getText().toString();
                if(password_1.equals(password_2))
                {
                    SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("NAME",fullname);
                    editor.putString("PHONENUMBER",phoneNumber);
                    editor.putString("EMAIL",email);
                    editor.putString("PASSWORD",password_1);
                    editor.apply();
                    Toast.makeText(SignUp.this,"You are successfully registered",Toast.LENGTH_LONG).show();
                    startActivity(signUPIntent);
                }
                else
                {
                    Toast.makeText(SignUp.this,"Password don't match",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
