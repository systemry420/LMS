package com.example.lms.view.activity.admin.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.example.lms.view.adapter.StudentsAdapter;
import com.example.lms.viewmodel.StudentViewModel;

public class StudentsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        recyclerView = findViewById(R.id.students_recycler_view);
        StudentsAdapter adapter = new StudentsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe(this, students -> {
            adapter.submitList(students);
        });

    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, StudentDetailsActivity.class));
    }
}