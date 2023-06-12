package com.namth.assignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NhanVien extends AppCompatActivity {
    EditText timNhanvien;
    Button btn_themNV;
    ListView lvSinhVien;
    private NhanVienModel nhanVienModel;
    ArrayList<NhanVienModel> list =new ArrayList<>();
    NhanVienAdapter adapter = new NhanVienAdapter(list,NhanVien.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        timNhanvien=findViewById(R.id.edtTimnv);
        timNhanvien.setVisibility(View.GONE);
        btn_themNV = findViewById(R.id.btn_themnhanvien);
        lvSinhVien = findViewById(R.id.lv_nhanvien);
        docListDulieu();

        if (list.size() == 0) {
            ds();
        }

        Toolbar toolbar =findViewById(R.id.toolbar_nhanvien);
        setSupportActionBar(toolbar);
        fill();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_themNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NhanVien.this, Them_Sua.class);
                them.launch(i);
            }
        });
    }
    ActivityResultLauncher<Intent> them = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        Them_Sua.KEY_THEM_SUA="Thêm Nhân Viên";
                        String maNV = b.getString(Them_Sua.KEY_MA_NV);
                        String hoTen = b.getString(Them_Sua.KEY_HO_TEN);
                        String pb = b.getString(Them_Sua.KEY_PHONG_BAN);
                        list.add(new NhanVienModel(maNV,hoTen,pb));
                        fill();
                        luuListDulieu(list);
                    }
                }
            }
    );
    public static final String KEY_NV_MODEL = "nv_model";
    ActivityResultLauncher<Intent> sua = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String maNV = b.getString(Them_Sua.KEY_MA_NV);
                        String hoTen = b.getString(Them_Sua.KEY_HO_TEN);
                        String pb = b.getString(Them_Sua.KEY_PHONG_BAN);

                        Them_Sua.KEY_THEM_SUA="Sửa Nhân Viên";
                        nhanVienModel.phongBan = pb;
                        nhanVienModel.maNV = maNV;
                        nhanVienModel.hoTen = hoTen;
                        fill();
                    }
                }
            }
    );
    Activity activity;
    public void xoa(int index){
        if (NhanVien.this != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(NhanVien.this);
            builder.setMessage("Bạn có chắc chắn muốn xóa?");
            builder.setPositiveButton("Có", (dialog, which) -> {
                list.remove(index);
                fill();
                luuListDulieu(list);
            });
            builder.setNegativeButton("Không", null);
            builder.show();
        }
    }
    public void suaSinhVien(int i){
        Intent intent = new Intent(NhanVien.this, Them_Sua.class);
        nhanVienModel = list.get(i);

        intent.putExtra(KEY_NV_MODEL,nhanVienModel);
        sua.launch(intent);
        luuListDulieu(list);
    }
    private void ds(){
        list.add(new NhanVienModel("Mã Nhân Viên: NV001","Họ và tên: Nhân Viên A","Phòng Ban: Nhân Sự"));
        list.add(new NhanVienModel("Mã Nhân Viên: NV002","Họ và tên: Nhân Viên B","Phòng Ban: Hành Chính"));
        list.add(new NhanVienModel("Mã Nhân Viên: NV003","Họ và tên: Nhân Viên C","Phòng Ban: Đào Tạo"));
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
            if (timNhanvien.getVisibility() == View.VISIBLE){
                timNhanvien.setVisibility(View.GONE);
            }else {
                timNhanvien.setVisibility(View.VISIBLE);
            }
            timNhanvien.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                    lvSinhVien.setAdapter(adapter);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
    public  void  fill(){
        adapter = new NhanVienAdapter(list,NhanVien.this);
        lvSinhVien.setAdapter(adapter);
    }
    String FILE_NAME = "nhanvien.txt";
    public void luuListDulieu (ArrayList<NhanVienModel> list) {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {

        }
    }
    public void docListDulieu () {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            list = (ArrayList<NhanVienModel>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {

        }
    }
}