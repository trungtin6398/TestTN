package com.example.sqlitedemo.doanhthu;

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

public class ThemThuActivity extends AppCompatActivity {

    EditText edtTen, edtNgay, edtGia;
    Button btnLuu, btnHuy;
    private String DATABASE_NAME = "database.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themthu);
        edtTen = findViewById(R.id.edtTenThu);
        edtNgay = findViewById(R.id.edtNgay);
        edtGia = findViewById(R.id.edtTien);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemThuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edtNgay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemThu();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void ThemThu(){
        String tenthu = edtTen.getText().toString();
        String giathu = edtGia.getText().toString();
        String ngaythu = edtNgay.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.C_THU_TEN, tenthu);
        contentValues.put(DbHelper.C_THU_GIA, giathu);
        contentValues.put(DbHelper.C_THU_NGAY, ngaythu);

        SQLiteDatabase sqLiteDatabase = Database.initDatabase(ThemThuActivity.this, DATABASE_NAME);
        sqLiteDatabase.insert("Thu", null, contentValues);

        startActivity(new Intent(ThemThuActivity.this, ListThuActivity.class));
    }

}
