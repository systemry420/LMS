package com.example.lms.view.activity.admin.instructor;

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
import com.example.lms.SpinnerItem;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.InstructorViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorDetailsActivity extends AppCompatActivity {
    private static final String TAG = "InstructorDetailsActivi";
    TextInputEditText txtName, txtAddress, txtEmail, txtPassword, txtPhone;
    InstructorViewModel instructorViewModel;
    GradeViewModel gradeViewModel;
    Spinner spinnerGrade;
    List<SpinnerItem> gradesList;
    SpinnerItem gradeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_details);

        txtName = findViewById(R.id.txt_instructor_name);
        txtAddress = findViewById(R.id.txt_instructor_address);
        txtEmail = findViewById(R.id.txt_instructor_email);
        txtPassword = findViewById(R.id.txt_instructor_password);
        txtPhone = findViewById(R.id.txt_instructor_phone);
        spinnerGrade = findViewById(R.id.spinner_instructor_grade);
        gradesList = new ArrayList<>();

        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);
        ArrayAdapter<SpinnerItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gradesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(arrayAdapter);

        gradeViewModel.getAllGrades().observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                gradesList.add(new SpinnerItem((long) 0, "Please select a grade"));
                for (Grade grade : grades) {
                    gradesList.add(new SpinnerItem(grade.getGradeID(), grade.getGradeName()));
                    Log.i(TAG, "onChanged: " + new SpinnerItem(grade.getGradeID(), grade.getGradeName()).toString());
                }
                arrayAdapter.notifyDataSetChanged();
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
        String address = txtAddress.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String phone = txtPhone.getText().toString();
        if (name.equals("")) {
            txtName.setError("Please enter instructor name");
            return;
        }
        if (address.equals("")) {
            txtAddress.setError("Please enter instructor address");
            return;
        }
        if (email.equals("")) {
            txtEmail.setError("Please enter instructor email");
            return;
        }
        if (password.equals("")) {
            txtPassword.setError("Please enter instructor password");
            return;
        }
        if (phone.equals("")) {
            txtPhone.setError("Please enter instructor phone");
            return;
        }
        if (spinnerGrade.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a grade", Toast.LENGTH_LONG).show();
            return;
        }

        Instructor instructor = new Instructor(gradeID.getId(), name, address, phone, email, password);
        long id = instructorViewModel.insertInstructor(instructor);
        Log.i(TAG, "saveInstructor: " + id + " " + instructor.toString());
        Toast.makeText(this, "Instructor added successfully!", Toast.LENGTH_LONG).show();
        finish();
    }
}