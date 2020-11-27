package com.teamone.salesmanagement.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;

public class Addcustomer extends AppCompatActivity {
    TextInputEditText tiePhone,tieName,tieDateOfBirth,tieAddress,tieID;
    CustomerDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);
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
                        startActivity(new Intent(Addcustomer.this,ListCustomer.class));
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