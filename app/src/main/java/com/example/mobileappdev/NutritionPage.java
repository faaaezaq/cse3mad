package com.example.mobileappdev;

import static com.example.mobileappdev.HomePage.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    protected int initalWater = 0;
    protected int water = 0;

    int breakfastCaloriesFinal = 0;
    int LunchCaloriesFinal = 0;
    int DinnerCaloriesFinal = 0;
    private int SnacksCaloriesFinal = 0;


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

        resetButton = findViewById(R.id.button2);
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
//                editor.putInt("progressBar", water);
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

        SharedPreferences sharedPreferences = getSharedPreferences("waterNumSave", MODE_PRIVATE);
        String waterNumber = sharedPreferences.getString("waterNumber", DEFAULT);
        String waterProgress = sharedPreferences.getString("waterProgressBar", DEFAULT);
//        Log.d(caloriesNum, "Calories Number : ");
        waterIntake.setText(waterNumber);
        waterProgressBar.setProgress(Integer.parseInt(waterProgress));

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

                    setCurrentCalories();

//                    saveMainProgressBar();
//                    SharedPreferences sharedPreferencesForMainPB = getSharedPreferences("mainProgressBar", MODE_PRIVATE);
//                    String mainPBSave = sharedPreferencesForMainPB.getString("progressBarMain", DEFAULT);
//                    Log.d(mainPBSave, "main value");
//                    progressBar.setProgress(Integer.parseInt(mainPBSave));

//                    String BreakFCProgressbar = String.valueOf(breakfastCaloriesFinal);
//                    SharedPreferences sharedPreferencesProgressBar = getSharedPreferences("bProgressBar", MODE_PRIVATE);
//                    SharedPreferences.Editor editorProgressBar = sharedPreferencesProgressBar.edit();
//                    editor.putString("breakFastProgressBar", BreakFCProgressbar);
                }
            }
        });
        SharedPreferences sharedPreferencesForBreakfast = getSharedPreferences("BreakFastSave", MODE_PRIVATE);
        String caloriesNum = sharedPreferencesForBreakfast.getString("breakFastCal", DEFAULT);
        String progressBarNum = sharedPreferencesForBreakfast.getString("breakfastPB", DEFAULT);
//        Log.d(caloriesNum, "Calories Number : ");
        breakFastInputValue.setText(caloriesNum);
        breakfastProgressBar.setProgress(Integer.parseInt(progressBarNum));

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

                    setCurrentCalories();

//                    saveMainProgressBar();
//                    SharedPreferences sharedPreferencesForMainPB = getSharedPreferences("mainProgressBar", MODE_PRIVATE);
//                    String mainPBSave = sharedPreferencesForMainPB.getString("progressBarMain", DEFAULT);
//                    progressBar.setProgress(Integer.parseInt(mainPBSave));

                }
            }
        });

        SharedPreferences sharedPreferencesLunch = getSharedPreferences("LunchCalSave", MODE_PRIVATE);
        String lunchcaloriesNum = sharedPreferencesLunch.getString("lunchCal", DEFAULT);
        String lunchPB = sharedPreferencesLunch.getString("lunchProgressBar", DEFAULT);
//        Log.d(caloriesNum, "Calories Number : ");
        LunchTextViewValue.setText(lunchcaloriesNum);
        LunchProgressBar.setProgress(Integer.parseInt(lunchPB));

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

                    setCurrentCalories();

//                    saveMainProgressBar();
//                    SharedPreferences sharedPreferencesForMainPB = getSharedPreferences("mainProgressBar", MODE_PRIVATE);
//                    String mainPBSave = sharedPreferencesForMainPB.getString("progressBarMain", DEFAULT);
//                    progressBar.setProgress(Integer.parseInt(mainPBSave));

                }
            }
        });
        SharedPreferences sharedPreferencesDinner = getSharedPreferences("DinnerCalSave", MODE_PRIVATE);
        String DinnercaloriesNum = sharedPreferencesDinner.getString("DinnerCal", DEFAULT);
        String DinnerProgress = sharedPreferencesDinner.getString("DinnerProgressBar", DEFAULT);
//        Log.d(caloriesNum, "Calories Number : ");
        DinnerTextViewValue.setText(DinnercaloriesNum);
        DinnerProgressBar.setProgress(Integer.parseInt(DinnerProgress));

//                SharedPreferences sharedPreferences111 = getSharedPreferences("ProgressBar1", MODE_PRIVATE);
//                SharedPreferences sharedPreferences1 = getSharedPreferences("currentCal", MODE_PRIVATE);
//                int currentCalValue = sharedPreferences1.getInt("value", Integer.parseInt(DEFAULT));
//                String mainPB = String.valueOf(sharedPreferences111.getInt("progressValue", Integer.parseInt(DEFAULT)));
//                String currentCalories = String.valueOf(sharedPreferences111.getInt("value", Integer.parseInt(DEFAULT)));
//                Log.d(String.valueOf(currentCalValue), "potato");
//                currentCaloriesTextView.setText(String.valueOf(currentCalValue));
//                progressBarMain.setProgress(Integer.parseInt(String.valueOf(currentCalValue)));


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

                    setCurrentCalories();


