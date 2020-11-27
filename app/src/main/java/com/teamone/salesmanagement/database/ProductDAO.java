package com.teamone.salesmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teamone.salesmanagement.Customer.Customer;
import com.teamone.salesmanagement.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public final static String PRODUCT_TABLE_NAME ="product";
    public static final String SQL_PRODUCT = "CREATE TABLE product (" +
            " id text primary key, " +
            " image integer , " +
            " name text, " +
            " price text, " +
            " address text" +
            ");";
    public ProductDAO(final Context context){
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public List<Product> getAllProduct(){
        List<Product> listProduct = new ArrayList<>();
        Cursor cursor = db.query(PRODUCT_TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Product product = new Product();
            product.setId(cursor.getString(0));
            product.setImg(cursor.getInt(1));
            product.setName(cursor.getString(2));
            product.setPrice(cursor.getString(3));
            product.setAddress(cursor.getString(4));
            listProduct.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return listProduct;
    }
    public int insertProduct(Product product){
        ContentValues values = new ContentValues();
        values.put("image",product.getImg());
        values.put("id",product.getId());
        values.put("name",product.getName());
        values.put("price",product.getPrice());
        values.put("address",product.getAddress());
        try{
            if (db.insert(PRODUCT_TABLE_NAME,null,values) < 0){
                return -1;
            }
        }catch (Exception e ){

        }
        return 1;
    }
    public int updateProduct(Product product){
        ContentValues values = new ContentValues();
        values.put("image",product.getImg());
        values.put("id",product.getId());
        values.put("name",product.getName());
        values.put("price",product.getPrice());
        values.put("address",product.getAddress());
        int kq = db.update(PRODUCT_TABLE_NAME,values,"id=?",new String[]{product.getId()});
        if (kq==0){
            return -1;
        }
        return 1;
    }
    public int delProduct(String idProduct){
        int kq = db.delete(PRODUCT_TABLE_NAME,"id=?",new String[]{idProduct});
        if (kq==0){
            return -1;
        }
        return 1;
    }
}
