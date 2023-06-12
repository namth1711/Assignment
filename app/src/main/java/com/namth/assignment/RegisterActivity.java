package com.namth.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText txtname = findViewById(R.id.edt_userdk);
        EditText txtpass = findViewById(R.id.edt_passworđk);
        EditText txtconfirm = findViewById(R.id.edt_nlpassworđk);
        Button btndangki = findViewById(R.id.btn_Register);
        Button btnTrove = findViewById(R.id.btn_TroVe);

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtname.getText().toString();
                String pass = txtpass.getText().toString();
                String confirm = txtconfirm.getText().toString();
                if (name.equals("") || pass.equals("") || confirm.equals("") || !pass.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Dữ liệu sai", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent();
                    Bundle b = new Bundle();
                    b.putString("username", name);
                    b.putString("password", pass);
                    i.putExtras(b);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}