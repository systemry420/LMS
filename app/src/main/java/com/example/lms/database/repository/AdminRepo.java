package com.example.lms.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lms.database.AppDatabase;
import com.example.lms.database.dao.GradeDao;
import com.example.lms.model.Grade;

import java.util.List;

public class AdminRepo {
    private static final String TAG = "AdminRepo";
    private final GradeDao gradeDao;
    private final LiveData<List<Grade>> allGrades;

    public AdminRepo(Application application) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        gradeDao = db.gradeDao();
        allGrades = gradeDao.getAllGrades();
    }

    public LiveData<List<Grade>> getAllGrades() {
        return allGrades;
    }

    public void insertGrade(Grade grade) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gradeDao.insertGrade(grade);
        });
    }


}
