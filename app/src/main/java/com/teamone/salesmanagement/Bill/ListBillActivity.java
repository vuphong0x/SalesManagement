package com.teamone.salesmanagement.Bill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.teamone.salesmanagement.Product.ProductAdapter;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.BillDAO;
import com.teamone.salesmanagement.database.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class ListBillActivity extends AppCompatActivity {
    List<Bill> billList;
    BillDAO billDAO;
    RecyclerView rvBill;
    BillAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);

        Toolbar toolbar = findViewById(R.id.toolbarDanhSachHoaDon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        billList = new ArrayList<>();
        billDAO = new BillDAO(this);
        billList.addAll(billDAO.getAllBill());

        // RecycleView
        rvBill = findViewById(R.id.rvBill);
        rvBill.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvBill.setLayoutManager(layoutManager);
        adapter = new BillAdapter(billList);
        rvBill.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.themSanPham:
                Intent intent = new Intent(ListBillActivity.this, AddBillActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}