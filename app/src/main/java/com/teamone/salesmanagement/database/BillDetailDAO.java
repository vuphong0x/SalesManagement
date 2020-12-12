package com.teamone.salesmanagement.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teamone.salesmanagement.Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class BillDetailDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public final static String CUSTOMER_TABLE_NAME ="BillDetail";
    public static final String SQL_CUSTOMER = "CREATE TABLE BillDetail (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idBill text, " +
            "productId text, " +
            " dateOfBirth text, " +
            " address text" +
            ");";

    public BillDetailDAO(final Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public List<Customer> getAllCustomer() {
        List<Customer> listCustomer = new ArrayList<>();
        Cursor cursor = db.query(BillDetailDAO.CUSTOMER_TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Customer customer = new Customer();
            customer.setIdCustomer(cursor.getString(0));
            customer.setPhone(cursor.getString(1));
            customer.setName(cursor.getString(2));
            customer.setDateOfBirth(cursor.getString(3));
            customer.setAddress(cursor.getString(4));
            listCustomer.add(customer);
            cursor.moveToNext();
        }
        cursor.close();
        return listCustomer;
    }

    public int insertCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put("id", customer.getIdCustomer());
        values.put("phone", customer.getPhone());
        values.put("name", customer.getName());
        values.put("dateOfBirth", customer.getDateOfBirth());
        values.put("address", customer.getAddress());
        try {
            if (db.insert(BillDetailDAO.CUSTOMER_TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {

        }
        return 1;
    }

    public int updateCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put("id", customer.getIdCustomer());
        values.put("phone", customer.getPhone());
        values.put("name", customer.getName());
        values.put("dateOfBirth", customer.getDateOfBirth());
        values.put("address", customer.getAddress());
        int kq = db.update(BillDetailDAO.CUSTOMER_TABLE_NAME, values, "id=?", new String[]{customer.getIdCustomer()});
        if (kq == 0) {
            return -1;
        }
        return 1;
    }

    public int delCustomer(String idCustomer) {
        int kq = db.delete(BillDetailDAO.CUSTOMER_TABLE_NAME, "id=?", new String[]{idCustomer});
        if (kq == 0) {
            return -1;
        }
        return 1;
    }
}
