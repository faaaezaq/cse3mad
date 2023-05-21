package com.example.mobileappdev;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    TextView stepsCounter;

    Sensor mStepCounter;

    int stepCount = 0;

    boolean isCounterSensorPresent;

    ProgressBar stepsProgressDisplay;
    ProgressBar caloriesBurned;

    TextView stepsCount;
    TextView calorieConsumedNum;
    TextView caloriesBurnedNum;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SidebarClass sidebar;
    TextView waterIntake;
    public static final String DEFAULT = "0";
    private ProgressBar summaryProgressBar;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sidebar = new SidebarClass(this, drawerLayout, navigationView);
        sidebar.setupSidebar();
        summaryProgressBar = findViewById(R.id.progressBar2);

        stepsProgressDisplay = findViewById(R.id.stepsProgressBar);
        caloriesBurned = findViewById(R.id.calorieBurnedProgress);

        calorieConsumedNum = findViewById(R.id.caloriesConsumed);
        caloriesBurnedNum = findViewById(R.id.caloriesBurned);

        stepsCounter = findViewById(R.id.textView7);
        stepsCount = findViewById(R.id.stepsTextView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        waterIntake = findViewById(R.id.waterIntakeNum);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        String name = sharedPreferences.getString("name",DEFAULT);
        waterIntake.setText(name);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        SharedPreferences sharedPreferences1 = getSharedPreferences("ProgressBar1", MODE_PRIVATE);
        String progress = sharedPreferences1.getString("name", DEFAULT);
        summaryProgressBar.setProgress(Integer.parseInt(progress));
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.clear();

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        } else {
            stepsCounter.setText("No Sensor");
            isCounterSensorPresent = false;
        }
// 94-96 is setting up Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);
        navigationView.setCheckedItem(R.id.home);

        SharedPreferences sharedPreferences2 = getSharedPreferences("ProgressBar1", MODE_PRIVATE);
        calorieConsumedNum.setText(String.valueOf(sharedPreferences2.getInt("progressValue", Integer.parseInt(DEFAULT))));

//        Line 102-109 catch when the Reset button is clcked on the Nutrition page and hence reset the values on the Home page
        boolean resetButtonClicked = getIntent().getBooleanExtra("resetButtonClicked", false);
        if (resetButtonClicked) {
            calorieConsumedNum.setText("0");
            summaryProgressBar.setProgress(0);
            SharedPreferences sharedPreferences3 = getSharedPreferences("ProgressBar1", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sharedPreferences3.edit();
            editor2.clear();
            editor2.apply();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return sidebar.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!sidebar.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
            sensorManager.unregisterListener(this, mStepCounter);
    }
//    line 138-147 : this is when the sensor is present, the steps are being populated in the correct TextView along with the
//    progressBar being updated with it.
    @Override
    public void onSensorChanged (SensorEvent sensorEvent) {
        if (sensorEvent.sensor == mStepCounter) {
            stepCount = (int) sensorEvent.values[0];
            stepsCounter.setText(String.valueOf(stepCount));
            stepsProgressDisplay.setProgress(Integer.parseInt(String.valueOf(stepsCounter.getText())));
            stepsCount.setText(String.valueOf(stepsCounter.getText()));
            int caloriesPerStep = (int) (Integer.parseInt(String.valueOf(stepsCounter.getText())) * 0.04);
            caloriesBurned.setProgress(caloriesPerStep);
            caloriesBurnedNum.setText(String.valueOf(caloriesPerStep));
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}