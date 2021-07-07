package com.example.lms.view.activity.instructor.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.model.Exam;
import com.example.lms.model.Grade;
import com.example.lms.model.Lecture;
import com.example.lms.util.SpinnerItem;
import com.example.lms.view.adapter.ExamAdapter;
import com.example.lms.view.adapter.LecturesAdapter;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.ExamViewModel;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.LectureViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddExamActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExamViewModel examViewModel;
    private GradeViewModel gradeViewModel;
    private CourseViewModel courseViewModel;
    private Spinner spinnerGrade, spinnerCourse;
    private SpinnerItem selectedGrade, selectedCourse;
    private ExamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        spinnerCourse = findViewById(R.id.spinner_course_add_exam);
        spinnerGrade = findViewById(R.id.spinner_grade_add_exam);

        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        recyclerView = findViewById(R.id.instructor_exam_recycler_view);

        adapter = new ExamAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        populateGradeSpinner();

    }

    private void populateGradeSpinner() {
        final List<SpinnerItem> gradesList = new ArrayList<>();
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
                populateCourseSpinner(selectedGrade.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void populateCourseSpinner(long gradeID) {
        List<SpinnerItem> coursesList = new ArrayList<>();

        ArrayAdapter<SpinnerItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(arrayAdapter);
        courseViewModel.getCoursesOfGrade(gradeID).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                coursesList.add(new SpinnerItem((long) 0, "Please select a course"));
                for (Course course : courses) {
                    coursesList.add(new SpinnerItem(course.getCourseID(), course.getName() ));
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (SpinnerItem) parent.getItemAtPosition(position);

                fetchExams(selectedCourse);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchExams(SpinnerItem selectedCourse) {
        if (selectedCourse.getId() == 0) {
            return;
        }
        else {
            examViewModel.getExamsOfCourse(selectedCourse.getId())
                .observe(this, new Observer<List<Exam>>() {
                    @Override
                    public void onChanged(List<Exam> exams) {
                        adapter.submitList(exams);
                    }
                });
        }
    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, ExamDetailsActivity.class));
    }


}