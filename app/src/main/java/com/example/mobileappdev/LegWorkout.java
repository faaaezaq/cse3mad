package com.example.mobileappdev;

import androidx.appcompat.app.AppCompatActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;

import android.os.Bundle;

import android.widget.CheckBox;

import android.widget.CompoundButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationView;

public class LegWorkout extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "MyPrefs";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SidebarClass sidebar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_body_workouts);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sidebar = new SidebarClass(this, drawerLayout, navigationView);
        sidebar.setupSidebar();

        // Initialising SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        // Retrieving and setting the state of each checkbox
        CheckBox checkBox1 = findViewById(R.id.checkBox);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);
        CheckBox checkBox4 = findViewById(R.id.checkBox4);
        CheckBox checkBox5 = findViewById(R.id.checkBox5);
        CheckBox checkBox6 = findViewById(R.id.checkBox6);

        checkBox1.setChecked(sharedPreferences.getBoolean("checkBox7State", false));
        checkBox2.setChecked(sharedPreferences.getBoolean("checkBox8State", false));
        checkBox3.setChecked(sharedPreferences.getBoolean("checkBox9State", false));
        checkBox4.setChecked(sharedPreferences.getBoolean("checkBox10State", false));
        checkBox5.setChecked(sharedPreferences.getBoolean("checkBox11State", false));
        checkBox6.setChecked(sharedPreferences.getBoolean("checkBox12State", false));

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox7State", isChecked);
                editor.apply();
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox8State", isChecked);
                editor.apply();
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox9State", isChecked);
                editor.apply();
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox10State", isChecked);
                editor.apply();
            }
        });

        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox11State", isChecked);
                editor.apply();
            }
        });

        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox12State", isChecked);
                editor.apply();
            }
        });
    }
}