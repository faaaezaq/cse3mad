package com.example.mobileappdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class UpperBody extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "MyPrefs";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SidebarClass sidebar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper_body_workouts);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sidebar = new SidebarClass(this, drawerLayout, navigationView);
        sidebar.setupSidebar();

        // Initialising SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        // Retrieving and setting the state of each checkbox
        CheckBox checkBox13 = findViewById(R.id.checkBox);
        CheckBox checkBox14 = findViewById(R.id.checkBox2);
        CheckBox checkBox15 = findViewById(R.id.checkBox3);
        CheckBox checkBox16 = findViewById(R.id.checkBox4);
        CheckBox checkBox17 = findViewById(R.id.checkBox5);
        CheckBox checkBox18 = findViewById(R.id.checkBox6);

        checkBox13.setChecked(sharedPreferences.getBoolean("checkBox13State", false));
        checkBox14.setChecked(sharedPreferences.getBoolean("checkBox14State", false));
        checkBox15.setChecked(sharedPreferences.getBoolean("checkBox15State", false));
        checkBox16.setChecked(sharedPreferences.getBoolean("checkBox16State", false));
        checkBox17.setChecked(sharedPreferences.getBoolean("checkBox17State", false));
        checkBox18.setChecked(sharedPreferences.getBoolean("checkBox18State", false));

        checkBox13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox13State", isChecked);
                editor.apply();
            }
        });

        checkBox14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox14State", isChecked);
                editor.apply();
            }
        });

        checkBox15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox15State", isChecked);
                editor.apply();
            }
        });

        checkBox16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox16State", isChecked);
                editor.apply();
            }
        });

        checkBox17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox17State", isChecked);
                editor.apply();
            }
        });

        checkBox18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBox18State", isChecked);
                editor.apply();
            }
        });
    }
}