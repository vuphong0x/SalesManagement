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
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.teamone.salesmanagement.MainActivity;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;

public class AddCustomerActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edtPhoneNumber, edtName, edtDateOfBirth, edtAddress, edtID;
    CustomerDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        edtAddress = findViewById(R.id.edtAddress);
    }

    public void addKhachHang(View view) {
        dao = new CustomerDAO(this);
        String id = edtID.getText().toString();
        String phone =  edtPhoneNumber.getText().toString();
        String name = edtName.getText().toString();
        String dateOfBirth = edtDateOfBirth.getText().toString();
        String address = edtAddress.getText().toString();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(AddCustomerActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public int validate(){
        int check = 1;
        if (edtID.getText().length()==0 || edtPhoneNumber.getText().length()==0
                || edtName.getText().length()==0 || edtDateOfBirth.getText().length()==0
                || edtAddress.getText().length()==0){
            check =-1;
        }
        return check;
    }
}