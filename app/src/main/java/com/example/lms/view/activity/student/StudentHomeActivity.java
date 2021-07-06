package com.example.lms.view.activity.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.view.adapter.StudentHomeCoursesAdapter;
import com.example.lms.view.adapter.StudentHomeExamAdapter;
import com.example.lms.viewmodel.ExamViewModel;
import com.example.lms.viewmodel.StudentViewModel;

import java.util.List;

public class StudentHomeActivity extends AppCompatActivity {
    private static final String TAG = "StudentHomeActivity";
    private RecyclerView recyclerView, examsRecyclerView;
    private StudentViewModel studentViewModel;
    private SharedPreferences sharedPreferences;
    private ExamViewModel examViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        sharedPreferences = getSharedPreferences("user_student", MODE_PRIVATE);

        recyclerView = findViewById(R.id.student_home_courses_recyclerview);
        examsRecyclerView = findViewById(R.id.student_home_exams_recyclerview);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        StudentHomeCoursesAdapter adapter = new StudentHomeCoursesAdapter();
        StudentHomeExamAdapter examAdapter = new StudentHomeExamAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        examsRecyclerView.setAdapter(examAdapter);
        examsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.i(TAG, "onCreate: " + sharedPreferences.getLong("studentID", 0));

        studentViewModel.getCoursesOfStudent(sharedPreferences.getLong("studentID", 0))
                .observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courseList) {
                adapter.submitList(courseList);
            }
        });

        adapter.setOnCourseClickListener(new StudentHomeCoursesAdapter.OnCourseClickListener() {
            @Override
            public void onClickCourse(Course course) {
                Intent intent = new Intent(StudentHomeActivity.this,
                        StudentCourseLecturesActivity.class);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });

    }
}