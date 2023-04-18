package com.example.mobileappdev;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.GradientDrawable;

public class Nutrition_page extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView currentCaloriesTextView;
    private TextView targetCaloriesTextView;
    private int currentCalories = 0;
    private int targetCalories = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_page);

        progressBar = findViewById(R.id.progressBar);
        currentCaloriesTextView = findViewById(R.id.currentCaloriesTextView);
        targetCaloriesTextView = findViewById(R.id.targetCaloriesTextView);

        currentCalories = retrieveCurrentCalories();
        targetCalories = retrieveTargetCalories();

        currentCaloriesTextView.setText(getResources().getString(R.string.current_calories, currentCalories));
        targetCaloriesTextView.setText(getResources().getString(R.string.target_calories, targetCalories));
        int progressValue = calculateProgressValue(currentCalories, targetCalories);
        progressBar.setProgress(progressValue);
    }
    private int calculateProgressValue(int currentCalories, int targetCalories) {
        int progress = 0;
        if (targetCalories > 0) {
            progress = (currentCalories - targetCalories) - targetCalories * -1;
        }
        Log.d("MainActivity", "Current Calories: " + currentCalories);
        Log.d("MainActivity", "Target Calories: " + targetCalories);
        Log.d("MainActivity", "Progress value: " + progress);
        return progress;
    }
    private int retrieveCurrentCalories() {
        // Retrieve current calorie value from database or shared preferences here
        Log.d("MainActivity", "Current Calories: " + currentCalories);
        return 80; // Example value
    }
    private int retrieveTargetCalories() {
        Log.d("MainActivity", "Target Calories: " + targetCalories);
        return 100;
        }

}
