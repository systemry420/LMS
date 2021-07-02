package com.example.lms.view.activity.admin.instructor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.Instructor;
import com.example.lms.view.activity.admin.grade.GradesActivity;
import com.example.lms.view.adapter.InstructorAdapter;
import com.example.lms.viewmodel.InstructorViewModel;

import java.io.Serializable;

public class InstructorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private InstructorViewModel instructorViewModel;
    private InstructorAdapter adapter;
    private AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        alertDialog = new AlertDialog.Builder(this);

        recyclerView = findViewById(R.id.instructor_recycler_view);
        adapter = new InstructorAdapter();
        setAdapterListeners();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        instructorViewModel.getAllInstructors().observe(this, instructors -> {
            adapter.submitList(instructors);
        });
    }

    private void setAdapterListeners() {
        adapter.setOnInstructorClickListener(new InstructorAdapter.OnInstructorClickListener() {
            @Override
            public void onClickItem(Instructor instructor) {
                Intent intent = new Intent(InstructorActivity.this, InstructorDetailsActivity.class);
                intent.putExtra("instructor", instructor);
                startActivity(intent);
            }
        });

        adapter.setOnInstructorDeleteListener(new InstructorAdapter.OnDeleteInstructorClickListener() {
            @Override
            public void onDeleteItem(Instructor instructor) {
                deleteCurrentInstructor(instructor);
            }
        });
    }

    private void deleteCurrentInstructor(Instructor instructor) {
        alertDialog
            .setTitle("Delete")
            .setMessage("Are you sure you want to delete this instructor?")
            .setCancelable(false)
            .setPositiveButton("Yes", (dialog, which) -> {
                instructorViewModel.deleteInstructor(instructor);
                Toast.makeText(InstructorActivity.this, "Instructor deleted", Toast.LENGTH_LONG).show();
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            }).show();
    }

    public void openDetailsActivity(View view) {
        startActivity(new Intent(this, InstructorDetailsActivity.class));
    }
}