package com.example.lms.view.activity.admin.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.example.lms.view.adapter.CoursesAdapter;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.GradeViewModel;

public class CoursesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        recyclerView = findViewById(R.id.course_recycler_view);
        CoursesAdapter adapter = new CoursesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courses -> {
            adapter.submitList(courses);
        });
    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, CourseDetailsActivity.class));
    }
}