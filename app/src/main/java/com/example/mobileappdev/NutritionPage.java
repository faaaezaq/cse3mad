package com.example.mobileappdev;

import static com.example.mobileappdev.HomePage.DEFAULT;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import kotlinx.coroutines.flow.SharedFlow;

public class NutritionPage extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SidebarClass sidebar;

    private static final int REQUEST_CODE = 1;
    public ProgressBar progressBarMain;
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

    Button resetButton;

    Button saveProgress;

    protected int initalWater = 0;
    protected int water = 0;

    int breakfastCaloriesFinal = 0;
    int LunchCaloriesFinal = 0;
    int DinnerCaloriesFinal = 0;
    private int SnacksCaloriesFinal = 0;


    private ActivityResultLauncher<Intent> resetLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sidebar = new SidebarClass(this, drawerLayout, navigationView);
        sidebar.setupSidebar();

        progressBarMain = findViewById(R.id.progressBar);
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

        resetButton = findViewById(R.id.resetButton);
        saveProgress = findViewById(R.id.saveButton);


//  Lines 135-144 is the initialization for the resetLauncher, which gets caught in HomePage
        resetLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                        }
                    }
                });
// Line 144-307 takes the User Inputs, saves it using SharedPreferences, populates the correct TextViews and progressBars
// and also send some of the Data (such as the currentCalories) over Using SharedPreferences to HomePage for it to be displayed there.
        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initalWater = Integer.parseInt(waterIntake.getText().toString()) + 1;
                waterIntake.setText(initalWater + "");
                water++;
                waterProgressBar.setProgress(initalWater);
                String str = waterIntake.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name",str);
                editor.commit();

                String waterNum = waterIntake.getText().toString();
                String progressNum = waterIntake.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("waterNumSave", MODE_PRIVATE);
                SharedPreferences.Editor editorSave = sharedPreferences.edit();
                editorSave.putString("waterNumber", waterNum);
                editorSave.putString("waterProgressBar", progressNum);
                editorSave.commit();
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
                String text = String.valueOf(breakfastIntake.getText()).trim();
                if (text.isEmpty()) {
                    Toast.makeText(NutritionPage.this, "Please enter an int value", Toast.LENGTH_SHORT).show();
                }
                else {
                    int number = Integer.parseInt(text);
                    breakfastCaloriesFinal = number + Integer.parseInt((String) breakFastInputValue.getText());
                    breakFastInputValue.setText(String.valueOf(breakfastCaloriesFinal));
                    breakfastProgressBar.setProgress(breakfastCaloriesFinal);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    Toast.makeText(NutritionPage.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                    setCurrentCalories();
                    breakfastIntake.setText("");

                    String BreakFC = String.valueOf(breakfastCaloriesFinal);
                    String BreakFCPB = String.valueOf(breakfastCaloriesFinal);
                    SharedPreferences sharedPreferences = getSharedPreferences("BreakFastSave", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("breakFastCal",BreakFC);
                    editor.putString("breakfastPB", BreakFCPB);
                    editor.apply();

                }
            }
        });


        LunchCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(LunchIntake.getText());
                if (text.isEmpty()) {
                    Toast.makeText(NutritionPage.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    int number = Integer.parseInt(text);
                    LunchCaloriesFinal = number + Integer.parseInt((String) LunchTextViewValue.getText());
                    LunchTextViewValue.setText(String.valueOf(LunchCaloriesFinal));
                    LunchProgressBar.setProgress(LunchCaloriesFinal);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    Toast.makeText(NutritionPage.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                    setCurrentCalories();
                    LunchIntake.setText("");

                    String LunchFC = String.valueOf(LunchCaloriesFinal);
                    String LunchPB = String.valueOf(LunchCaloriesFinal);
                    SharedPreferences sharedPreferences = getSharedPreferences("LunchCalSave", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("lunchCal",LunchFC);
                    editor.putString("lunchProgressBar", LunchPB);
                    editor.apply();

                }
            }
        });

        DinnerCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(DinnerIntake.getText());
                if (text.isEmpty()) {
                    Toast.makeText(NutritionPage.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    int number = Integer.parseInt(text);
                    DinnerCaloriesFinal = number + Integer.parseInt(DinnerTextViewValue.getText().toString());
                    DinnerTextViewValue.setText(String.valueOf(DinnerCaloriesFinal));
                    DinnerProgressBar.setProgress(DinnerCaloriesFinal);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    Toast.makeText(NutritionPage.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                    setCurrentCalories();
                    DinnerIntake.setText("");

                    String dinnerFC = String.valueOf(DinnerCaloriesFinal);
                    String dinnerPB = String.valueOf(DinnerCaloriesFinal);
                    SharedPreferences sharedPreferences = getSharedPreferences("DinnerCalSave", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("DinnerCal",dinnerFC);
                    editor.putString("DinnerProgressBar", dinnerPB);
                    editor.apply();

                }
            }
        });


        saveProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        SnacksCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(SnacksIntake.getText());
                if (text.isEmpty()) {
                    Toast.makeText(NutritionPage.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }
                else {

                    int number = Integer.parseInt(text);
                    SnacksCaloriesFinal = number + Integer.parseInt(SnacksTextViewValue.getText().toString());
                    SnacksTextViewValue.setText(String.valueOf(SnacksCaloriesFinal));
                    SnacksProgressBar.setProgress(SnacksCaloriesFinal);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    Toast.makeText(NutritionPage.this, "Calories Stored : " + text, Toast.LENGTH_SHORT).show();
                    setCurrentCalories();
                    SnacksIntake.setText("");

                    String SnacksFC = String.valueOf(SnacksCaloriesFinal);
                    String SnacksPB = String.valueOf(SnacksCaloriesFinal);
                    SharedPreferences sharedPreferences = getSharedPreferences("SnacksCalSave", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("SnacksCal",SnacksFC);
                    editor.putString("SnacksProgressBar", SnacksPB);
                    editor.apply();

                }
            }
        });
