package com.example.lms.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.AdminRepo;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.model.relations.InstructorGradeCrossRef;

import java.util.List;

public class InstructorViewModel extends AndroidViewModel {

    private final AdminRepo adminRepo;
    private final LiveData<List<Instructor>> allInstructors;

    public InstructorViewModel(Application application) {
        super(application);
        adminRepo = new AdminRepo(application);
        allInstructors = adminRepo.getAllInstructors();
    }

    public LiveData<List<Grade>> getGradesOfInstructor(long instructorID) {
        return adminRepo.getGradesOfInstructor(instructorID);
    }

    public void insertInstructorGradeJoin(InstructorGradeCrossRef join) {
        adminRepo.insertInstructorGradeJoin(join);
    }

    public LiveData<List<Instructor>> getAllInstructors() {
        return allInstructors;
    }

    public long insertInstructor(Instructor instructor) {
        return adminRepo.insertInstructor(instructor);
    }

    public void deleteInstructor(Instructor instructor) {
        adminRepo.deleteInstructor(instructor);
    }

    public void updateInstructor(Instructor instructor) {
        adminRepo.updateInstructor(instructor);
    }

}
