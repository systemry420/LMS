package com.example.lms.view.activity.admin.assign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lms.R;
import com.example.lms.model.Instructor;
import com.example.lms.view.adapter.InstructorAdapter2;
import com.example.lms.viewmodel.InstructorViewModel;

import java.util.List;

public class AdminAssignInstructorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private InstructorViewModel instructorViewModel;
    private InstructorAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_instructor);

        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        recyclerView = findViewById(R.id.assign_instructor_recycler_view);
        adapter = new InstructorAdapter2();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        instructorViewModel.getAllInstructors().observe(this, new Observer<List<Instructor>>() {
            @Override
            public void onChanged(List<Instructor> instructors) {
                adapter.submitList(instructors);
            }
        });

        adapter.setOnInstructorClickListener(new InstructorAdapter2.OnInstructorClickListener() {
            @Override
            public void onClickItem(Instructor instructor) {
                Intent intent = new Intent(AdminAssignInstructorActivity.this,
                        InstructorAssignActivity.class);
                intent.putExtra("instructor", instructor);
                startActivity(intent);
            }
        });
    }
}