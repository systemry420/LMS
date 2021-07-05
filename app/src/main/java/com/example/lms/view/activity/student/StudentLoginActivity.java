package com.example.lms.view.activity.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.model.Instructor;
import com.example.lms.model.Student;
import com.example.lms.view.activity.instructor.InstructorHomeActivity;
import com.example.lms.view.activity.instructor.InstructorLoginActivity;
import com.example.lms.viewmodel.StudentViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StudentLoginActivity extends AppCompatActivity {
    private TextInputEditText txtEmail, txtPassword;
    private StudentViewModel studentViewModel;
    private TextView textViewError;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        txtEmail = findViewById(R.id.txt_student_login_email);
        txtPassword = findViewById(R.id.txt_student_login_password);
        textViewError = findViewById(R.id.student_login_textview_error);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        sharedPreferences = getSharedPreferences("user_student", MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
    }

    public void loginStudent(View view) {

        if (txtEmail.getText().toString().equals("")) {
            txtEmail.setError("Please enter a valid email");
            return;
        }
        if (txtPassword.getText().toString().equals("")) {
            txtPassword.setError("Please enter a valid password");
            return;
        }

        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                for(Student student : students) {
                    String email = student.getEmail();
                    String password = student.getPassword();

                    if (email.equals(txtEmail.getText().toString())
                            && password.equals(txtPassword.getText().toString())) {
                        sharedEditor.putLong("studentID", student.getStudentID()).commit();
                        startActivity(new Intent(StudentLoginActivity.this,
                                StudentHomeActivity.class));
                        break;
                    }
                    else {
                        textViewError.setVisibility(View.VISIBLE);
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                textViewError.setVisibility(View.INVISIBLE);
                            }
                        }, 2000);
                    }
                }
            }
        });
    }
}