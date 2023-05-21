package com.example.mobileappdev;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ApparelPage extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SidebarClass sidebar;

    private ImageButton nike_button;
    private ImageButton adidas_button;
    private ImageButton gymshark_button;


    // The code below has image buttons hyperlinked to the different brand websites.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apparel);
        nike_button = findViewById(R.id.nike_next);
        adidas_button = findViewById(R.id.adidas_next);
        gymshark_button = findViewById(R.id.gymshark_next);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sidebar = new SidebarClass(this, drawerLayout, navigationView);
        sidebar.setupSidebar();

        nike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApparelPage.this, WebpageClass.class);
                intent.putExtra("url", "https://www.nike.com/au/women");
                startActivity(intent);
            }
        });

        gymshark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApparelPage.this, WebpageClass.class);
                intent.putExtra("url", "https://www.gymshark.com");
                startActivity(intent);
            }
        });

        adidas_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApparelPage.this, WebpageClass.class);
                intent.putExtra("url", "https://www.adidas.com");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!sidebar.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return sidebar.onOptionsItemSelected(item);
    }
}