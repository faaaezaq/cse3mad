package com.example.mobileappdev;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.Toast;

import android.os.Bundle;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    Button button;
    ArrayList barArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getData();
        barChartStyling();

        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,Nutrition_page.class));
            }
        });
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
        barChart.setMinimumWidth(5);

        BarDataSet barDataSet = new BarDataSet(barArrayList, "STEP COUNT");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(10f);

        barChart.getDescription().setEnabled(true);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        String[] daysOfWeek = {" " , " ", "Mon", "Tue", "Wed", "Thurs", "Fri", "Sat","Sun"};
        //TODO: Debug days of the week to figure out why it ignores the first two indexes
        xAxis.setValueFormatter(new IndexAxisValueFormatter(daysOfWeek));
    }
}