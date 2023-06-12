package com.namth.assignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    private static final String Tag = "dangnhap";
    CheckBox chkremeber;
    EditText edtname;
    EditText edtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtname = findViewById(R.id.edt_user);
        edtpass = findViewById(R.id.edt_pass);
        Button btndangnhap = findViewById(R.id.btn_DangNhap);
        Button btndangki = findViewById(R.id.btn_DangKy);
        chkremeber = findViewById(R.id.chk_Remember);

        checkRemeber();
        ActivityResultLauncher dangnhap = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent i = result.getData();
                            Bundle acc = i.getExtras();
                            String u = acc.getString("username");
                            String p = acc.getString("password");
                            edtname.setText(u);
                            edtpass.setText(p);
                        }
                    }
                }
        );
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences lay = getSharedPreferences("abc", MODE_PRIVATE);
                String u = lay.getString("Username", "");
                String p = lay.getString("password", "");
                if (edtname.getText().toString().equals(u) && edtpass.getText().toString().equals(p)) {
                    Intent i = new Intent(LoginActivity.this, TrangChu.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    if (chkremeber.isChecked()) {
                        remeber(edtname.getText().toString(), edtpass.getText().toString(), true);
                    } else {
                        remeber("", "", false);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void remeber(String user, String pass, boolean chkRemeber){
        SharedPreferences sharedPreferences = getSharedPreferences("remeber",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.putBoolean("remeber",chkRemeber);
        editor.apply();
    }
    public void checkRemeber(){
        SharedPreferences sharedPreferences = getSharedPreferences("remeber",MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");
        String pass = sharedPreferences.getString("pass","");
        boolean chkRemeber1 = sharedPreferences.getBoolean("remeber",false);
        chkremeber.setChecked(chkRemeber1);
        if (chkremeber.isChecked()){
             edtname.setText(user);
             edtpass.setText(pass);
        }
    }
}
