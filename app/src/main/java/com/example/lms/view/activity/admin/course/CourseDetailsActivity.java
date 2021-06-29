package com.example.lms.view.activity.admin.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.viewmodel.CourseViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class CourseDetailsActivity extends AppCompatActivity {
    TextInputEditText txtName, txtDescription;
    Spinner spinnerGrade, spinnerInstructor;

    CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        txtName = findViewById(R.id.txt_course_name);
        txtDescription = findViewById(R.id.txt_course_desc);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

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
        else if (desc.equals("")) {
            txtDescription.setError("Please enter course description");
            return;
        }

        Course course = new Course(0, 0, name, desc);
        courseViewModel.insertCourse(course);
        Toast.makeText(this, "Course added successfully!", Toast.LENGTH_LONG).show();
        finish();
    }

}