package com.example.lms.view.activity.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lms.R;

public class StudentCourseLecturesActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_lectures);

        recyclerView = findViewById(R.id.student_course_lectures_recyclerview);
    }
}