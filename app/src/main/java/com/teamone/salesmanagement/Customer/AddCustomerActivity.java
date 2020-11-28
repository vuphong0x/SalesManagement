package com.teamone.salesmanagement.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.teamone.salesmanagement.R;

public class AddCustomerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextInputEditText tiePhone,tieName,tieDateOfBirth,tieAddress,tieID;
    CustomerDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void addKhachHang(View view) {
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.themSanPham:
                Intent intent = new Intent(AddCustomerActivity.this, AddCustomerActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}