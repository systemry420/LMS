package com.example.lms.view.activity.instructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.model.Instructor;
import com.example.lms.view.activity.admin.AdminLoginActivity;
import com.example.lms.viewmodel.InstructorViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class InstructorLoginActivity extends AppCompatActivity {
    private TextInputEditText txtEmail, txtPassword;
    private InstructorViewModel instructorViewModel;
    private TextView textViewError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_login);

        txtEmail = findViewById(R.id.txt_instructor_login_email);
        txtPassword = findViewById(R.id.txt_instructor_login_password);
        textViewError = findViewById(R.id.admin_login_textview_error);

        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);


    }

    public void loginInstructor(View view) {
        if (txtEmail.getText().toString().equals("")) {
            txtEmail.setError("Please enter a valid email");
            return;
        }
        if (txtPassword.getText().toString().equals("")) {
            txtPassword.setError("Please enter a valid password");
            return;
        }

        instructorViewModel.getAllInstructors().observe(this, new Observer<List<Instructor>>() {
            @Override
            public void onChanged(List<Instructor> instructors) {
                for(Instructor instructor : instructors) {
                    String email = instructor.getEmail();
                    String password = instructor.getPassword();

                    if (email.equals(txtEmail.getText().toString())
                        && password.equals(txtPassword.getText().toString())) {
                            startActivity(new Intent(InstructorLoginActivity.this,
                                    InstructorHomeActivity.class));
                        break;
                    }
                    else {
                        textViewError.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void openAdminLogin(View view) {
        startActivity(new Intent(this, AdminLoginActivity.class));
    }
}