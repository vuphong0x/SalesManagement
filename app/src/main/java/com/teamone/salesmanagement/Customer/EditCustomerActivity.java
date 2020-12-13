package com.teamone.salesmanagement.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;

public class EditCustomerActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextInputEditText tieDetailPhone,tieDetailName,tieDetailDateOfBirth,tieDetailAddress;
    TextView tieDetailID;
    CustomerDAO dao  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tieDetailID = findViewById(R.id.tieDetailID);
        tieDetailPhone = findViewById(R.id.tieDetailPhone);
        tieDetailName =  findViewById(R.id.tieDetailName);
        tieDetailDateOfBirth = findViewById(R.id.tieDetailDateOfBirth);
        tieDetailAddress = findViewById(R.id.tieDetailAddress);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");
        String phone = bundle.getString("phone");
        String name = bundle.getString("name");
        String dateOfBirth = bundle.getString("dateOfBirth");
        String address = bundle.getString("address");

        tieDetailID.setText(id);
        tieDetailPhone.setText(phone);
        tieDetailName.setText(name);
        tieDetailDateOfBirth.setText(dateOfBirth);
        tieDetailAddress.setText(address);
    }

    public void EditKhachHang(View view) {
        dao = new CustomerDAO(this);
        Customer customer = new Customer(tieDetailID.getText().toString().trim(),
                tieDetailPhone.getText().toString().trim(),
                tieDetailName.getText().toString().trim(),
                tieDetailDateOfBirth.getText().toString().trim(),
                tieDetailAddress.getText().toString().trim());
        if (tieDetailID.getText().length()==0 || tieDetailPhone.getText().length()==0
                ||  tieDetailName.getText().length()==0
                ||  tieDetailDateOfBirth.getText().length()==0
                ||  tieDetailAddress.getText().length()==0){
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
            if (dao.updateCustomer(customer) > 0){
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_thanh_cong);
                Button btn = dialog.findViewById(R.id.btnThemThanhCong);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(EditCustomerActivity.this,ListCustomerActivity.class));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(EditCustomerActivity.this, ListCustomerActivity.class);
                startActivity(intent);
                break;
            case R.id.delete:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.xoa);
                Button btnHuy = dialog.findViewById(R.id.btnHuy);
                Button btnDongY = dialog.findViewById(R.id.btnDongY);
                dialog.show();
                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao = new CustomerDAO(EditCustomerActivity.this);
                        if (dao.delCustomer(tieDetailID.getText().toString()) <= 0){
                            startActivity(new Intent(EditCustomerActivity.this,ListCustomerActivity.class));
                        }else {
                            dialog.setContentView(R.layout.xoa_thanh_cong);
                            Button btnOK = dialog.findViewById(R.id.ok);
                            dialog.show();
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(EditCustomerActivity.this,ListCustomerActivity.class));
                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}