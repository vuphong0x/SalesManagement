package com.teamone.salesmanagement.Product;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamone.salesmanagement.MainActivity;
import com.teamone.salesmanagement.R;
import com.teamone.salesmanagement.database.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rvProduct;
    ProductAdapter adapter;
    List<Product> productList;
    ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productList = new ArrayList<>();
        dao = new ProductDAO(this);
        productList.addAll(dao.getAllProduct());

        // RecycleView
        rvProduct = findViewById(R.id.rvProduct);
        rvProduct.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvProduct.setLayoutManager(layoutManager);
        adapter = new ProductAdapter(productList);
        rvProduct.setAdapter(adapter);

        // ContextMenu
        registerForContextMenu(rvProduct);

        EditText searchP = findViewById(R.id.searchP);
        searchP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    //    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.context_menu, menu);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        int position = -1;
//        try {
//            position = this.getAdapter()).getPosition();
//        } catch (Exception e) {
//            Log.d("TAG", e.getLocalizedMessage(), e);
//            return super.onContextItemSelected(item);
//        }
//        switch (item.getItemId()) {
//            case R.id.ctx_menu_remove_backup:
//                // do your stuff
//                break;
//            case R.id.ctx_menu_restore_backup:
//                // do your stuff
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(ListProductActivity.this, MainActivity.class);
                startActivity(intent1);;
                break;
            case R.id.themSanPham:
                Intent intent = new Intent(ListProductActivity.this, AddProductActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }

}