package com.example.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    AlertDialog.Builder builder;
    Intent login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final String Name=sharedPreferences.getString("NAME","DEFAULT_NAME");
        final String Phonenumber=sharedPreferences.getString("PHONENUMBER","DEFAULT_PHONENUMBER");
        final String Email=sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");
        TextView fullnameText = (TextView)findViewById(R.id.profile_fullname);
        TextView phonenumberText = (TextView)findViewById(R.id.profile_phoneNumber) ;
        TextView emailText = (TextView)findViewById(R.id.profile_email);

        fullnameText.setText(Name);
        phonenumberText.setText(Phonenumber);
        emailText.setText(Email);

        Button logoutButton = findViewById(R.id.button_logout);
        builder = new AlertDialog.Builder(this);
        login=new Intent(HomePage.this,MainActivity.class);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sharedPreferences.edit().putBoolean("ISLOGGEDIN",false).apply();
                                startActivity(login);
                                finish();
                                Toast.makeText(getApplicationContext(),"You are logged out",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("HOMEPAGE");
                alert.show();


            }
        });
    }
}