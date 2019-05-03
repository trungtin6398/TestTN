package com.example.sqlitedemo.doanhthu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sqlitedemo.MainActivity;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.adapter.AdapterThu;
import com.example.sqlitedemo.database.Database;
import com.example.sqlitedemo.model.Thu;

import java.util.ArrayList;

public class ListThuActivity extends AppCompatActivity {

     TextView btnThemThu, btnHome;
     ListView listViewThu;
     private AdapterThu adapterThu;
     private String DATABASE_NAME = "database.db";
     ArrayList<Thu> thuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listthu);
        btnThemThu = findViewById(R.id.btnThemThu);
        listViewThu = findViewById(R.id.listViewThu);
        btnHome = findViewById(R.id.btnHomeThu);
        thuList = new ArrayList<>();
        adapterThu = new AdapterThu(ListThuActivity.this, thuList);
        listViewThu.setAdapter(adapterThu);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListThuActivity.this, MainActivity.class));
            }
        });

        btnThemThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListThuActivity.this, ThemThuActivity.class));
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ShowList();
    }

    public void ShowList(){
        SQLiteDatabase sqLiteDatabase = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Thu", null);
        if (cursor != null){
            thuList.clear();
            for (int i = 0; i < cursor.getCount(); i++){
                cursor.moveToPosition(i);
                int id = cursor.getInt(0);
                String ten = cursor.getString(1);
                String ngay = cursor.getString(2);
                String gia = cursor.getString(3);

                thuList.add(new Thu(id, ten, ngay,gia));
            }
            adapterThu.notifyDataSetChanged();
        }
    }
}
