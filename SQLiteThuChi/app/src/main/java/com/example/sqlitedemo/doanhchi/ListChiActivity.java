package com.example.sqlitedemo.doanhchi;

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
import com.example.sqlitedemo.adapter.AdapterChi;
import com.example.sqlitedemo.database.Database;
import com.example.sqlitedemo.model.Chi;

import java.util.ArrayList;

public class ListChiActivity extends AppCompatActivity {

    ListView listViewChi;
    TextView btnThemChi, btnHome;
    ArrayList<Chi> chiArrayList;
    AdapterChi adapterChi;
    private String DATABASE_NAME = "database.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listchi);
        listViewChi = findViewById(R.id.listchi);
        btnThemChi = findViewById(R.id.btnThemChi);
        btnHome = findViewById(R.id.btnHomeChi);

        btnThemChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListChiActivity.this, ThemChiActivity.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListChiActivity.this, MainActivity.class));
            }
        });

        chiArrayList = new ArrayList<>();
        adapterChi = new AdapterChi(ListChiActivity.this, chiArrayList);
        listViewChi.setAdapter(adapterChi);
        ShowChi();

    }

    public void ShowChi(){
        SQLiteDatabase sqLiteDatabase = Database.initDatabase(ListChiActivity.this, DATABASE_NAME);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Chi", null);
        if (cursor != null){
            chiArrayList.clear();
            for (int i = 0; i < cursor.getCount(); i++){
                cursor.moveToPosition(i);
                int idchi = cursor.getInt(0);
                String tenchi = cursor.getString(1);
                String ngaychi = cursor.getString(2);
                String giachi = cursor.getString(3);
                chiArrayList.add(new Chi(idchi, tenchi, ngaychi, giachi));
            }
            adapterChi.notifyDataSetChanged();
        }
    }
}
