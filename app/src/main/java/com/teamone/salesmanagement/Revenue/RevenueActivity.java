package com.teamone.salesmanagement.Revenue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.teamone.salesmanagement.R;

import java.util.ArrayList;

public class RevenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);


        // PieChart Revenue
        PieChart pieChart = findViewById(R.id.pieChart);
        // Data
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(500));
        visitors.add(new PieEntry(1000));

        PieDataSet pieDataSet = new PieDataSet(visitors, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();
    }
}