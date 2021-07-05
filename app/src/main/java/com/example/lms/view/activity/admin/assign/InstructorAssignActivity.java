package com.example.lms.view.activity.admin.assign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.model.Student;
import com.example.lms.model.relations.InstructorGradeCrossRef;
import com.example.lms.model.relations.StudentCoursesCrossRef;
import com.example.lms.util.SpinnerItem;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.InstructorViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class InstructorAssignActivity extends AppCompatActivity {
    private static final String TAG = "InstructorAssignActivit";
    private Spinner spinnerGrade;
    private ChipGroup chipGroup;
    private GradeViewModel gradeViewModel;
    private Instructor currentInstructor;
    private List<SpinnerItem> gradesList;
    private SpinnerItem selectedGrade;
    private ArrayAdapter<SpinnerItem> gradeAdapter;
    private InstructorViewModel instructorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_assign);

        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        spinnerGrade = findViewById(R.id.spinner_assign_instructor_grade);
        chipGroup = new ChipGroup(this);

        Intent intent = getIntent();
        currentInstructor = (Instructor) intent.getSerializableExtra("instructor");
        setGradesSpinner();

        instructorViewModel.getGradesOfInstructor(currentInstructor.getInstructorID())
            .observe(this, new Observer<List<Grade>>() {
                @Override
                public void onChanged(List<Grade> gradesList) {
                    if(gradesList != null) {
                        for (Grade grade : gradesList) {
                            addChip(grade.getGradeName());
                        }
                    }
                }
            });
    }

    private void setGradesSpinner() {
        gradesList = new ArrayList<>();
        gradeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gradesList);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(gradeAdapter);

        gradeViewModel.getAllGrades().observe(this, new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                gradesList.add(new SpinnerItem((long) 0, "Please select a grade"));
                for (Grade grade : grades) {
                    gradesList.add(new SpinnerItem(grade.getGradeID(), grade.getGradeName()));
                }
                gradeAdapter.notifyDataSetChanged();
            }
        });

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGrade = (SpinnerItem) parent.getItemAtPosition(position);
                assignGrade(selectedGrade);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void assignGrade(SpinnerItem selectedGrade) {
        if(selectedGrade.getId() == 0) {
            return;
        }

        addChip(selectedGrade.getCaption());
        InstructorGradeCrossRef join = new InstructorGradeCrossRef(currentInstructor.getInstructorID(), selectedGrade.getId());
        instructorViewModel.insertInstructorGradeJoin(join);
    }


    private void addChip(String gradeName) {
        Chip chip = new Chip(this);
        chip.setChipBackgroundColorResource(R.color.error);
        chip.setTextColor(getResources().getColor(R.color.white));
        chip.setText(gradeName);
        chip.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
        chipGroup.addView(chip);
    }
}