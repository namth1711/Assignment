package com.namth.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangChu extends AppCompatActivity {
    Button btnphongBan;
    Button btnnhavien;
    Button btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        btnphongBan=findViewById(R.id.btnPhongBan);
        btnnhavien=findViewById(R.id.btnnhanVien);
        btnthoat=findViewById(R.id.btnThoat);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnnhavien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chuyennv=new Intent(TrangChu.this,NhanVien.class);
                startActivity(chuyennv);
            }
        });
        btnphongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chuyenphb=new Intent(TrangChu.this,PhongBan.class);
                startActivity(chuyenphb);
            }
        });
    }
}