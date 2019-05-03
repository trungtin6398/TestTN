package com.example.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sqlitedemo.database.DbHelper;
import com.example.sqlitedemo.doanhchi.ListChiActivity;
import com.example.sqlitedemo.doanhthu.ListThuActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnThu, btnChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper createtable = new DbHelper(this);
        createtable.open();
        btnThu = findViewById(R.id.btnThu);
        btnChi = findViewById(R.id.btnChi);
        btnThu.setOnClickListener(this);
        btnChi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThu:
                startActivity(new Intent(MainActivity.this, ListThuActivity.class));
                break;
            case R.id.btnChi:
                startActivity(new Intent(MainActivity.this, ListChiActivity.class));
                break;
        }
    }
}
