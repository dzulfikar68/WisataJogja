package com.digitcreativestudio.wisatajogja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,
            etPassword;
    TextView btnRegister;
    Button btnLogin;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.loginCheck(etEmail.getText().toString().trim(), etPassword.getText().toString().trim())){
                    SharedPrefManager.getInstance(getApplicationContext())
                            .setLogin(
                                    etEmail.getText().toString().trim()
                            );
                    Toast.makeText(getApplicationContext(), "login berhasil", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
