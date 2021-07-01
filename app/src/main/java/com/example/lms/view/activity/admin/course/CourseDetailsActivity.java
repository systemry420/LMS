package com.example.lms.view.activity.admin.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.util.SpinnerItem;
import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.InstructorViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {
    private static final String TAG = "CourseDetailsActivity";
    TextInputEditText txtName, txtDescription;
    Spinner spinnerGrade, spinnerInstructor;
    GradeViewModel gradeViewModel; InstructorViewModel instructorViewModel;
    List<SpinnerItem> gradesList, instructorList;
    SpinnerItem gradeID, instructorID;


    CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        txtName = findViewById(R.id.txt_course_name);
        txtDescription = findViewById(R.id.txt_course_desc);
        spinnerGrade = findViewById(R.id.spinner_grade_id);
        spinnerInstructor = findViewById(R.id.spinner_instructor_id);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        gradesList = new ArrayList<>();
        instructorList = new ArrayList<>();

        ArrayAdapter<SpinnerItem> gradeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gradesList);
        ArrayAdapter<SpinnerItem> instructorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, instructorList);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instructorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(gradeAdapter);
        spinnerInstructor.setAdapter(instructorAdapter);

        gradeViewModel.getAllGrades().observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                gradesList.add(new SpinnerItem((long) 0, "Please select a grade"));
                for (Grade grade : grades) {
                    gradesList.add(new SpinnerItem(grade.getGradeID(), grade.getGradeName()));
                }
                gradeAdapter.notifyDataSetChanged();
            }
        });

        instructorViewModel.getAllInstructors().observe(this, new Observer<List<Instructor>>() {
            @Override
            public void onChanged(List<Instructor> instructors) {
                instructorList.add(new SpinnerItem((long)0, "Please select an instructor"));
                for(Instructor instructor:instructors) {
                    instructorList.add(new SpinnerItem(instructor.getInstructorID(), instructor.getName()));
                }

                instructorAdapter.notifyDataSetChanged();
            }
        });

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: " + parent.getItemAtPosition(position));
                gradeID = (SpinnerItem) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerInstructor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                instructorID = (SpinnerItem) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveInstructor();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveInstructor() {
        String name = txtName.getText().toString();
        String desc = txtDescription.getText().toString();

        if (name.equals("")) {
            txtName.setError("Please enter course name");
            return;
        }
        if (desc.equals("")) {
            txtDescription.setError("Please enter course description");
            return;
        }
        if (spinnerInstructor.getSelectedItemId() == 0) {
            Toast.makeText(this, "Please select an instructor", Toast.LENGTH_LONG).show();
            return;
        }
        if(spinnerGrade.getSelectedItemId() == 0) {
            Toast.makeText(this, "Please select a grade", Toast.LENGTH_LONG).show();
            return;
        }

        Course course = new Course(0, 0, name, desc);
        long id = courseViewModel.insertCourse(course);
        Toast.makeText(this, "Course added successfully!", Toast.LENGTH_LONG).show();
        finish();
    }

}