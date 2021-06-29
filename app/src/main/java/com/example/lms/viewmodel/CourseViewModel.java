package com.example.lms.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.AdminRepo;
import com.example.lms.model.Course;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private final AdminRepo adminRepo;
    private final LiveData<List<Course>> allCourses;

    public CourseViewModel(Application application) {
        super(application);
        adminRepo = new AdminRepo(application);
        allCourses = adminRepo.getAllICourses();
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insertCourse(Course course) {
        adminRepo.insertCourse(course);
    }

}