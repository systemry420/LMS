package com.example.lms.view.activity.admin.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.example.lms.model.Grade;
import com.example.lms.util.SpinnerItem;
import com.example.lms.model.Student;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.StudentViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {
    private TextInputEditText txtName, txtAddress, txtEmail, txtPassword, txtPhone;
    private StudentViewModel studentViewModel;
    private GradeViewModel gradeViewModel;
    private List<SpinnerItem> gradesList;
    private Student currentStudent;
    private String name, address, email, password, phone;
    private SpinnerItem selectedGrade;
    private Spinner spinnerGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        txtName = findViewById(R.id.txt_student_name);
        txtAddress = findViewById(R.id.txt_student_address);
        txtEmail = findViewById(R.id.txt_student_email);
        txtPassword = findViewById(R.id.txt_student_password);
        txtPhone = findViewById(R.id.txt_student_phone);

        gradesList = new ArrayList<>();

        spinnerGrade = findViewById(R.id.spinner_student_grade);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        setGradesSpinner();

        checkIntent();


    }

    private void checkIntent() {
        Intent intent = getIntent();
        currentStudent = (Student) intent.getSerializableExtra("student");
        if (currentStudent != null) {
            txtName.setText(currentStudent.getName());
            txtPhone.setText(currentStudent.getPhone());
            txtEmail.setText(currentStudent.getEmail());
            txtPassword.setText(currentStudent.getPassword());
            txtAddress.setText(currentStudent.getAddress());
        }
    }

    private void saveStudent() {
        name = txtName.getText().toString();
        address = txtAddress.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        phone = txtPhone.getText().toString();

        if (validateInput()) {
            if (currentStudent != null) {
                currentStudent.setStudentID(currentStudent.getStudentID());
                currentStudent.setName(name);
                currentStudent.setAddress(address);
                currentStudent.setEmail(email);
                currentStudent.setPassword(password);
                currentStudent.setPhone(phone);
                studentViewModel.updateStudent(currentStudent);
            }
            else {
                Student student = new Student(selectedGrade.getId(), name, address, email, password, phone);
                long id = studentViewModel.insertStudent(student);
                Log.i("student", "save: " +id + " " + student.toString());
                Toast.makeText(this, "Student added successfully!", Toast.LENGTH_LONG).show();
            }
            finish();
        }

    }

    private void setGradesSpinner() {
        ArrayAdapter<SpinnerItem> gradesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, gradesList);
        gradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(gradesAdapter);

        gradeViewModel.getAllGrades().observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                gradesList.add(new SpinnerItem((long) 0, "Please select a grade"));
                for (Grade grade : grades) {
                    gradesList.add(new SpinnerItem(grade.getGradeID(), grade.getGradeName()));
                }
                gradesAdapter.notifyDataSetChanged();
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

    private boolean validateInput() {
        if (txtName.getText().toString().equals("")) {
            txtName.setError("Please enter student name");
            return false;
        }
        if (txtAddress.getText().toString().equals("")) {
            txtAddress.setError("Please enter student address");
            return false;
        }
        if (txtEmail.getText().toString().equals("")) {
            txtEmail.setError("Please enter student email");
            return false;
        }
        if (txtPassword.getText().toString().equals("")) {
            txtPassword.setError("Please enter student password");
            return false;
        }
        if (txtPhone.getText().toString().equals("")) {
            txtPhone.setError("Please enter student phone");
            return false;
        }

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveStudent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}