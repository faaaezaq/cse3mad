package com.example.mobileappdev;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class RegistrationPage extends AppCompatActivity {

    public static final String TAG = "TAG";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference UserRef = db.collection("Users");

    private FbHelper fbHelper;

    FirebaseAuth fAuth;
    private EditText name_field, email_field, password_field, confirm_password_field;

    private Button button3;

    String userID;

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        db = FirebaseFirestore.getInstance();

        fbHelper= new FbHelper(db);

        fAuth = FirebaseAuth.getInstance();

        button3 = findViewById(R.id.create_account_button2);

        name_field = findViewById(R.id.name_field);

        email_field = findViewById(R.id.email_field);

        password_field = findViewById(R.id.password_field);

        confirm_password_field = findViewById(R.id.confirm_password_field);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_field.getText().toString();
                String password = password_field.getText().toString();
                String confirmPassword = confirm_password_field.getText().toString();
                String fullName = name_field.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_LONG);
                    t.show();
                    name_field.getText().clear();
                    email_field.getText().clear();
                    password_field.getText().clear();
                    confirm_password_field.getText().clear();
                }

                if (TextUtils.isEmpty(fullName)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Enter Your Full Name", Toast.LENGTH_LONG);
                    t.show();
                    name_field.getText().clear();
                    email_field.getText().clear();
                    password_field.getText().clear();
                    confirm_password_field.getText().clear();
                }

                if (TextUtils.isEmpty(password)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Enter Your Password", Toast.LENGTH_LONG);
                    t.show();
                    name_field.getText().clear();
                    email_field.getText().clear();
                    password_field.getText().clear();
                    confirm_password_field.getText().clear();
                }

                if (password.length() < 6) {
                    Toast t = Toast.makeText(getApplicationContext(), "Password Must Be At Least 6 Characters Long", Toast.LENGTH_LONG);
                    t.show();
                    name_field.getText().clear();
                    email_field.getText().clear();
                    password_field.getText().clear();
                    confirm_password_field.getText().clear();
                }

                if (!password.equals(confirmPassword)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Ensure That Both Passwords Fields Are The Same", Toast.LENGTH_LONG);
                    t.show();
                    name_field.getText().clear();
                    email_field.getText().clear();
                    password_field.getText().clear();
                    confirm_password_field.getText().clear();
                }

                if (fullName.length() > 0 && email.length() > 0 && password.length() >= 6 && password.equals(confirmPassword)) {
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast t = Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG);
                                    t.show();
                                    userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = db.collection("user").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("fName", fullName);
                                    user.put("Email", email);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d(TAG, "onSuccess: user profile is created for" + userID);
                                        }
                                    });
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                }

                                else {
                                    Toast t = Toast.makeText(getApplicationContext(), "User Failed To Create", Toast.LENGTH_LONG);
                                    t.show();
                                }
                            }
                    );
                }

                /*
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast t = Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG);
                        t.show();
                        userID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("user").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("fName", fullName);
                        user.put("Email", email);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "onSuccess: user profile is created for" + userID);
                            }
                        });
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }

                    else {
                        Toast t = Toast.makeText(getApplicationContext(), "User Failed To Create", Toast.LENGTH_LONG);
                        t.show();
                    }
                }
                );

                 */
            }
        });



        /*
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(name_field)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Enter Your Full Name", Toast.LENGTH_LONG);
                    t.show();
                }

                if (isEmpty(email_field)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Enter Your Email", Toast.LENGTH_LONG);
                    t.show();
                }

                if (isEmpty(password_field)) {
                    Toast t = Toast.makeText(getApplicationContext(), "PLease Enter Your Password", Toast.LENGTH_LONG);
                    t.show();
                }

                if (!password_field.getText().toString().equals(confirm_password_field.getText().toString())) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please Ensure That Both Passwords Fields Are The Same", Toast.LENGTH_LONG);
                    t.show();
                }

                if (name_field.getText().toString().length() > 0 && email_field.getText().toString().length() > 0 && password_field.getText().toString().equals(confirm_password_field.getText().toString())) {
                    fbHelper.addUser(new Users(name_field.getText().toString(), email_field.getText().toString(),
                            password_field.getText().toString()));
                    Toast.makeText(getApplicationContext(), "User Successfully Created In Database", Toast.LENGTH_LONG).show();
                    name_field.getText().clear();
                    email_field.getText().clear();
                    password_field.getText().clear();
                    confirm_password_field.getText().clear();
                }


                closeKeyboard();

            }
        });         */

    }

}