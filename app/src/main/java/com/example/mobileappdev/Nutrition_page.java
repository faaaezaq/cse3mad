package com.example.mobileappdev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Nutrition_page extends AppCompatActivity {
    private ProgressBar progressBar;
    private ProgressBar waterProgressBar;
    private ProgressBar breakfastProgressBar;
    private ProgressBar LunchProgressBar;
    private ProgressBar DinnerProgressBar;
    private ProgressBar SnacksProgressBar;
    private TextView currentCaloriesTextView;
    private TextView targetCaloriesTextView;
    private int currentCalories = 0;
    private int targetCalories = 0;

    Button addWater;
    Button minusWater;
    TextView waterIntake;

    ImageButton breakfastCalories;
    EditText breakfastIntake;
    TextView breakFastInputValue;

    ImageButton LunchCalories;
    EditText LunchIntake;
    TextView LunchTextViewValue;

    ImageButton DinnerCalories;
    EditText DinnerIntake;
    TextView DinnerTextViewValue;

    ImageButton SnacksCalories;
    EditText SnacksIntake;
    TextView SnacksTextViewValue;

    protected int initalWater = 0;
    protected int water = 0;

    int breakfastCaloriesFinal = 0;
     int LunchCaloriesFinal = 0;
     int DinnerCaloriesFinal = 0;
     private int SnacksCaloriesFinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_page);

        progressBar = findViewById(R.id.progressBar);
        waterProgressBar = findViewById(R.id.waterIntakeProgressBar);
        LunchProgressBar = findViewById(R.id.LunchProgressBar);

        currentCaloriesTextView = findViewById(R.id.currentCaloriesTextView);
        targetCaloriesTextView = findViewById(R.id.targetCaloriesTextView);

        breakfastCalories = findViewById(R.id.button4);
        breakfastIntake = findViewById(R.id.breakfastCaloriesInput);
        breakFastInputValue = findViewById(R.id.breakfastTextView);
        breakfastProgressBar = findViewById(R.id.breakfastIntakeProgressBar);

        LunchCalories = findViewById(R.id.LunchInputButton);
        LunchIntake = findViewById(R.id.LunchCaloriesInput);
        LunchTextViewValue = findViewById(R.id.LunchTextView);
        LunchProgressBar = findViewById(R.id.LunchProgressBar);

        DinnerCalories = findViewById(R.id.DinnerInputButton);
        DinnerIntake = findViewById(R.id.DinnerCaloriesInput);
        DinnerTextViewValue = findViewById(R.id.DinnerTextView);
        DinnerProgressBar = findViewById(R.id.DinnerProgressBar);

        SnacksCalories = findViewById(R.id.SnacksInputButton);
        SnacksIntake = findViewById(R.id.SnacksCaloriesInput);
        SnacksTextViewValue = findViewById(R.id.SnacksTextView);
        SnacksProgressBar = findViewById(R.id.SnacksProgressBar);

        addWater = findViewById(R.id.plusButtonForWaterIntake);
        minusWater = findViewById(R.id.minusButtonForWaterIntake);
        waterIntake = findViewById(R.id.water);

        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initalWater++;
                waterIntake.setText(initalWater + "");
                water++;
                waterProgressBar.setProgress(water);
            }
        });
        minusWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initalWater--;
                waterIntake.setText(initalWater + "");
                water--;
                waterProgressBar.setProgress(water);
            }
        });

        breakfastCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(breakfastIntake.getText());
                int number = Integer.parseInt(text);
                breakfastCaloriesFinal += number;
                breakFastInputValue.setText(String.valueOf(breakfastCaloriesFinal));
                breakfastProgressBar.setProgress(breakfastCaloriesFinal);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Toast.makeText(Nutrition_page.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                setCurrentCalories();
                breakfastIntake.setText("");
            }
        });

        LunchCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(LunchIntake.getText());
                int number = Integer.parseInt(text);
                LunchCaloriesFinal += number;
                LunchTextViewValue.setText(String.valueOf(LunchCaloriesFinal));
                LunchProgressBar.setProgress(LunchCaloriesFinal);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Toast.makeText(Nutrition_page.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                setCurrentCalories();
                LunchIntake.setText("");
            }
        });

        DinnerCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(DinnerIntake.getText());
                int number = Integer.parseInt(text);
                DinnerCaloriesFinal +=number;
                DinnerTextViewValue.setText(String.valueOf(DinnerCaloriesFinal));
                DinnerProgressBar.setProgress(DinnerCaloriesFinal);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Toast.makeText(Nutrition_page.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                setCurrentCalories();
                DinnerIntake.setText("");
            }
        });

        SnacksCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(SnacksIntake.getText());
                int number = Integer.parseInt(text);
                SnacksCaloriesFinal +=number;
                SnacksTextViewValue.setText(String.valueOf(SnacksCaloriesFinal));
                SnacksProgressBar.setProgress(SnacksCaloriesFinal);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Toast.makeText(Nutrition_page.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                setCurrentCalories();
                SnacksIntake.setText("");

            }
        });

        setCurrentCalories();
    }

    private void setCurrentCalories() {
        currentCalories = retrieveCurrentCalories();
        targetCalories = retrieveTargetCalories();
        int snacksValue = retrieveCurrentCalories();

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
        return progress;
    }
    private int retrieveCurrentCalories() {
        // Retrieve current calorie value from database or shared preferences here
        return breakfastCaloriesFinal + LunchCaloriesFinal + DinnerCaloriesFinal + SnacksCaloriesFinal;
    }
    private int retrieveTargetCalories() {
        return 100;
        }
}
