package com.example.lms.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.AdminRepo;
import com.example.lms.database.repository.InstructorRepo;
import com.example.lms.model.Instructor;
import com.example.lms.model.Lecture;

import java.util.List;

public class LectureViewModel extends AndroidViewModel {
    private final InstructorRepo instructorRepo;
    private final LiveData<List<Lecture>> allLectures;

    public LectureViewModel(Application application) {
        super(application);
        instructorRepo = new InstructorRepo(application);
        allLectures = instructorRepo.getAllLectures();
    }

    public LiveData<List<Lecture>> getAllLectures() {
        return allLectures;
    }

    public long insertLecture(Lecture lecture) {
        return instructorRepo.insertLecture(lecture);
    }

}
