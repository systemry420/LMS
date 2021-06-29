package com.example.lms.view.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.example.lms.view.activity.admin.course.CoursesActivity;
import com.example.lms.view.activity.admin.grade.GradesActivity;
import com.example.lms.view.activity.admin.instructor.InstructorActivity;
import com.example.lms.view.activity.student.StudentsActivity;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }


    public void openActivity(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.card_grades:
                intent = new Intent(this, GradesActivity.class);
                startActivity(intent);
                break;
            case R.id.card_instructors:
                intent = new Intent(this, InstructorActivity.class);
                startActivity(intent);
                break;
            case R.id.card_courses:
                intent = new Intent(this, CoursesActivity.class);
                startActivity(intent);
                break;
            case R.id.card_students:
                intent = new Intent(this, StudentsActivity.class);
                startActivity(intent);
                break;
        }

    }}