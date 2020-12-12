package com.teamone.salesmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teamone.salesmanagement.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public final static String PRODUCT_TABLE_NAME ="product";
    public static final String SQL_PRODUCT = "CREATE TABLE product(" +
            "code TEXT PRIMARY KEY, " +
            "image BLOB, " +
            "name TEXT, " +
            "price TEXT, " +
            "size TEXT" +
            ");";
    public ProductDAO(final Context context){
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public List<Product> getAllProduct(){
        List<Product> listProduct = new ArrayList<>();
        Cursor cursor = db.query(PRODUCT_TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Product product = new Product();
            product.setProductCode(cursor.getString(cursor.getColumnIndex("code")));
            product.setProductImage(cursor.getBlob(cursor.getColumnIndex("image")));
            product.setProductName(cursor.getString(cursor.getColumnIndex("name")));
            product.setProductPrice(cursor.getDouble(cursor.getColumnIndex("price")));
            product.setProductSize(cursor.getString(cursor.getColumnIndex("size")));
            listProduct.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return listProduct;
    }

        public Product getProductById(String productId) {
        Product product = null;
        String selection = "code=?";
        String[] selectionArgs = {productId};
        Cursor cursor = db.query(PRODUCT_TABLE_NAME, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Product();
            product.setProductCode(cursor.getString(cursor.getColumnIndex("code")));
            product.setProductImage(cursor.getBlob(cursor.getColumnIndex("image")));
            product.setProductName(cursor.getString(cursor.getColumnIndex("name")));
            product.setProductPrice(cursor.getDouble(cursor.getColumnIndex("price")));
            product.setProductSize(cursor.getString(cursor.getColumnIndex("size")));
            break;
        }
        cursor.close();
        return product;
    }

    public int insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("code", product.getProductCode());
        values.put("image", product.getProductImage());
        values.put("name", product.getProductName());
        values.put("price", product.getProductPrice());
        values.put("size", product.getProductSize());
        try {
            if (db.insert(PRODUCT_TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int updateProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("image", product.getProductImage());
        values.put("code", product.getProductCode());
        values.put("name", product.getProductName());
        values.put("price", product.getProductPrice());
        values.put("size", product.getProductSize());
        int kq = db.update(PRODUCT_TABLE_NAME, values, "code=?", new String[]{product.getProductCode()});
        if (kq == 0) {
            return -1;
        }
        return 1;
    }
    public int delProduct(String idProduct){
        int kq = db.delete(PRODUCT_TABLE_NAME, "code=?", new String[]{idProduct});
        if (kq==0){
            return -1;
        }
        return 1;
    }
}