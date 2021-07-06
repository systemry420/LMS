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
import com.example.lms.model.Exam;
import com.example.lms.model.Lecture;
import com.example.lms.model.Student;
import com.example.lms.view.adapter.StudentHomeCoursesAdapter;
import com.example.lms.view.adapter.StudentHomeExamAdapter;
import com.example.lms.view.adapter.StudentLecturesAdapter;
import com.example.lms.viewmodel.ExamViewModel;
import com.example.lms.viewmodel.LectureViewModel;
import com.example.lms.viewmodel.StudentViewModel;

import java.util.List;

public class StudentCourseLecturesActivity extends AppCompatActivity {
    private RecyclerView recyclerView, examsRecyclerView;
    private Course currentCourse;
    private LectureViewModel lectureViewModel;
    private StudentLecturesAdapter adapter;
    private ExamViewModel examViewModel;
    private StudentHomeExamAdapter examAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_lectures);

        lectureViewModel = new ViewModelProvider(this).get(LectureViewModel.class);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        examsRecyclerView = findViewById(R.id.student_course_exams_recyclerview);
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

        examAdapter = new StudentHomeExamAdapter();
        examsRecyclerView.setAdapter(examAdapter);
        examsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        examAdapter.setOnExamClickListener(new StudentHomeExamAdapter.OnExamClickListener() {
            @Override
            public void onClickExam(Exam exam) {
                Intent intent = new Intent(StudentCourseLecturesActivity.this,
                        StudentExamActivity.class);
                intent.putExtra("exam", exam);
                startActivity(intent);
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

            examViewModel.getExamsOfCourse(currentCourse.getCourseID())
                .observe(this, new Observer<List<Exam>>() {
                    @Override
                    public void onChanged(List<Exam> exams) {
                        examAdapter.submitList(exams);
                    }
                });
        }
    }

}