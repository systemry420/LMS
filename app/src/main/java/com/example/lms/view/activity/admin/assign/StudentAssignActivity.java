package com.example.lms.view.activity.admin.assign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.model.Student;
import com.example.lms.model.relations.StudentCoursesCrossRef;
import com.example.lms.util.SpinnerItem;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.StudentViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class StudentAssignActivity extends AppCompatActivity {
    private static final String TAG = "StudentAssignActivity";
    private Spinner spinnerCourses;
    private ChipGroup chipGroup;
    private GradeViewModel gradeViewModel;
    private CourseViewModel courseViewModel;
    private StudentViewModel studentViewModel;
    private List<SpinnerItem> gradesList, coursesList;
    private SpinnerItem selectedCourse;
    private Student currentStudent;
    private List<Course> studentCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_assign);

        spinnerCourses = findViewById(R.id.spinner_assign_student_course);

        gradesList = new ArrayList<>();
        coursesList = new ArrayList<>();

        chipGroup = findViewById(R.id.chip_courses);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        Intent intent = getIntent();
        currentStudent = (Student) intent.getSerializableExtra("student");
        setCoursesSpinner(currentStudent.getGradeID());

        studentViewModel.getCoursesOfStudent(currentStudent.getStudentID()).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courseList) {
                if(courseList != null) {
                    for (Course course : courseList) {
                        Log.i(TAG, "onChanged: " + course);
                        addChip(course.getName());
                    }
                }
            }
        });
    }

    private void setCoursesSpinner(long gradeID) {
        ArrayAdapter<SpinnerItem> coursesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, coursesList);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(coursesAdapter);

        courseViewModel.getCoursesOfGrade(gradeID).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                coursesList.add(new SpinnerItem((long) 0, "Please select a course"));
                studentCourses = studentViewModel.getCoursesOfStudent(currentStudent.getStudentID()).getValue();

                for (Course course : courses) {
                    coursesList.add(
                            new SpinnerItem(course.getCourseID(), course.getName()));

//                    Log.i(TAG, "onChanged 222: " +course);
//                    if(studentCourses != null) {
//                        for (Course studentCourse : studentCourses) {
//                            if (course.getCourseID() == studentCourse.getCourseID()) {
//                                continue;
//                            }
//                        }
//                    }
                }
                coursesAdapter.notifyDataSetChanged();
            }
        });

        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (SpinnerItem) parent.getItemAtPosition(position);

                assignCourse(selectedCourse);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void assignCourse(SpinnerItem selectedCourse) {
        if(selectedCourse.getId() == 0) {
            return;
        }

        addChip(selectedCourse.getCaption());

        StudentCoursesCrossRef join = new StudentCoursesCrossRef(currentStudent.getStudentID(), selectedCourse.getId());
        studentViewModel.insertCourseToStudent(join);
    }

    private void addChip(String caption) {
        Chip chip = new Chip(this);
        chip.setChipBackgroundColorResource(R.color.error);
        chip.setTextColor(getResources().getColor(R.color.white));
        chip.setText(caption);
        chip.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
        chipGroup.addView(chip);
    }
}