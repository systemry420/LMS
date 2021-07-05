package com.example.lms.view.activity.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.example.lms.R;
import com.example.lms.model.Course;
import com.example.lms.model.Lecture;
import com.example.lms.model.Student;
import com.example.lms.view.adapter.StudentLecturesAdapter;
import com.example.lms.viewmodel.LectureViewModel;

import java.util.List;

public class StudentCourseLecturesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Course currentCourse;
    private LectureViewModel lectureViewModel;
    private StudentLecturesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_lectures);

        lectureViewModel = new ViewModelProvider(this).get(LectureViewModel.class);



        recyclerView = findViewById(R.id.student_course_lectures_recyclerview);
        adapter = new StudentLecturesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        checkIntent();

        adapter.setOnLectureClickListener(new StudentLecturesAdapter.OnLectureClickListener() {
            @Override
            public void onClickLecture(Lecture lecture) {
                openExternalLink(lecture);
            }
        });
    }

    private void openExternalLink(Lecture lecture) {
        String lectureLink = lecture.getLectureLink();

        Uri webpage = Uri.parse(lectureLink);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void checkIntent() {
        Intent intent = getIntent();
        currentCourse = (Course) intent.getSerializableExtra("course");
        if (currentCourse != null) {
            lectureViewModel.getLecturesOfCourse(currentCourse.getCourseID())
            .observe(this, new Observer<List<Lecture>>() {
                @Override
                public void onChanged(List<Lecture> lectures) {
                    adapter.submitList(lectures);
                }
            });
        }
    }

}