//  All data saved by the code in Line 144-308 is deleted using the resetButton below. It also resets the Data that is sent over
//        to the HomePage.
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferencesWater = getSharedPreferences("waterNumSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPreferencesBreakfast = getSharedPreferences("BreakFastSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPrefLunch = getSharedPreferences("LunchCalSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPrefDinner = getSharedPreferences("DinnerCalSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPrefSnacks = getSharedPreferences("SnacksCalSave", Context.MODE_PRIVATE);
                SharedPreferences mainProgressbar = getSharedPreferences("mainProgress", MODE_PRIVATE);

                SharedPreferences.Editor editorWater = sharedPreferencesWater.edit();
                SharedPreferences.Editor editorBreakfast = sharedPreferencesBreakfast.edit();
                SharedPreferences.Editor editorLunch = sharedPrefLunch.edit();
                SharedPreferences.Editor editorDinner = sharedPrefDinner.edit();
                SharedPreferences.Editor editorSnacks = sharedPrefSnacks.edit();
                SharedPreferences.Editor mainProgress = mainProgressbar.edit();

                editorWater.clear();
                editorWater.apply();

                editorBreakfast.clear();
                editorBreakfast.apply();

                editorLunch.clear();
                editorLunch.apply();

                editorDinner.clear();
                editorDinner.apply();

                editorSnacks.clear();
                editorSnacks.apply();

                mainProgress.clear();
                mainProgress.apply();


                Intent intent1 = new Intent(NutritionPage.this, HomePage.class);
                intent1.putExtra("resetButtonClicked", true);
                resetLauncher.launch(intent1);
            }
        });
    }


