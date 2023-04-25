package com.example.mobileappdev;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class WeightsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);

    }
}