package com.example.sqlitedemo.model;

public class Chi {
    public int idchi;
    public String tenchi;
    public String ngaychi;
    public String giachi;

    public Chi(int idchi, String tenchi, String ngaychi, String giachi) {
        this.idchi = idchi;
        this.tenchi = tenchi;
        this.ngaychi = ngaychi;
        this.giachi = giachi;
    }

    public int getIdchi() {
        return idchi;
    }

    public void setIdchi(int idchi) {
        this.idchi = idchi;
    }

    public String getTenchi() {
        return tenchi;
    }

    public void setTenchi(String tenchi) {
        this.tenchi = tenchi;
    }

    public String getNgaychi() {
        return ngaychi;
    }

    public void setNgaychi(String ngaychi) {
        this.ngaychi = ngaychi;
    }

    public String getGiachi() {
        return giachi;
    }

    public void setGiachi(String giachi) {
        this.giachi = giachi;
    }
}
