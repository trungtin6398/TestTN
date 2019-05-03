package com.example.sqlitedemo.doanhchi;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.database.Database;
import com.example.sqlitedemo.database.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemChiActivity extends AppCompatActivity {

    EditText edtTenChi, edtNgayChi, edtGiaChi;
    Button btnLuuChi, btnHuyChi;
    private String DATABASE_NAME = "database.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themchi);
        edtTenChi = findViewById(R.id.edtTenChi);
        edtNgayChi = findViewById(R.id.edtNgayChi);
        edtGiaChi = findViewById(R.id.edtTienChi);
        btnHuyChi = findViewById(R.id.btnHuyChi);
        btnLuuChi = findViewById(R.id.btnLuuChi);

        btnHuyChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLuuChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemChi();
            }
        });

        edtNgayChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemChiActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edtNgayChi.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }

    public void ThemChi(){
        String tenchi = edtTenChi.getText().toString();
        String ngaychi = edtNgayChi.getText().toString();
        String giachi = edtGiaChi.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.C_CHI_TEN, tenchi);
        contentValues.put(DbHelper.C_CHI_NGAY, ngaychi);
        contentValues.put(DbHelper.C_CHI_GIA, giachi);

        SQLiteDatabase sqLiteDatabase = Database.initDatabase(ThemChiActivity.this, DATABASE_NAME);
        sqLiteDatabase.insert("Chi", null, contentValues);

        startActivity(new Intent(ThemChiActivity.this, ListChiActivity.class));

    }
}
