package com.example.sqlitedemo.model;

public class Thu {
    public int id;
    public String ten;
    public String ngay;
    public String gia;

    public Thu(int id, String ten, String ngay, String gia) {
        this.id = id;
        this.ten = ten;
        this.ngay = ngay;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
