package com.example.lms.view.activity.admin.student;

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
import com.example.lms.model.Student;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.StudentViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {
    TextInputEditText txtName, txtAddress, txtEmail, txtPassword, txtPhone;
    Spinner spinnerGrade;
    StudentViewModel studentViewModel;
    GradeViewModel gradeViewModel;
    List<SpinnerItem> gradesList;
    SpinnerItem selectedGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        txtName = findViewById(R.id.txt_student_name);
        txtAddress = findViewById(R.id.txt_student_address);
        txtEmail = findViewById(R.id.txt_student_email);
        txtPassword = findViewById(R.id.txt_student_password);
        txtPhone = findViewById(R.id.txt_student_phone);
        spinnerGrade = findViewById(R.id.spinner_student_grade);

        gradesList = new ArrayList<>();

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

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
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                saveInstructor();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveInstructor() {
        String name = txtName.getText().toString();
        String address = txtAddress.getText().toString();
        String phone = txtPhone.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        if (name.equals("")) {
            txtName.setError("Please enter student name");
            return;
        }
        if (address.equals("")) {
            txtAddress.setError("Please enter student address");
            return;
        }
        if (phone.equals("")) {
            txtPhone.setError("Please enter student phone");
            return;
        }
        if (email.equals("")) {
            txtEmail.setError("Please enter student email");
            return;
        }
        if (password.equals("")) {
            txtPassword.setError("Please enter student password");
            return;
        }
        if(spinnerGrade.getSelectedItemId() == 0) {
            Toast.makeText(this, "Please select a grade", Toast.LENGTH_LONG).show();
            return;
        }

        Student student = new Student(selectedGrade.getId(), name, address, email, password, phone);
        long id = studentViewModel.insertStudent(student);
        Log.i("fuck", "saveInstructor: " +id + " " + student.toString());
        Toast.makeText(this, "Student added successfully!", Toast.LENGTH_LONG).show();
        finish();
    }
}