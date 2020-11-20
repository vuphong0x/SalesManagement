package com.teamone.salesmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    }

    public void doanhThu(View view) {
    }

    public void khachHang(View view) {
    }
}