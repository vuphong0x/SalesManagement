package com.teamone.salesmanagement.Revenue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.teamone.salesmanagement.R;

import java.util.ArrayList;

public class RevenueActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private PieChart chartDayRevenue, chartMonthRevenue, chartYearRevenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // PieChart Revenue
        chartDayRevenue = findViewById(R.id.chartDayRevenue);
        chartMonthRevenue = findViewById(R.id.chartMonthRevenue);
        chartYearRevenue = findViewById(R.id.chartYearRevenue);

        // Data
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(500, "Doanh thu"));
        visitors.add(new PieEntry(1000, "Dự kiến"));

        PieDataSet pieDataSet = new PieDataSet(visitors, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        chartDayRevenue.setData(pieData);
        chartDayRevenue.getDescription().setEnabled(false);
        chartDayRevenue.animate();

        chartMonthRevenue.setData(pieData);
        chartMonthRevenue.getDescription().setEnabled(false);
        chartMonthRevenue.animate();

        chartYearRevenue.setData(pieData);
        chartYearRevenue.getDescription().setEnabled(false);
        chartYearRevenue.animate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}