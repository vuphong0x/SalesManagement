package com.teamone.salesmanagement.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;

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
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tieID = findViewById(R.id.tieID);
        tiePhone = findViewById(R.id.tiePhone);
        tieName = findViewById(R.id.tieName);
        tieDateOfBirth = findViewById(R.id.tieDateOfBirth);
        tieAddress = findViewById(R.id.tieAddress);
    }

    public void addKhachHang(View view) {
        dao = new CustomerDAO(this);
        String id = tieID.getText().toString();
        String phone =  tiePhone.getText().toString();
        String name = tieName.getText().toString();
        String dateOfBirth = tieDateOfBirth.getText().toString();
        String address = tieAddress.getText().toString();
        Customer customer = new Customer(id,phone,name,dateOfBirth,address);
        if (validate()<0){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.them_that_bai);
            Button btn = dialog.findViewById(R.id.btnThemThatBai);
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }else {
            if (dao.insertCustomer(customer) > 0){
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_thanh_cong);
                Button btn = dialog.findViewById(R.id.btnThemThanhCong);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(AddCustomerActivity.this,ListCustomerActivity.class));
                        dialog.dismiss();
                    }
                });
            }else {
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_that_bai);
                Button btn = dialog.findViewById(R.id.btnThemThatBai);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        }
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
    public int validate(){
        int check = 1;
        if (tieID.getText().length()==0 || tiePhone.getText().length()==0
                || tieName.getText().length()==0 || tieDateOfBirth.getText().length()==0
                || tieAddress.getText().length()==0){
            check =-1;
        }
        return check;
    }
}