package com.example.mobileappdev;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class HomePage extends AppCompatActivity implements SensorEventListener {
    ArrayList barArrayList;

    SensorManager sensorManager;
    TextView stepsCounter;

    Sensor mStepCounter;

    int stepCount = 0;

    //boolean running = false;

    boolean isCounterSensorPresent;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SidebarClass sidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sidebar = new SidebarClass(this, drawerLayout, navigationView);
        sidebar.setupSidebar();

        stepsCounter = findViewById(R.id.textView7);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//            stepsCounter.getDisplay(stepCount);
            Log.d(TAG, "Steps are " + mStepCounter);
            isCounterSensorPresent = true;
        } else {
            stepsCounter.setText("Counter Sensor is not present");
            isCounterSensorPresent = false;
        }

        getData();
        barChartStyling();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);

        //TODO: debug why floatingHomeButton doesnt work
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


    /*@Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener((SensorEventListener) this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor Not Found!", Toast.LENGTH_LONG).show();

        }
    }*/



    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
            sensorManager.unregisterListener(this, mStepCounter);
        //To Unregister
    }



    @Override
    public void onSensorChanged (SensorEvent sensorEvent) {
        if (sensorEvent.sensor == mStepCounter) {
            stepCount = (int) sensorEvent.values[0];
            stepsCounter.setText(String.valueOf(stepCount));
            Log.d(TAG, "Step count: " + stepCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void getData(){
        barArrayList= new ArrayList();
        barArrayList.add(new BarEntry(2f, 1890));
        barArrayList.add(new BarEntry(3f, 2033));
        barArrayList.add(new BarEntry(4f, 3034));
        barArrayList.add(new BarEntry(5f, 4780));
        barArrayList.add(new BarEntry(6f, 5320));
        barArrayList.add(new BarEntry(7f, 5220));
        barArrayList.add(new BarEntry(8f, 5021));
    }

    private void barChartStyling(){
        BarChart barChart=findViewById(R.id.barchartUI);
        Description description = new Description();
        description.setText("Steps count");
        barChart.setDescription(description);

        BarDataSet barDataSet = new BarDataSet(barArrayList, "STEP COUNT");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barChart.getDescription().setEnabled(true);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        String[] daysOfWeek = {"", "", "Mon", "Tue", "Wed", "Thurs", "Fri", "Sat","Sun"};
        //TODO: Debug days of the week to figure out why it ignores the first two indexes
        xAxis.setValueFormatter(new IndexAxisValueFormatter(daysOfWeek));
    }

}