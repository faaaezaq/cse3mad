package com.example.mobileappdev;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class NavigationbarClass {
    public static void setupBottomNavigation(BottomNavigationView bottomNavigationView, AppCompatActivity activity) {
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.floatingHomeButton:
                        activity.setContentView(R.layout.activity_home_page);
                        activity.startActivity(new Intent(activity, HomePage.class));
                        break;
                    case R.id.weight:
                        activity.setContentView(R.layout.activity_weights);
                        activity.startActivity(new Intent(activity, WeightsPage.class));
                        break;
                    case R.id.workout:
                        activity.setContentView(R.layout.activity_workout);
                        activity.startActivity(new Intent(activity, WorkoutPage.class));
                        break;
                    case R.id.nutrition:
                        activity.setContentView(R.layout.activity_nutrition);
                        activity.startActivity(new Intent(activity, NutritionPage.class));
                        break;
                    case R.id.apparel:
                        activity.setContentView(R.layout.activity_apparel);
                        activity.startActivity(new Intent(activity, ApparelPage.class));
                        break;
                }
                return true;
            }
        });
    }
}
