package com.example.sqlitedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.Chi;

import java.util.ArrayList;

public class AdapterChi extends BaseAdapter {

    Context context;
    ArrayList<Chi> chiArrayList;

    public AdapterChi(Context context, ArrayList<Chi> chiArrayList) {
        this.context = context;
        this.chiArrayList = chiArrayList;
    }

    @Override
    public int getCount() {
        return chiArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return chiArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        ChiHolder holder = new ChiHolder();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.items_chi, null);
            holder.idchi = convertView.findViewById(R.id.tvidChi);
            holder.tenchi = convertView.findViewById(R.id.tvtenChi);
            holder.ngaychi = convertView.findViewById(R.id.tvngayChi);
            holder.giachi = convertView.findViewById(R.id.tvgiaChi);
            convertView.setTag(holder);
        }else {
            holder = (ChiHolder) convertView.getTag();
        }

        Chi chi = chiArrayList.get(position);

        holder.idchi.setText("ID: " + chi.idchi);
        holder.tenchi.setText("Tên: " + chi.tenchi);
        holder.ngaychi.setText("Ngày: " + chi.ngaychi);
        holder.giachi.setText("Giá: " + chi.giachi);
        return convertView;
    }

    public class ChiHolder{
        TextView idchi, tenchi, ngaychi, giachi;
    }
}
