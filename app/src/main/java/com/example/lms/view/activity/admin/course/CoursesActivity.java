package com.example.lms.view.activity.admin.course;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.util.SpinnerItem;
import com.example.lms.view.adapter.CoursesAdapter;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.GradeViewModel;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseViewModel courseViewModel;
    private CoursesAdapter adapter;
    private AlertDialog.Builder alertDialog;
    private GradeViewModel gradeViewModel;
    private Spinner spinnerGrade;
    private SpinnerItem selectedGrade;
    private List<SpinnerItem> gradesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        alertDialog = new AlertDialog.Builder(this);
        spinnerGrade = findViewById(R.id.spinner_admin_courses);

        gradesList = new ArrayList<>();

        recyclerView = findViewById(R.id.course_recycler_view);
        adapter = new CoursesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                adapter.submitList(courses);
            }
        });

        populateGradeSpinner();

        setAdapterListeners();
    }

    private void populateGradeSpinner() {
        ArrayAdapter<SpinnerItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gradesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(arrayAdapter);
        gradeViewModel.getAllGrades().observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                gradesList.add(new SpinnerItem((long) 0, "Please select a grade"));
                for (Grade grade : grades) {
                    gradesList.add(new SpinnerItem(grade.getGradeID(), grade.getGradeName()));
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGrade = (SpinnerItem) parent.getItemAtPosition(position);
                fetchCourses(selectedGrade.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchCourses(Long id) {
        if(id == 0) {
            courseViewModel.getCoursesOfGrade(id).observe(this, courses -> {
                adapter.submitList(courses);
            });
        }
        else {
            courseViewModel.getCoursesOfGrade(id).observe(this, courses -> {
                adapter.submitList(courses);
            });
        }
    }


    private void setAdapterListeners() {
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