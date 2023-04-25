package com.example.mobileappdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
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
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ArrayList barArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getData();
        barChartStyling();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        NavigationbarClass.setupBottomNavigation(bottomNavigationView, this);

        //TODO: debug why floatingHomeButton doesnt work
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