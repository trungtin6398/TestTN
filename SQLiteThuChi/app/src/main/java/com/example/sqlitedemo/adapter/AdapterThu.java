package com.example.sqlitedemo.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.database.Database;
import com.example.sqlitedemo.doanhthu.UpdateThuActivity;
import com.example.sqlitedemo.model.Thu;

import java.util.ArrayList;

public class AdapterThu extends BaseAdapter {

    Context context;
    ArrayList<Thu> thuArrayList;

    public AdapterThu(Context context, ArrayList<Thu> thuArrayList) {
        this.context = context;
        this.thuArrayList = thuArrayList;
    }

    @Override
    public int getCount() {
        return thuArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return thuArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ThuHolder holder = new ThuHolder();
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items_thu, null);
            holder.id = convertView.findViewById(R.id.tvid);
            holder.tenthu = convertView.findViewById(R.id.tvten);
            holder.ngaythu = convertView.findViewById(R.id.tvngay);
            holder.giathu = convertView.findViewById(R.id.tvgia);
            holder.click = convertView.findViewById(R.id.click);
            convertView.setTag(holder);
        }else {
            holder = (ThuHolder) convertView.getTag();
        }

        final Thu thu = thuArrayList.get(position);

        holder.id.setText("ID: " + thu.id);
        holder.tenthu.setText("Tên: " + thu.ten);
        holder.ngaythu.setText("Ngày: " + thu.ngay);
        holder.giathu.setText("Giá: " + thu.gia + " VNĐ");

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateThuActivity.class);
                intent.putExtra("Sua", thuArrayList.get(position).id);
                context.startActivity(intent);
            }
        });

        holder.click.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có muốn xóa?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(thuArrayList.get(position).id);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        return convertView;
    }

    public class ThuHolder{
        LinearLayout click;
        TextView id, tenthu, ngaythu, giathu;
    }

    public void delete(int svid){
        SQLiteDatabase sqLiteDatabase = Database.initDatabase((Activity)context, "database.db");
        sqLiteDatabase.delete("Thu", "id = ?", new String[]{svid + ""});
        thuArrayList.clear();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Thu", null);
        while (c.moveToNext()){
            int id = c.getInt(0);
            String ten = c.getString(1);
            String ngay = c.getString(2);
            String gia = c.getString(3);

            thuArrayList.add(new Thu(id, ten, ngay, gia));
        }
        notifyDataSetChanged();
    }

}

