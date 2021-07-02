package com.example.lms.view.activity.admin.grade;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Grade;
import com.example.lms.view.adapter.GradesAdapter;
import com.example.lms.viewmodel.GradeViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class GradesActivity extends AppCompatActivity {
    private static final String TAG = "GradesActivity";
    private RecyclerView recyclerView;
    private GradeViewModel gradeViewModel;
    private TextInputEditText textGrade;
    private GradesAdapter adapter;
    private AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        textGrade = findViewById(R.id.txt_grade_name);
        alertDialog = new AlertDialog.Builder(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GradesAdapter();
        setAdapterListeners();
        recyclerView.setAdapter(adapter);

        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);
        gradeViewModel.getAllGrades().observe(this, grades -> {
            adapter.submitList(grades);
        });
    }

    private void setAdapterListeners() {
        adapter.setOnItemClickListener(grade -> Toast.makeText(GradesActivity.this, "details activity", Toast.LENGTH_LONG).show());

        adapter.setOnDeleteListener(grade -> {
            deleteGrade(grade);
        });
    }

    private void addNewGrade(View view) {
        if(textGrade.getText().toString().equals("")) {
            textGrade.setError("Please enter a name for grade");
            return;
        }

        Grade grade = new Grade(textGrade.getText().toString());
        gradeViewModel.insertGrade(grade);
        Toast.makeText(this, "Grade added successfully!", Toast.LENGTH_LONG).show();
        textGrade.setText("");
    }

    private void deleteGrade(Grade grade) {
        alertDialog
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete the grade")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    gradeViewModel.deleteGrade(grade);
                    Toast.makeText(GradesActivity.this, "Grade deleted", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).show();
    }
}