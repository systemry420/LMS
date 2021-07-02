package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.view.activity.instructor.InstructorLoginActivity;
import com.example.lms.view.activity.student.StudentLoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLoginActivity(View view) {
        switch (view.getId()) {
            case R.id.student_portal:
                startActivity(new Intent(this, StudentLoginActivity.class));
                break;
            case R.id.instructor_portal:
                startActivity(new Intent(this, InstructorLoginActivity.class));
                break;
        }
    }
}