// Basic Java methods created to help in the populating of different TextViews
    private void setCurrentCalories() {
        currentCalories = retrieveCurrentCalories();
        targetCalories = retrieveTargetCalories();

//        currentCaloriesTextView.setText(getResources().getString(R.string.current_calories, currentCalories));
        targetCaloriesTextView.setText(getResources().getString(R.string.target_calories, targetCalories));

        int progressValue = Integer.parseInt(String.valueOf(LunchTextViewValue.getText())) + Integer.parseInt(String.valueOf(DinnerTextViewValue.getText())) + Integer.parseInt(String.valueOf(breakFastInputValue.getText())) + Integer.parseInt(String.valueOf(SnacksTextViewValue.getText()));
//        calculateProgressValue(currentCalories, targetCalories) +
        currentCaloriesTextView.setText(String.valueOf(progressValue)) ;

        progressBarMain.setMax(retrieveTargetCalories());
        progressBarMain.setProgress(progressValue);

        String str1 = String.valueOf(progressValue);
        SharedPreferences sharedPref = getSharedPreferences("ProgressBar1",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("progressValue",progressValue);
        editor.putString("name",str1);
        editor.putInt("progressBar", water);
        editor.putInt("progressValue", progressValue);
        editor.putInt("currentCalories", currentCalories);

        SharedPreferences mainProgressVal = getSharedPreferences("mainProgress", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = mainProgressVal.edit();
        editor1.putInt("mainProgressBar", progressValue);
        editor1.putString("currentCalValue", String.valueOf(progressValue));

        editor.apply();
        editor1.apply();
    }
//    All SharedPreferences created after the buttons above are caught here and Data is hence saved and populates the desired
//    TextViews and ProgressBars.
    @SuppressLint("SetTextI18n")
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("waterNumSave", MODE_PRIVATE);
        String waterNumber = sharedPreferences.getString("waterNumber", DEFAULT);
        String waterProgress = sharedPreferences.getString("waterProgressBar", DEFAULT);
        waterIntake.setText(waterNumber);
        waterProgressBar.setProgress(Integer.parseInt(waterProgress));

        SharedPreferences sharedPreferencesForBreakfast = getSharedPreferences("BreakFastSave", MODE_PRIVATE);
        String caloriesNum = sharedPreferencesForBreakfast.getString("breakFastCal", DEFAULT);
        String progressBarNum = sharedPreferencesForBreakfast.getString("breakfastPB", DEFAULT);
        breakFastInputValue.setText(caloriesNum);
        breakfastProgressBar.setProgress(Integer.parseInt(progressBarNum));

        SharedPreferences sharedPreferencesDinner = getSharedPreferences("DinnerCalSave", MODE_PRIVATE);
        String DinnercaloriesNum = sharedPreferencesDinner.getString("DinnerCal", DEFAULT);
        String DinnerProgress = sharedPreferencesDinner.getString("DinnerProgressBar", DEFAULT);
        DinnerTextViewValue.setText(DinnercaloriesNum);
        DinnerProgressBar.setProgress(Integer.parseInt(DinnerProgress));

        SharedPreferences sharedPreferencesLunch = getSharedPreferences("LunchCalSave", MODE_PRIVATE);
        String lunchcaloriesNum = sharedPreferencesLunch.getString("lunchCal", DEFAULT);
        String lunchPB = sharedPreferencesLunch.getString("lunchProgressBar", DEFAULT);
        LunchTextViewValue.setText(lunchcaloriesNum);
        LunchProgressBar.setProgress(Integer.parseInt(lunchPB));

        SharedPreferences sharedPreferencesSnacks = getSharedPreferences("SnacksCalSave", MODE_PRIVATE);
        String SnackscaloriesNum = sharedPreferencesSnacks.getString("SnacksCal", DEFAULT);
        String SnacksProgress = sharedPreferencesSnacks.getString("SnacksProgressBar", DEFAULT);
        SnacksTextViewValue.setText(SnackscaloriesNum);
        SnacksProgressBar.setProgress(Integer.parseInt(SnacksProgress));

        SharedPreferences sharedPreferences1 = getSharedPreferences("mainProgress", MODE_PRIVATE);
        int mainValue = sharedPreferences1.getInt("mainProgressBar", Integer.parseInt(DEFAULT));
        progressBarMain.setProgress(mainValue);
        currentCaloriesTextView.setText(String.valueOf(mainValue));

        targetCaloriesTextView.setText("Target Calories : " + retrieveTargetCalories());
    }

    private int retrieveCurrentCalories() {
        int currentCalories = ((breakfastCaloriesFinal + LunchCaloriesFinal + DinnerCaloriesFinal + SnacksCaloriesFinal) - 10000) - targetCalories * -1;
        Log.d(String.valueOf(currentCalories), "2nd progress");
        SharedPreferences sharedPreferencesForCurrentCalories = getSharedPreferences("currentCal", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesForCurrentCalories.edit();
        editor.putInt("value" , currentCalories);
        editor.apply();
        return currentCalories;
    }
    private int retrieveTargetCalories() {
        return 5000;
    }

    public void store_input(View view) {
    }

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