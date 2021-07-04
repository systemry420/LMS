package com.example.lms.view.activity.admin.assign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lms.R;

public class AdminAssignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign);
    }

    public void openActivity(View view) {
        switch (view.getId()) {
            case R.id.card_assign_instructor:
                startActivity(new Intent(this, AdminAssignInstructorActivity.class));
                break;

            case R.id.card_assign_student:
                startActivity(new Intent(this, AdminAssignStudentActivity.class));
                break;
        }
    }
}