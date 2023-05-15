package com.example.mobileappdev;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button button3;
    Button button1;
    Button button2;
 
    @SuppressLint("MissingInflatedId")

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button2 = findViewById(R.id.login_button);
        button3 = findViewById(R.id.create_account_button1);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HomePage.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationPage.class));
            }
        });


    }
}