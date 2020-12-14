package com.teamone.salesmanagement.Revenue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.teamone.salesmanagement.MainActivity;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.BillDAO;

import java.util.ArrayList;
import java.util.Random;

public class RevenueActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private PieChart chartDayRevenue, chartMonthRevenue, chartYearRevenue;
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        billDAO = new BillDAO(this);
        double doanhThu = billDAO.getDoanhThuTheoNgay();
        double doanhThu1 = billDAO.getDoanhThuTheoThang();
        double doanhThu2 = billDAO.getDoanhThuTheoNam();
        // PieChart Revenue
        chartDayRevenue = findViewById(R.id.chartDayRevenue);
        chartMonthRevenue = findViewById(R.id.chartMonthRevenue);
        chartYearRevenue = findViewById(R.id.chartYearRevenue);

        // Data
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry((float) doanhThu, "Thu về"));
        visitors.add(new PieEntry((float) bundle.getDouble("MT"), "Mục tiêu"));
        PieDataSet pieDataSet = new PieDataSet(visitors, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.parseColor("#000000"));
        pieDataSet.setValueTextSize(16f);
        PieData pieData = new PieData(pieDataSet);
        chartDayRevenue.setData(pieData);
        chartDayRevenue.getDescription().setEnabled(false);
        chartDayRevenue.animate();

        ArrayList<PieEntry> visitors1 = new ArrayList<>();
        visitors1.add(new PieEntry((float) doanhThu1, "Thu về"));
        visitors1.add(new PieEntry((float) bundle.getDouble("MT")*30, "Mục tiêu"));
        PieDataSet pieDataSet1 = new PieDataSet(visitors1, "");
        pieDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet1.setValueTextColor(Color.parseColor("#000000"));
        pieDataSet1.setValueTextSize(16f);
        PieData pieData1 = new PieData(pieDataSet1);
        chartMonthRevenue.setData(pieData1);
        chartMonthRevenue.getDescription().setEnabled(false);
        chartMonthRevenue.animate();

        ArrayList<PieEntry> visitors2 = new ArrayList<>();
        visitors2.add(new PieEntry((float) doanhThu2, "Thu về"));
        visitors2.add(new PieEntry((float) bundle.getDouble("MT")*30*12, "Mục tiêu"));
        PieDataSet pieDataSet2 = new PieDataSet(visitors2, "");
        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setValueTextColor(Color.parseColor("#000000"));
        pieDataSet2.setValueTextSize(16f);
        PieData pieData2 = new PieData(pieDataSet2);
        chartYearRevenue.setData(pieData2);
        chartYearRevenue.getDescription().setEnabled(false);
        chartYearRevenue.animate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(RevenueActivity.this, MainActivity.class);
            startActivity(intent);// close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}