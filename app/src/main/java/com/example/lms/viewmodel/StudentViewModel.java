package com.example.lms.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.AdminRepo;
import com.example.lms.model.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private final AdminRepo adminRepo;
    private final LiveData<List<Student>> allStudents;

    public StudentViewModel(Application application) {
        super(application);
        adminRepo = new AdminRepo(application);
        allStudents = adminRepo.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public void insertStudent(Student Student) {
        adminRepo.insertStudent(Student);
    }


}
