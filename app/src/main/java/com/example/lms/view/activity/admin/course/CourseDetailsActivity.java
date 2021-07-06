package com.example.lms.view.activity.admin.course;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lms.MainActivity;
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
    private TextInputEditText txtName, txtDescription;
    private Spinner spinnerGrade;
    private GradeViewModel gradeViewModel; InstructorViewModel instructorViewModel;
    private List<SpinnerItem> gradesList, instructorList;
    private SpinnerItem selectedGrade;
    private ArrayAdapter<SpinnerItem> gradeAdapter, instructorAdapter;
    private Course currentCourse;
    private ImageView imageView;

    CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        txtName = findViewById(R.id.txt_course_name);
        txtDescription = findViewById(R.id.txt_course_desc);
        spinnerGrade = findViewById(R.id.spinner_grade_id);
        
        imageView = findViewById(R.id.course_image);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        setSpinners();

        checkIntent();

    }

    private void checkIntent() {
        Intent intent = getIntent();
        currentCourse = (Course) intent.getSerializableExtra("course");
        if (currentCourse != null) {
            txtName.setText(currentCourse.getName());
            txtDescription.setText(currentCourse.getDescription());
        }
    }

    private void saveCourse() {
        String name = txtName.getText().toString();
        String desc = txtDescription.getText().toString();

        if (validateInput()) {
            if (currentCourse != null) {
                currentCourse.setInstructorID(currentCourse.getInstructorID());
                currentCourse.setName(name);
                currentCourse.setDescription(desc);
                courseViewModel.updateCourse(currentCourse);
            }
            else {
                Course course = new Course(selectedGrade.getId(), 0, name, desc);
                long id = courseViewModel.insertCourse(course);
                Toast.makeText(this, "Course added successfully!", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }

    public boolean validateInput() {
        String name = txtName.getText().toString();
        String desc = txtDescription.getText().toString();

        if (name.equals("")) {
            txtName.setError("Please enter course name");
            return false;
        }
        if (desc.equals("")) {
            txtDescription.setError("Please enter course description");
            return false;
        }
        if(spinnerGrade.getSelectedItemId() == 0) {
            Toast.makeText(this, "Please select a grade", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void setSpinners() {
        gradesList = new ArrayList<>();
        instructorList = new ArrayList<>();

        gradeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gradesList);
        instructorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, instructorList);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instructorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(gradeAdapter);


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

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: " + parent.getItemAtPosition(position));
                selectedGrade = (SpinnerItem) parent.getItemAtPosition(position);
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
                saveCourse();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}