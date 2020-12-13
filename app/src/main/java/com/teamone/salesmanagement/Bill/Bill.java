package com.teamone.salesmanagement.Bill;

public class Bill {
    String maHoaDon;
    String tenKhachHang;
    String tongTien;
    String date;

    public Bill() {
    }

    public Bill(String maHoaDon, String tenKhachHang, String tongTien, String date) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.tongTien = tongTien;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //    public Bill(String maHoaDon, String tenKhachHang, String tongTien) {
//        this.maHoaDon = maHoaDon;
//        this.tenKhachHang = tenKhachHang;
//        this.tongTien = tongTien;
//    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
