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
import com.example.lms.viewmodel.ExamViewModel;
import com.example.lms.viewmodel.LectureViewModel;

public class AddExamActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExamViewModel examViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        recyclerView = findViewById(R.id.instructor_exam_recycler_view);

        LecturesAdapter adapter = new LecturesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        examViewModel.getExamsOfCourse()
    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, ExamDetailsActivity.class));
    }
}