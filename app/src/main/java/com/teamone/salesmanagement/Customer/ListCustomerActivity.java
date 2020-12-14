package com.teamone.salesmanagement.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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


        EditText searchC = findViewById(R.id.searchC);
        searchC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("Text [" + charSequence + "] - Start [" + i + "] - Before [" + i1 + "] - Count [" + i2 + "]");
                if (i2<i1){
                    adapter.resetData1();
                }
                adapter.getFilter().filter(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
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
//    @Override
//    protected void onResume() {
//        super.onResume();
//        customerList.clear();
//        customerList = dao.getAllCustomer();
//        adapter.changeDataset(customerList);
//    }
}