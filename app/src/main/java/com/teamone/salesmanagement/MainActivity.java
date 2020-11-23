package com.teamone.salesmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.teamone.salesmanagement.Bill.ListBill;
import com.teamone.salesmanagement.Customer.ListCustomer;
import com.teamone.salesmanagement.Product.ListProduct;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sanPham(View view) {
        Intent intent = new Intent(MainActivity.this, ListProduct.class);
        startActivity(intent);
    }

    public void donHang(View view) {
        Intent intent = new Intent(MainActivity.this, ListBill.class);
        startActivity(intent);
    }

    public void doanhThu(View view) {
        Intent intent = new Intent(MainActivity.this, ListBill.class);
        startActivity(intent);
    }

    public void khachHang(View view) {
        Intent intent = new Intent(MainActivity.this, ListCustomer.class);
        startActivity(intent);
    }
}