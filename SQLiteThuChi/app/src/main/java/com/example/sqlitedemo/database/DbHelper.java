package com.example.sqlitedemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static String TB_THU = "Thu";
    public static String C_THU_ID = "id";
    public static String C_THU_TEN = "tenthu";
    public static String C_THU_NGAY = "ngaythu";
    public static String C_THU_GIA = "giathu";

    public static String TB_CHI = "Chi";
    public static String C_CHI_ID = "id";
    public static String C_CHI_TEN = "tenchi";
    public static String C_CHI_NGAY = "ngaychi";
    public static String C_CHI_GIA = "giachi";

    public DbHelper(Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlthu = "CREATE TABLE "
                + TB_THU + " ("
                + C_THU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + C_THU_TEN + " TEXT NOT NULL, "
                + C_THU_NGAY + " DATE NOT NULL, "
                + C_THU_GIA + " TEXT NOT NULL)";
        db.execSQL(sqlthu);

        String sqlchi = "CREATE TABLE "
                + TB_CHI + " ("
                + C_CHI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + C_CHI_TEN + " TEXT NOT NULL, "
                + C_CHI_NGAY + " DATE NOT NULL, "
                + C_CHI_GIA + " TEXT NOT NULL)";
        db.execSQL(sqlchi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open() {
        return super.getWritableDatabase();
    }
}
