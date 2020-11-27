package com.teamone.salesmanagement.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;

import java.util.List;

public class ListCustomer extends AppCompatActivity {
    ListView lvList;
    CustomerDAO dao;
    List<Customer> customerList;
    CustomerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        Toolbar toolbar = findViewById(R.id.tbKhachHang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvList = findViewById(R.id.listKhachHang);

        dao = new CustomerDAO(this);
        customerList = dao.getAllCustomer();
        adapter = new CustomerAdapter(this,customerList);
        lvList.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.themKhachHang){
            Intent intent = new Intent(ListCustomer.this, Addcustomer.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        customerList.clear();
        customerList = dao.getAllCustomer();
        adapter.changeDataset(customerList);
    }
}