package com.example.lms.view.activity.admin.instructor;

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
import com.example.lms.util.SpinnerItem;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.InstructorViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class InstructorDetailsActivity extends AppCompatActivity {
    private static final String TAG = "InstructorDetailsActivi";
    private TextInputEditText txtName, txtAddress, txtEmail, txtPassword, txtPhone;
    private InstructorViewModel instructorViewModel;
    private GradeViewModel gradeViewModel;
    private List<SpinnerItem> gradesList;
    private SpinnerItem gradeID;
    private Instructor currentInstructor;
    private String name, address, email, password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_details);

        initVariables();

        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        checkIntent();
    }

    private void checkIntent() {
        Intent intent = getIntent();
        currentInstructor = (Instructor) intent.getSerializableExtra("instructor");
        if (currentInstructor != null) {
            txtName.setText(currentInstructor.getName());
            txtPhone.setText(currentInstructor.getPhone());
            txtEmail.setText(currentInstructor.getEmail());
            txtPassword.setText(currentInstructor.getPassword());
            txtAddress.setText(currentInstructor.getAddress());
        }
    }

    private void saveInstructor() {
        name = txtName.getText().toString();
        address = txtAddress.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        phone = txtPhone.getText().toString();

        if (validateInput()) {
            if (currentInstructor != null) {
                currentInstructor.setInstructorID(currentInstructor.getInstructorID());
                currentInstructor.setName(name);
                currentInstructor.setAddress(address);
                currentInstructor.setEmail(email);
                currentInstructor.setPassword(password);
                currentInstructor.setPhone(phone);
                instructorViewModel.updateInstructor(currentInstructor);
            }
            else {
                Instructor instructor = new Instructor(gradeID.getId(), name, address, phone, email, password);
                long id = instructorViewModel.insertInstructor(instructor);
                Log.i(TAG, "saveInstructor: " + id + " " + instructor.toString());
                Toast.makeText(this, "Instructor added successfully!", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }

    private void initVariables() {
        txtName = findViewById(R.id.txt_instructor_name);
        txtAddress = findViewById(R.id.txt_instructor_address);
        txtEmail = findViewById(R.id.txt_instructor_email);
        txtPassword = findViewById(R.id.txt_instructor_password);
        txtPhone = findViewById(R.id.txt_instructor_phone);
        gradesList = new ArrayList<>();
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

    private boolean validateInput() {
        if (txtName.getText().toString().equals("")) {
            txtName.setError("Please enter instructor name");
            return false;
        }
        if (txtAddress.getText().toString().equals("")) {
            txtAddress.setError("Please enter instructor address");
            return false;
        }
        if (txtEmail.getText().toString().equals("")) {
            txtEmail.setError("Please enter instructor email");
            return false;
        }
        if (txtPassword.getText().toString().equals("")) {
            txtPassword.setError("Please enter instructor password");
            return false;
        }
        if (txtPhone.getText().toString().equals("")) {
            txtPhone.setError("Please enter instructor phone");
            return false;
        }

        return true;
    }

}