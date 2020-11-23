package com.teamone.salesmanagement.Revenue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.teamone.salesmanagement.R;

public class Revenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}