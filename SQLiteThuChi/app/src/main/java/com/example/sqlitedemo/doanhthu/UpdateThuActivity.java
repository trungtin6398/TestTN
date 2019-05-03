package com.example.sqlitedemo.doanhthu;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

public class UpdateThuActivity extends AppCompatActivity {

    EditText edtTenThuSua, edtNgayThuSua, edtGiaThuSua;
    Button btnCapNhat, btnHuySua;
    int id = -1;
    private String DATABASE_NAME = "database.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatethu);
        edtTenThuSua = findViewById(R.id.edtTenThuSua);
        edtNgayThuSua = findViewById(R.id.edtNgaySua);
        edtGiaThuSua = findViewById(R.id.edtTienSua);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnHuySua = findViewById(R.id.btnHuyThuSua);

        btnHuySua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        edtNgayThuSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateThuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edtNgayThuSua.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        Intent intent = getIntent();
        id = intent.getIntExtra("Sua", -1);
        SQLiteDatabase sqLiteDatabase = Database.initDatabase(UpdateThuActivity.this, DATABASE_NAME);
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Thu WHERE id = ?", new String[]{id + ""});
        c.moveToFirst();
        String ten = c.getString(1);
        String ngay = c.getString(2);
        String gia = c.getString(3);

        edtTenThuSua.setText(ten);
        edtNgayThuSua.setText(ngay);
        edtGiaThuSua.setText(gia);
    }

    public void update(){
        String ten = edtTenThuSua.getText().toString();
        String ngay = edtNgayThuSua.getText().toString();
        String gia = edtGiaThuSua.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.C_THU_TEN, ten);
        contentValues.put(DbHelper.C_THU_NGAY, ngay);
        contentValues.put(DbHelper.C_THU_GIA, gia);
        SQLiteDatabase sqLiteDatabase = Database.initDatabase(UpdateThuActivity.this, DATABASE_NAME);
        sqLiteDatabase.update("Thu", contentValues, "id = ?", new String[]{id + ""});
        startActivity(new Intent(UpdateThuActivity.this, ListThuActivity.class));
    }
}
