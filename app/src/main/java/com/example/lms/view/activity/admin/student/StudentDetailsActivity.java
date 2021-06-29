package com.example.lms.view.activity.admin.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Student;
import com.example.lms.viewmodel.StudentViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class StudentDetailsActivity extends AppCompatActivity {
    TextInputEditText txtName, txtAddress, txtEmail, txtPassword, txtPhone;
    Spinner spinnerGrade;
    StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        txtName = findViewById(R.id.txt_student_name);
        txtAddress = findViewById(R.id.txt_student_address);
        txtEmail = findViewById(R.id.txt_student_email);
        txtPassword = findViewById(R.id.txt_student_password);
        txtPhone = findViewById(R.id.txt_student_phone);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

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

        Student student = new Student(0, name, address, email, password, phone);
        studentViewModel.insertStudent(student);
        Toast.makeText(this, "Student added successfully!", Toast.LENGTH_LONG).show();
        finish();
    }
}