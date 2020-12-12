package com.teamone.salesmanagement.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.teamone.salesmanagement.MainActivity;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.CustomerDAO;

import java.util.List;

public class ListCustomerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    ListView lvList;
    CustomerDAO dao;
    List<Customer> customerList;
    CustomerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = new Intent(ListCustomerActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.themSanPham:
                intent = new Intent(ListCustomerActivity.this, AddCustomerActivity.class);
                startActivity(intent);
                break;

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