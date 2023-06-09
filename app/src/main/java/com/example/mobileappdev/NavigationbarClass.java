package com.example.mobileappdev;

import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//Setting up the global Bottom Navigation Bar here for using in different pages in the app
public class NavigationbarClass {
    public static void setupBottomNavigation(BottomNavigationView bottomNavigationView, AppCompatActivity activity) {
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.home:
                        activity.startActivity(new Intent(activity, HomePage.class));
                        break;
                    case R.id.workout:
                        activity.startActivity(new Intent(activity, WorkoutPage.class));
                        break;
                    case R.id.nutrition:
                        activity.startActivity(new Intent(activity, NutritionPage.class));
                        break;
                    case R.id.apparel:
                        activity.startActivity(new Intent(activity, ApparelPage.class));
                        break;
                }
                return true;
            }
        });
    }
}
