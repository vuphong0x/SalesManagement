package com.teamone.salesmanagement.Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.ProductDAO;

import java.util.List;

public class ListProduct extends AppCompatActivity {
    ListView listView;
    ProductDAO dao;
    List<Product> productList;
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        Toolbar toolbar = findViewById(R.id.toolbarDanhSachSanPham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listSanPham);

        dao = new ProductDAO(this);
        productList = dao.getAllProduct();
        adapter = new ProductAdapter(this,productList);
        listView.setAdapter(adapter);
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
            Intent intent = new Intent(ListProduct.this, AddProductActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        productList.clear();
        productList = dao.getAllProduct();
        adapter.changeDataset(productList);
    }
}