package com.example.mobileappdev;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


import com.google.firebase.auth.FirebaseAuth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Authentication of users to allow safe storage of information
    FirebaseAuth fAuth;

    EditText mEmail, mPassword;

    Button button3;
    Button button2;

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    @SuppressLint("MissingInflatedId")

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialising fields
        fAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.textView10);

        mPassword = findViewById(R.id.editText_password);

        button2 = findViewById(R.id.login_button);

        button3 = findViewById(R.id.create_account_button1);

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationPage.class));
            }
        });



        //When Login is clicked, the user validation runs and only logs in when a user is found in the database with authentication
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                //Login Validation
                if (TextUtils.isEmpty(email)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_LONG);
                    t.show();
                    mEmail.getText().clear();
                    mPassword.getText().clear();
                }

                if (TextUtils.isEmpty(password)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Enter Your Password", Toast.LENGTH_LONG);
                    t.show();
                    mEmail.getText().clear();
                    mPassword.getText().clear();
                }
                if (password.length() < 6) {
                    Toast t = Toast.makeText(getApplicationContext(), "Password Must Be At Least 6 Characters", Toast.LENGTH_LONG);
                    t.show();
                    mEmail.getText().clear();
                    mPassword.getText().clear();
                }

                if (email.length() > 0 && password.length() >= 6) {
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity.this, HomePage.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Account Not Recognised, Create Account And Try Again", Toast.LENGTH_LONG).show();
                                mEmail.getText().clear();
                                mPassword.getText().clear();
                            }

                        }
                    });
                }
            }
        });

    }
}