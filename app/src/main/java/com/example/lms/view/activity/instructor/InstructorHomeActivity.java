package com.example.lms.view.activity.instructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.example.lms.view.activity.instructor.evaluation.CorrectExamActivity;
import com.example.lms.view.activity.instructor.exam.AddExamActivity;
import com.example.lms.view.activity.instructor.lecture.AddLectureActivity;

public class InstructorHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_home);
    }

    public void openActivity(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.add_lecture:
                intent = new Intent(this, AddLectureActivity.class);
                startActivity(intent);
                break;
            case R.id.add_exam:
                intent = new Intent(this, AddExamActivity.class);
                startActivity(intent);
                break;
            case R.id.correct_exam:
                intent = new Intent(this, CorrectExamActivity.class);
                startActivity(intent);
                break;
        }
    }
}