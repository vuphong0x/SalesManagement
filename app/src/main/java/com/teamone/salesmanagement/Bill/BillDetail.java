package com.teamone.salesmanagement.Bill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.teamone.salesmanagement.R;

public class BillDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}