//                    saveMainProgressBar();
//                    SharedPreferences sharedPreferencesForMainPB = getSharedPreferences("mainProgressBar", MODE_PRIVATE);
//                    String mainPBSave = sharedPreferencesForMainPB.getString("progressBarMain", DEFAULT);
//                    progressBar.setProgress(Integer.parseInt(mainPBSave));
                }
            }
        });
        SharedPreferences sharedPreferencesSnacks = getSharedPreferences("SnacksCalSave", MODE_PRIVATE);
        String SnackscaloriesNum = sharedPreferencesSnacks.getString("SnacksCal", DEFAULT);
        String SnacksProgress = sharedPreferencesSnacks.getString("SnacksProgressBar", DEFAULT);
//        Log.d(caloriesNum, "Calories Number : ");
        SnacksTextViewValue.setText(SnackscaloriesNum);
        SnacksProgressBar.setProgress(Integer.parseInt(SnacksProgress));

//        currentCalories = String.valueOf(retrieveCurrentCalories());
//        targetCalories = retrieveTargetCalories();
////        int snacksValue = retrieveCurrentCalories();
//
//        currentCaloriesTextView.setText(getResources().getString(R.string.current_calories, currentCalories));
//        targetCaloriesTextView.setText(getResources().getString(R.string.target_calories, targetCalories));
//
//        int progressValue = calculateProgressValue(Integer.parseInt(currentCalories), targetCalories);
//        progressBarMain.setMax(retrieveTargetCalories());
//        progressBarMain.setProgress(progressValue);
//
//        String str1 = String.valueOf(progressValue);
//        SharedPreferences sharedPref = getSharedPreferences("ProgressBar1",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("name",str1);
//        editor.putInt("progressBar", water);
//        editor.putInt("progressValue", progressValue);
//        editor.putInt("currentCalories", Integer.parseInt(currentCalories));
//        editor.apply();


//        setCurrentCalories();

//

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferencesWater = getSharedPreferences("waterNumSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPreferencesBreakfast = getSharedPreferences("BreakFastSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPrefLunch = getSharedPreferences("LunchCalSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPrefDinner = getSharedPreferences("DinnerCalSave", Context.MODE_PRIVATE);
                SharedPreferences sharedPrefSnacks = getSharedPreferences("SnacksCalSave", Context.MODE_PRIVATE);

                SharedPreferences.Editor editorWater = sharedPreferencesWater.edit();
                SharedPreferences.Editor editorBreakfast = sharedPreferencesBreakfast.edit();
                SharedPreferences.Editor editorLunch = sharedPrefLunch.edit();
                SharedPreferences.Editor editorDinner = sharedPrefDinner.edit();
                SharedPreferences.Editor editorSnacks = sharedPrefSnacks.edit();

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

                startActivity(new Intent(NutritionPage.this,NutritionPage.class));
            }
        });
    }

    private void setCurrentCalories() {
        currentCalories = retrieveCurrentCalories();
        targetCalories = retrieveTargetCalories();

        currentCaloriesTextView.setText(retrieveCurrentCalories());
        targetCaloriesTextView.setText(getResources().getString(R.string.target_calories, targetCalories));

        int progressValue = calculateProgressValue(currentCalories, targetCalories);
        progressBarMain.setMax(retrieveTargetCalories());
        progressBarMain.setProgress(progressValue);

        String str1 = String.valueOf(progressValue);
        SharedPreferences sharedPref = getSharedPreferences("ProgressBar1",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name",str1);
        editor.putInt("progressBar", water);
        editor.putInt("progressValue", progressValue);
        editor.putInt("currentCalories", currentCalories);

        SharedPreferences mainProgressVal = getSharedPreferences("mainProgress", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = mainProgressVal.edit();
        editor1.putInt("mainProgressBar", progressValue);
        editor1.putString("currentCalValue", String.valueOf(currentCalories));

        editor.apply();
        editor1.apply();

        SharedPreferences sharedPreferences = getSharedPreferences("mainProgress", MODE_PRIVATE);
        int mainValue = sharedPreferences.getInt("mainProgressBar", Integer.parseInt(DEFAULT));
        String currentCalValue = sharedPreferences.getString("currentCalValue", DEFAULT);
        progressBarMain.setProgress(mainValue);
        currentCaloriesTextView.setText(currentCalValue);

        retrieveCurrentCalories();
    }


//    private void saveMainProgressBar(){
//       int progress = setCurrentCalories();
//       Log.d(String.valueOf(progress), "Progress Value");
////       Log.d(String.valueOf(progress), "Record " );
//       String mainProgressBar = String.valueOf(progress);
//       SharedPreferences sharedPreferences = getSharedPreferences("mainProgressBar", MODE_PRIVATE);
//       SharedPreferences.Editor editor = sharedPreferences.edit();
//       editor.putString("progressBarMain", mainProgressBar);
//       editor.apply();
//    }

    private int calculateProgressValue(int currentCalories, int targetCalories) {
        int progress = 0;
        if (targetCalories > 0) {
            progress = (currentCalories - targetCalories) - targetCalories * -1;
        }
        return progress;
    }
    private int retrieveCurrentCalories() {
        int currentCalories = breakfastCaloriesFinal + LunchCaloriesFinal + DinnerCaloriesFinal + SnacksCaloriesFinal;
        SharedPreferences sharedPreferencesForCurrentCalories = getSharedPreferences("currentCal", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesForCurrentCalories.edit();
        editor.putInt("value" , currentCalories);
        editor.apply();
        return currentCalories;
    }
//


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