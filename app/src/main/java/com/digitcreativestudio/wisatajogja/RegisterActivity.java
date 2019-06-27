package com.digitcreativestudio.wisatajogja;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail,
            etPassword,
            etFullname,
            etAddress;
    Button btnRegister;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etFullname = findViewById(R.id.et_fullname);
        etAddress = findViewById(R.id.et_address);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                        etEmail.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty() ||
                        etFullname.getText().toString().isEmpty() ||
                        etAddress.getText().toString().isEmpty()
                ){
                    Toast.makeText(getApplicationContext(), "form harus diisi semua", Toast.LENGTH_LONG).show();
                } else {
                    if(!db.existCheck(etEmail.getText().toString().trim())){
                        db.insertUser(
                                etFullname.getText().toString().trim(),
                                etEmail.getText().toString().trim(),
                                etPassword.getText().toString().trim(),
                                etAddress.getText().toString().trim()
                        );
                        Toast.makeText(getApplicationContext(), "register berhasil", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });
    }

}
