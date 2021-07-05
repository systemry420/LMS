package com.example.lms.view.activity.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.view.adapter.StudentHomeCoursesAdapter;
import com.example.lms.viewmodel.StudentViewModel;

import java.util.List;

public class StudentHomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        recyclerView = findViewById(R.id.student_home_courses_recyclerview);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        StudentHomeCoursesAdapter adapter = new StudentHomeCoursesAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentViewModel.getCoursesOfStudent(1).observe(this, new Observer<List<Course>>() {
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