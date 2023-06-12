package com.namth.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends BaseAdapter implements Filterable {
    List<NhanVienModel> list;
    List<NhanVienModel> listTim;
    Context c;

    public NhanVienAdapter(List<NhanVienModel> list, Context c){
        this.list = list;
        this.listTim = list;
        this.c = c;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf =((NhanVien)c).getLayoutInflater();
        convertView = inf.inflate(R.layout.item_lv_nhan_vien,null);
        TextView maNV =convertView.findViewById(R.id.txt_ma_nv);
        TextView hoTen =convertView.findViewById(R.id.txt_hoten);
        TextView phongBan=convertView.findViewById(R.id.txt_phongban);
        ImageButton suaNhanVien =convertView.findViewById(R.id.btn_sua);
        ImageButton xoaNhanVien =convertView.findViewById(R.id.btn_xoa);
        maNV.setText(list.get(position).maNV);
        hoTen.setText(list.get(position).hoTen);
        phongBan.setText(list.get(position).phongBan);

        suaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NhanVien)c).suaSinhVien(position);
            }
        });
        xoaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NhanVien)c).xoa(position);
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String tim =constraint.toString();
                if (tim.isEmpty()){
                    list=listTim;
                }else{
                    List<NhanVienModel> lists = new ArrayList<>();
                    for (NhanVienModel x: listTim){
                        if (x.hoTen.toLowerCase().contains(tim.toLowerCase())){
                            lists.add(x);
                        }
                    }
                    list=lists;
                }
                FilterResults filterResults =new FilterResults();
                filterResults.values=list;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list=(ArrayList<NhanVienModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
