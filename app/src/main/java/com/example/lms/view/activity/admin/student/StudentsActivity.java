package com.example.lms.view.activity.admin.student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Student;
import com.example.lms.view.activity.admin.instructor.InstructorActivity;
import com.example.lms.view.activity.admin.instructor.InstructorDetailsActivity;
import com.example.lms.view.adapter.StudentsAdapter;
import com.example.lms.viewmodel.StudentViewModel;

public class StudentsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentViewModel studentViewModel;
    private StudentsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        recyclerView = findViewById(R.id.students_recycler_view);
        adapter = new StudentsAdapter();

        setAdapterListeners();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe(this, students -> {
            adapter.submitList(students);
        });

    }

    private void setAdapterListeners() {
        adapter.setOnStudentClickListener(new StudentsAdapter.OnStudentClickListener() {
            @Override
            public void onClickStudent(Student student) {
                Intent intent = new Intent(StudentsActivity.this,
                        StudentDetailsActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });

        adapter.setOnStudentDeleteListener(new StudentsAdapter.OnDeleteStudentClickListener() {
            @Override
            public void onDeleteStudent(Student student) {
                deleteCurrentStudent(student);
            }
        });
    }

    private void deleteCurrentStudent(Student student) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog
            .setTitle("Delete student")
            .setMessage("Are you sure you want to delete this student?")
            .setCancelable(false)
            .setPositiveButton("Yes", (dialog, which) -> {
                studentViewModel.deleteStudent(student);
                Toast.makeText(StudentsActivity.this, "Student deleted", Toast.LENGTH_LONG).show();
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            }).show();
    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, StudentDetailsActivity.class));
    }
}