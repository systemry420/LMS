package com.example.lms.view.activity.instructor.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.example.lms.view.adapter.LecturesAdapter;
import com.example.lms.viewmodel.LectureViewModel;

public class AddExamActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        recyclerView = findViewById(R.id.instructor_lectures_recycler_view);

        LecturesAdapter adapter = new LecturesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        lectureViewModel = new ViewModelProvider(this).get(LectureViewModel.class);
//        lectureViewModel.getAllLectures().observe(this, new Observer<List<Lecture>>() {
//            @Override
//            public void onChanged(List<Lecture> lectures) {
//                adapter.submitList(lectures);
//            }
//        });

    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, ExamDetailsActivity.class));
    }
}