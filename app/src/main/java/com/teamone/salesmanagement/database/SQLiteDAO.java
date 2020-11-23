package com.teamone.salesmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDAO {

    private static final String DATABASE_NAME = "salesmanagement.db";
    private static final int DATABASE_VERSION =1;

    private final SQLiteDatabase database;
    private final SQLiteOpenHelper helper;

    public SQLiteDAO(final Context context){
        this.helper = new SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(ConstantKey.CREATE_CUSTOMER_TABLE);
                db.execSQL(ConstantKey.CREATE_PRODUCT_TABLE);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int i, int i1) {
                db.execSQL(ConstantKey.DROP_CUSTOMER_TABLE);
                db.execSQL(ConstantKey.DROP_PRODUCT_TABLE);
                this.onCreate(db);
            }
        };
        this.database = this.helper.getWritableDatabase();
    }

    public long addData(String tableName, ContentValues values){
        long data = this.database.insert(tableName,null,values);
        return data;
    }
    public long deleteDataByID(String tableName, final  String id){
        long data = this.database.delete(tableName,ConstantKey.COLUMN_ID + "= ?",new String[]{String.valueOf(id)});
        return data;
    }
    public Cursor getAllData(String query) {
        final Cursor cursor = this.database.rawQuery(query, null);
        return cursor;
    }

    public long updateById(String tableName, ContentValues values, String id) {
        long data = this.database.update(tableName, values, ConstantKey.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return data;
    }

}
