package com.example.lms.view.activity.instructor.lecture;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.model.Lecture;
import com.example.lms.util.SpinnerItem;
import com.example.lms.view.activity.admin.instructor.InstructorActivity;
import com.example.lms.view.activity.admin.instructor.InstructorDetailsActivity;
import com.example.lms.view.activity.admin.student.StudentsActivity;
import com.example.lms.view.adapter.CoursesAdapter;
import com.example.lms.view.adapter.LecturesAdapter;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.GradeViewModel;
import com.example.lms.viewmodel.LectureViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddLectureActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LectureViewModel lectureViewModel;
    private Spinner spinnerGrade, spinnerCourse;
    private GradeViewModel gradeViewModel;
    private CourseViewModel courseViewModel;
    private SpinnerItem selectedGrade, selectedCourse;
    private LecturesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);



        spinnerGrade = findViewById(R.id.spinner_grade_lecture);
        spinnerCourse = findViewById(R.id.spinner_course_lecture);

        recyclerView = findViewById(R.id.instructor_lectures_recycler_view);

        lectureViewModel = new ViewModelProvider(this).get(LectureViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        adapter = new LecturesAdapter();
        setAdaptersListeners();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        populateGradeSpinner();

    }

    private void setAdaptersListeners() {
        adapter.setOnLectureClickListener(new LecturesAdapter.OnLectureClickListener() {
            @Override
            public void onClickLecture(Lecture lecture) {
                Intent intent = new Intent(AddLectureActivity.this,
                        LectureDetailsActivity.class);
                intent.putExtra("lecture", lecture);
                startActivity(intent);
            }
        });

        adapter.setOnDeleteLectureListener(new LecturesAdapter.OnDeleteLectureClickListener() {
            @Override
            public void onDeleteLecture(Lecture lecture) {
                deleteCurrentLecture(lecture);
            }
        });
    }

    private void deleteCurrentLecture(Lecture lecture) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog
            .setTitle("Delete lecture")
            .setMessage("Are you sure you want to delete this lecture?")
            .setCancelable(false)
            .setPositiveButton("Yes", (dialog, which) -> {
                lectureViewModel.deleteLecture(lecture);
                Toast.makeText(AddLectureActivity.this, "Lecture deleted", Toast.LENGTH_LONG).show();
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            }).show();
    }

    private void populateGradeSpinner() {
        final List<SpinnerItem> gradesList = new ArrayList<>();
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
                populateCourseSpinner(selectedGrade.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void populateCourseSpinner(long gradeID) {
        List<SpinnerItem> coursesList = new ArrayList<>();

        ArrayAdapter<SpinnerItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(arrayAdapter);
        courseViewModel.getCoursesOfGrade(gradeID).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for (Course course : courses) {
                    coursesList.add(new SpinnerItem(course.getCourseID(), course.getName() ));
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (SpinnerItem) parent.getItemAtPosition(position);

                fetchLectures(selectedCourse);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchLectures(SpinnerItem selectedCourse) {
        if (selectedCourse.getId() == 0) {
            lectureViewModel.getAllLectures().observe(this, new Observer<List<Lecture>>() {
                @Override
                public void onChanged(List<Lecture> lectures) {
                    adapter.submitList(lectures);
                }
            });
        }
        else {
            lectureViewModel.getLecturesOfCourse(selectedCourse.getId()).observe(this, new Observer<List<Lecture>>() {
                @Override
                public void onChanged(List<Lecture> lectures) {
                    adapter.submitList(lectures);
                }
            });
        }
    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, LectureDetailsActivity.class));
    }
}