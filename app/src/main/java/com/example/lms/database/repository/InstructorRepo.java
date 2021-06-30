package com.example.lms.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lms.database.AppDatabase;
import com.example.lms.database.dao.LectureDao;
import com.example.lms.model.Lecture;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InstructorRepo {
    private final LectureDao lectureDao;
    private final LiveData<List<Lecture>> allLectures;

    public InstructorRepo(Application application) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        lectureDao = db.lectureDao();


        allLectures = lectureDao.getAllLectures();
    }

    public LiveData<List<Lecture>> getAllLectures() {
        return allLectures;
    }

    public long insertLecture(Lecture lecture) {
        AtomicLong id = new AtomicLong();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            id.set(lectureDao.insertLecture(lecture));
        });

        return id.get();
    }

}
