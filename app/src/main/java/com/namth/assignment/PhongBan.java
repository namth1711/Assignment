package com.namth.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class PhongBan extends AppCompatActivity {

    EditText timPhongban;
    ListView phongBan;
    ArrayList<PhongBanModel> list =new ArrayList<>();
    PhongBanAdapter adapter =new PhongBanAdapter(list,PhongBan.this);
    public  void  fill(){
        adapter = new PhongBanAdapter(list,PhongBan.this);
        phongBan.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        timPhongban=findViewById(R.id.edtTimpb);
        phongBan = findViewById(R.id.lv_phongban);
        timPhongban.setVisibility(View.GONE);

        list.add(new PhongBanModel(R.drawable.ic_phongban,"Nhân Sự"));
        list.add(new PhongBanModel(R.drawable.ic_phongban,"Hành Chính"));
        list.add(new PhongBanModel(R.drawable.ic_phongban,"Đào Tạo"));
        fill();

        Toolbar toolbar =findViewById(R.id.toolbar_phongban);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chung,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        } else if (item.getItemId()== R.id.menu_tim) {
            if (timPhongban.getVisibility() == View.VISIBLE){
                timPhongban.setVisibility(View.GONE);
            }else {
                timPhongban.setVisibility(View.VISIBLE);
            }
            timPhongban.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                    phongBan.setAdapter(adapter);
                }
                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}