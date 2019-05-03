package com.example.sqlitedemo.dao;

import android.content.Context;

import com.example.sqlitedemo.database.DbHelper;
import com.example.sqlitedemo.model.Thu;

import java.util.ArrayList;

public class ThuDAO {

    DbHelper db;

    public ThuDAO(Context context) {
        this.db = new DbHelper(context);
    }

    public ArrayList<Thu> viewAll(){
        ArrayList<Thu> list = null;
        return list;
    }
}
