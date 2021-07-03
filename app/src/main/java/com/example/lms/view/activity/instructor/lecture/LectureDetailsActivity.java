package com.example.lms.view.activity.instructor.lecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Student;
import com.example.lms.util.DateConverter;
import com.example.lms.util.SpinnerItem;
import com.example.lms.model.Course;
import com.example.lms.model.Lecture;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.LectureViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class LectureDetailsActivity extends AppCompatActivity {
    private static final String TAG = "LectureDetailsActivity";
    private TextInputEditText txtLectureTitle, txtLectureLink;
    private LectureViewModel lectureViewModel;
    private CourseViewModel courseViewModel;
    private Spinner spinnerCourse;
    private List<SpinnerItem> coursesList;
    private SpinnerItem selectedCourse;
    private Lecture currentLecture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_details);

        txtLectureTitle = findViewById(R.id.txt_lecture_title);
        txtLectureLink = findViewById(R.id.txt_lecture_link);
        spinnerCourse = findViewById(R.id.spinner_course_add_lecture);

        coursesList = new ArrayList<>();

        lectureViewModel = new ViewModelProvider(this).get(LectureViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        setCourseSpinner();

        checkIntent();

    }



    private void checkIntent() {
        Intent intent = getIntent();
        currentLecture = (Lecture) intent.getSerializableExtra("lecture");
        if (currentLecture != null) {
            txtLectureTitle.setText(currentLecture.getLectureTitle());
            txtLectureLink.setText(currentLecture.getLectureLink());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveLecture();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveLecture() {
        String title = txtLectureTitle.getText().toString();
        String link = txtLectureLink.getText().toString();

        if(title.equals("")) {
            txtLectureTitle.setError("Please enter lecture title");
            return;
        }
        if(link.equals("")) {
            txtLectureTitle.setError("Please enter lecture link");
            return;
        }

        if(currentLecture != null) {
            currentLecture.setLectureID(currentLecture.getLectureID());
            currentLecture.setCourseID(currentLecture.getCourseID());
            currentLecture.setLectureLink(currentLecture.getLectureLink());
            currentLecture.setLectureDate(currentLecture.getLectureDate());
        }
        else {
            String date = DateConverter.getCurrentDate();
            Lecture lecture = new Lecture(selectedCourse.getId(), title, link, date);
            long id = lectureViewModel.insertLecture(lecture);
            Toast.makeText(this, "Lecture added successfully!", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    private void setCourseSpinner() {
        ArrayAdapter<SpinnerItem> coursesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, coursesList);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(coursesAdapter);

        courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for(Course course: courses) {
                    coursesList.add(new SpinnerItem(course.getCourseID(), course.getName()));
                }
                coursesAdapter.notifyDataSetChanged();
            }
        });

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (SpinnerItem) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}