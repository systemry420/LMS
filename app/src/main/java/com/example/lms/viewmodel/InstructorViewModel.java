package com.example.lms.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.AdminRepo;
import com.example.lms.model.Instructor;

import java.util.List;

public class InstructorViewModel extends AndroidViewModel {

    private final AdminRepo adminRepo;
    private final LiveData<List<Instructor>> allInstructors;

    public InstructorViewModel(Application application) {
        super(application);
        adminRepo = new AdminRepo(application);
        allInstructors = adminRepo.getAllInstructors();
    }

    public LiveData<List<Instructor>> getAllInstructors() {
        return allInstructors;
    }

    public void insertInstructor(Instructor instructor) {
        adminRepo.insertInstructor(instructor);
    }

}
