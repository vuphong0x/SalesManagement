package com.teamone.salesmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teamone.salesmanagement.Bill.ListBillActivity;
import com.teamone.salesmanagement.Customer.ListCustomerActivity;
import com.teamone.salesmanagement.Product.ListProductActivity;
import com.teamone.salesmanagement.Revenue.RevenueActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sanPham(View view) {
        Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
        startActivity(intent);
    }

    public void donHang(View view) {
        Intent intent = new Intent(MainActivity.this, ListBillActivity.class);
        startActivity(intent);
    }

    public void doanhThu(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.muc_tieu);
        EditText editText = dialog.findViewById(R.id.tvMucTieu);
        Button button = dialog.findViewById(R.id.btnTT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RevenueActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("MT", Double.parseDouble(editText.getText().toString()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
dialog.show();
    }

    public void khachHang(View view) {
        Intent intent = new Intent(MainActivity.this, ListCustomerActivity.class);
        startActivity(intent);
    }
}