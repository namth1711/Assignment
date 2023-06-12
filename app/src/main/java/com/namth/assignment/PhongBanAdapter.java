package com.namth.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhongBanAdapter  extends BaseAdapter implements Filterable {
    ArrayList<PhongBanModel> list = new ArrayList<>();
    ArrayList<PhongBanModel> listTim = new ArrayList<>();
    Context c;
    public PhongBanAdapter(ArrayList<PhongBanModel> list, Context c){
        this.list = list;
        this.listTim=list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(c);
        convertView = inf.inflate(R.layout.item_lv_phong_ban, null);
        TextView phongBan =convertView.findViewById(R.id.txt_phong_ban);
        phongBan.setText(list.get(position).phongBan);

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
                    ArrayList<PhongBanModel> lists = new ArrayList<>();
                    for (PhongBanModel x: listTim){
                        if (x.phongBan.toLowerCase().contains(tim.toLowerCase())){
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
                list=(ArrayList<PhongBanModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
