package com.example.lms.view.activity.admin.course;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.model.Instructor;
import com.example.lms.view.activity.admin.instructor.InstructorActivity;
import com.example.lms.view.activity.admin.instructor.InstructorDetailsActivity;
import com.example.lms.view.adapter.CoursesAdapter;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.GradeViewModel;

public class CoursesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseViewModel courseViewModel;
    private CoursesAdapter adapter;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        alertDialog = new AlertDialog.Builder(this);

        recyclerView = findViewById(R.id.course_recycler_view);
        adapter = new CoursesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courses -> {
            adapter.submitList(courses);
        });


        adapter.setOnItemClickListener(new CoursesAdapter.OnCourseClickListener() {
            @Override
            public void onClickCourse(Course course) {
                Intent intent = new Intent(CoursesActivity.this,
                        CourseDetailsActivity.class);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });

        adapter.setOnDeleteListener(new CoursesAdapter.OnDeleteCourseClickListener() {
            @Override
            public void onDeleteCourse(Course course) {
                deleteCurrentCourse(course);
            }
        });
    }

    private void deleteCurrentCourse(Course course) {
        alertDialog
                .setTitle("Delete course")
                .setMessage("Are you sure you want to delete this course?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    courseViewModel.deleteCourse(course);
                    Toast.makeText(CoursesActivity.this, "Course deleted", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).show();
    }


    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, CourseDetailsActivity.class));
    }
}