package com.namth.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Them_Sua extends AppCompatActivity {
    Spinner spin;
    TextView edt_ma_nv, edt_ho_ten, txt_them_sua;
    Button btnSumbimt;
    public static String KEY_THEM_SUA = "Thêm Nhân Viên";
    public static final String KEY_MA_NV = "manv";
    public static final String KEY_HO_TEN = "hoten";
    public static final String KEY_PHONG_BAN = "phongban";
    ArrayList<PhongBanModel> list =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua);
        spin = findViewById(R.id.spin_phongban);
        edt_ho_ten = findViewById(R.id.edt_hoten);
        edt_ma_nv = findViewById(R.id.edt_manv);
        txt_them_sua = findViewById(R.id.txt_them_sua);
        btnSumbimt = findViewById(R.id.btn_sumbimt);
        txt_them_sua.setText(KEY_THEM_SUA);

        list.add(new PhongBanModel(R.drawable.ic_phongban,"Nhân Sự"));
        list.add(new PhongBanModel(R.drawable.ic_phongban,"Hành Chính"));
        list.add(new PhongBanModel(R.drawable.ic_phongban,"Đào Tạo"));

        NhanVienModel nvModel = (NhanVienModel) getIntent().getSerializableExtra(NhanVien.KEY_NV_MODEL);
        PhongBanAdapter adapter = new PhongBanAdapter(list,Them_Sua.this);
        spin.setAdapter(adapter);
        if (nvModel != null){
            edt_ma_nv.setText(edt_ma_nv.getText().toString());
            edt_ho_ten.setText(edt_ho_ten.getText().toString());

            int position = list.indexOf(nvModel.phongBan);
            spin.setSelection(position);
        }
        btnSumbimt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index =spin.getSelectedItemPosition();
                String pb ="Phòng Ban: " +  list.get(index).phongBan;

                String maNV ="Mã nhân viên: " + edt_ma_nv.getText().toString();
                String hoTen="Họ và tên: " + edt_ho_ten.getText().toString();
                if (hoTen.trim().equals("")){
                    Toast.makeText(Them_Sua.this,"Họ Tên Không được để trống!",Toast.LENGTH_SHORT).show();
                } else if (maNV.trim().equals("")) {
                    Toast.makeText(Them_Sua.this,"Mã Nhân Viên Không được để trống!",Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent();
                    Bundle b = new Bundle();
                    b.putString(KEY_MA_NV,maNV);
                    b.putString(KEY_HO_TEN,hoTen);
                    b.putString(KEY_PHONG_BAN,pb);
                    i.putExtras(b);
                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });
    }
}