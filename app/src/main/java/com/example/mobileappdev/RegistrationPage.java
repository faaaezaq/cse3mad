package com.example.mobileappdev;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationPage extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference UserRef = db.collection("Users");

    private String TAG = "FireStore";

    private FbHelper fbHelper;

    private EditText name_field, email_field, password_field, confirm_password_field;

    private Button button3;

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

        button3 = findViewById(R.id.create_account_button2);

        name_field = findViewById(R.id.name_field);

        email_field = findViewById(R.id.email_field);

        password_field = findViewById(R.id.password_field);

        confirm_password_field = findViewById(R.id.confirm_password_field);


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
        });
    }
}