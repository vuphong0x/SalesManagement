package com.teamone.salesmanagement.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teamone.salesmanagement.Bill.Bill;
import com.teamone.salesmanagement.Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public final static String BILL_TABLE_NAME ="bill";
    public static final String SQL_BILL = "CREATE TABLE bill (" +
            "billId text PRIMARY KEY, " +
            "customerName text, " +
            "date text, " +
            "totalMoney text" +
            ");";

    public BillDAO(final Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public List<Bill> getAllBill() {
        List<Bill> billList = new ArrayList<>();
        Cursor cursor = db.query(BillDAO.BILL_TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Bill bill = new Bill();
            bill.setMaHoaDon(cursor.getString(cursor.getColumnIndex("billId")));
            bill.setTenKhachHang(cursor.getString(cursor.getColumnIndex("customerName")));
            bill.setTongTien(cursor.getString(cursor.getColumnIndex("totalMoney")));
            bill.setDate(cursor.getString(cursor.getColumnIndex("date")));
            billList.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return billList;
    }

    public int insertBill(Bill bill) {
        ContentValues values = new ContentValues();
        values.put("billId", bill.getMaHoaDon());
        values.put("customerName", bill.getTenKhachHang());
        values.put("totalMoney", bill.getTongTien());
        values.put("date",bill.getDate());
        try {
            if (db.insert(BillDAO.BILL_TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {

        }
        return 1;
    }

    public int updateBill(Bill bill) {
        ContentValues values = new ContentValues();
        values.put("billId", bill.getMaHoaDon());
        values.put("customerName", bill.getTenKhachHang());
        values.put("totalMoney", bill.getTongTien());
        int kq = db.update(BillDAO.BILL_TABLE_NAME, values, "id=?", new String[]{bill.getMaHoaDon()});
        if (kq == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteBill(String billId) {
        int kq = db.delete(BillDAO.BILL_TABLE_NAME, "billId=?", new String[]{billId});
        if (kq == 0) {
            return -1;
        }
        return 1;
    }

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String SQL = "SELECT SUM(totalMoney) FROM bill where bill.date = date('now')";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
        String SQL = "SELECT SUM(totalMoney) FROM bill where strftime('%m',bill.date) = strftime('%m','now')";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
        String SQL = "SELECT SUM(totalMoney) FROM bill where strftime('%Y',bill.date) = strftime('%Y','now')";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
}
