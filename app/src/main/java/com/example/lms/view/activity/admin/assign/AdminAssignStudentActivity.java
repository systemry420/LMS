package com.example.lms.view.activity.admin.assign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lms.R;
import com.example.lms.model.Grade;
import com.example.lms.model.Student;
import com.example.lms.util.SpinnerItem;
import com.example.lms.view.activity.admin.student.StudentDetailsActivity;
import com.example.lms.view.activity.admin.student.StudentsActivity;
import com.example.lms.view.adapter.StudentAdapter2;
import com.example.lms.view.adapter.StudentsAdapter;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdminAssignStudentActivity extends AppCompatActivity {
    private Spinner spinnerGrade;
    private GradeViewModel gradeViewModel;
    private RecyclerView recyclerView;
    private List<SpinnerItem> gradesList;
    private SpinnerItem selectedGrade;
    private StudentViewModel studentViewModel;

    private StudentAdapter2 studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_student);

        spinnerGrade = findViewById(R.id.spinner_admin_assign_student);

        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);


        gradesList = new ArrayList<>();

        studentsAdapter = new StudentAdapter2();
        recyclerView = findViewById(R.id.assign_student_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentsAdapter);

        populateGradeSpinner();

        setAdapterListeners();

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

    private void fetchStudents(long gradeID) {
        if (gradeID == 0) {
            studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
                @Override
                public void onChanged(List<Student> students) {
                    studentsAdapter.submitList(students);
                }
            });
        }
        else  {
            studentViewModel.getStudentsOfGrade(gradeID).observe(this, new Observer<List<Student>>() {
                @Override
                public void onChanged(List<Student> students) {
                    studentsAdapter.submitList(students);
                }
            });
        }
    }

    private void setAdapterListeners() {
        studentsAdapter.setOnStudentClickListener(new StudentAdapter2.OnStudent2ClickListener() {
            @Override
            public void onClickStudent(Student student) {
                Intent intent = new Intent(AdminAssignStudentActivity.this,
                        StudentAssignActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });
    }

}