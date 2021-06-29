package com.example.lms.view.activity.admin.instructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;
import com.example.lms.view.adapter.InstructorAdapter;
import com.example.lms.viewmodel.InstructorViewModel;

public class InstructorActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    InstructorViewModel instructorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        recyclerView = findViewById(R.id.instructor_recycler_view);
        InstructorAdapter adapter = new InstructorAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        instructorViewModel.getAllInstructors().observe(this, instructors -> {
            adapter.submitList(instructors);
        });
    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, InstructorDetailsActivity.class));
    }
}