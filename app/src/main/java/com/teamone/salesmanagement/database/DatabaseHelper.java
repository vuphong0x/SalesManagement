
package com.teamone.salesmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context,"salesmanagement.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CustomerDAO.SQL_CUSTOMER);
        db.execSQL(ProductDAO.SQL_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS " + CustomerDAO.CUSTOMER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProductDAO.PRODUCT_TABLE_NAME);

    }
}