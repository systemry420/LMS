package com.example.lms.view.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.google.android.material.textfield.TextInputEditText;

public class AdminLoginActivity extends AppCompatActivity {
    TextInputEditText txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        txtEmail = findViewById(R.id.txt_admin_login_email);
        txtPassword = findViewById(R.id.txt_admin_login_password);
    }

    public void loginAdmin(View view) {
        startActivity(new Intent(this, AdminHomeActivity.class));
        if (txtEmail.getText().toString().equals("admin")
            && txtPassword.getText().toString().equals("admin")) {

        }

    }
}