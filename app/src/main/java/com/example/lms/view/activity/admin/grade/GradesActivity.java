package com.example.lms.view.activity.admin.grade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Grade;
import com.example.lms.view.adapter.GradesAdapter;
import com.example.lms.viewmodel.GradeViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class GradesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GradeViewModel gradeViewModel;
    TextInputEditText textGrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        textGrade = findViewById(R.id.txt_grade_name);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GradesAdapter adapter = new GradesAdapter();
        recyclerView.setAdapter(adapter);

        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);
        gradeViewModel.getAllGrades().observe(this, grades -> {
            adapter.submitList(grades);
        });
    }

    public void addNewGrade(View view) {
        if(textGrade.getText().toString().equals("")) {
            textGrade.setError("Grade name must be entered");
            return;
        }

        Grade grade = new Grade(textGrade.getText().toString());
        gradeViewModel.insertGrade(grade);
        Toast.makeText(this, "Grade added successfully!", Toast.LENGTH_LONG).show();
        textGrade.setText("");
    }
}