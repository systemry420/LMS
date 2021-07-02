package com.example.lms.view.activity.instructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.example.lms.view.activity.admin.AdminLoginActivity;

public class InstructorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_login);
    }

    public void openAdminLogin(View view) {
        startActivity(new Intent(this, AdminLoginActivity.class));
    }

    public void loginInstructor(View view) {
        startActivity(new Intent(this, InstructorHomeActivity.class));
    }
}