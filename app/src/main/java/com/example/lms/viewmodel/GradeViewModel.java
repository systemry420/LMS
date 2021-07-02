package com.example.lms.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.AdminRepo;
import com.example.lms.model.Grade;

import java.util.List;

public class GradeViewModel extends AndroidViewModel {
    private final AdminRepo adminRepo;
    private final LiveData<List<Grade>> allGrades;

    public GradeViewModel(Application application) {
        super(application);
        adminRepo = new AdminRepo(application);
        allGrades = adminRepo.getAllGrades();
    }

    public LiveData<List<Grade>> getAllGrades() {
        return allGrades;
    }

    public long insertGrade(Grade grade) {
        return adminRepo.insertGrade(grade);
    }

    public void deleteGrade(Grade grade) {
        adminRepo.deleteGrade(grade);
    }
}
