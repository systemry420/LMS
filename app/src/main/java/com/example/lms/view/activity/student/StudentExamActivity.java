package com.example.lms.view.activity.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.model.Exam;
import com.example.lms.model.Question;
import com.example.lms.view.adapter.StudentQuestionsAdapter;
import com.example.lms.viewmodel.ExamViewModel;

import java.util.List;

public class StudentExamActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvTimer;
    private Exam currentExam;
    private StudentQuestionsAdapter studentQuestionsAdapter;
    private ExamViewModel examViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exam);

        tvTimer = findViewById(R.id.tv_timer);

        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        recyclerView = findViewById(R.id.student_exam_recyclerview);
        studentQuestionsAdapter = new StudentQuestionsAdapter();
        recyclerView.setAdapter(studentQuestionsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        currentExam = (Exam) intent.getSerializableExtra("exam");
        if (currentExam != null) {
            examViewModel.getQuestionsOfExam(currentExam.getExamID())
                .observe(this, new Observer<List<Question>>() {
                    @Override
                    public void onChanged(List<Question> questionList) {
                        studentQuestionsAdapter.submitList(questionList);
                        Log.i("Stud", "onChanged: " + questionList.size());
                    }
                });
            startTimer();
        }
    }

    private void startTimer() {
        Log.i("Exam", "startTimer: " + currentExam.toString());
        new CountDownTimer(currentExam.getDuration(), 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time Left " +
                        millisUntilFinished / 1000);
            }

            public void onFinish() {
                // submit automatically
            }
        }.start();
    }
}