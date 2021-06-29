package com.example.lms.view.activity.admin.instructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Instructor;
import com.example.lms.viewmodel.InstructorViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class InstructorDetailsActivity extends AppCompatActivity {
    TextInputEditText txtName, txtAddress, txtEmail, txtPassword, txtPhone;
    InstructorViewModel instructorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_details);

        txtName = findViewById(R.id.txt_instructor_name);
        txtAddress = findViewById(R.id.txt_instructor_address);
        txtEmail = findViewById(R.id.txt_instructor_email);
        txtPassword = findViewById(R.id.txt_instructor_password);
        txtPhone = findViewById(R.id.txt_instructor_phone);

        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
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
        else if (address.equals("")) {
            txtAddress.setError("Please enter instructor address");
            return;
        }
        else if (email.equals("")) {
            txtEmail.setError("Please enter instructor email");
            return;
        }
        else if (password.equals("")) {
            txtPassword.setError("Please enter instructor password");
            return;
        }
        else if (phone.equals("")) {
            txtPhone.setError("Please enter instructor phone");
            return;
        }

        Instructor instructor = new Instructor(name, address, phone, email, password);
        instructorViewModel.insertInstructor(instructor);
        Toast.makeText(this, "Instructor added successfully!", Toast.LENGTH_LONG).show();
        finish();
    }
}