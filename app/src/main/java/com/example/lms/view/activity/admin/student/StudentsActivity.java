package com.example.lms.view.activity.admin.student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Grade;
import com.example.lms.model.Student;
import com.example.lms.util.SpinnerItem;
import com.example.lms.view.activity.admin.instructor.InstructorActivity;
import com.example.lms.view.activity.admin.instructor.InstructorDetailsActivity;
import com.example.lms.view.adapter.StudentsAdapter;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentViewModel studentViewModel;
    private GradeViewModel gradeViewModel;
    private StudentsAdapter adapter;
    private Spinner spinnerGrade;
    private SpinnerItem selectedGrade;
    private List<SpinnerItem> gradesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        spinnerGrade = findViewById(R.id.spinner_student_grade);
        gradesList = new ArrayList<>();

        recyclerView = findViewById(R.id.students_recycler_view);
        adapter = new StudentsAdapter();

        setAdapterListeners();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


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

    private void populateGradeSpinner() {
        ArrayAdapter<SpinnerItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gradesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(arrayAdapter);
        gradeViewModel.getAllGrades().observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                gradesList.add(new SpinnerItem((long) 0, "Please select a grade"));
                for (Grade grade : grades) {
                    gradesList.add(new SpinnerItem(grade.getGradeID(), grade.getGradeName()));
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGrade = (SpinnerItem) parent.getItemAtPosition(position);
                fetchStudents(selectedGrade.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchStudents(Long id) {
        if(id == 0) {
            studentViewModel.getAllStudents().observe(this, students -> {
                adapter.submitList(students);
            });
        }
        else {
            studentViewModel.getStudentsOfGrade(id).observe(this, students -> {
                adapter.submitList(students);
            });
        }
    }

}