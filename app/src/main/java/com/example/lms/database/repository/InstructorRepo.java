package com.example.lms.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lms.database.AppDatabase;
import com.example.lms.database.dao.ExamDao;
import com.example.lms.database.dao.LectureDao;
import com.example.lms.model.Exam;
import com.example.lms.model.Lecture;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InstructorRepo {
    private final LectureDao lectureDao;
    private final ExamDao examDao;
    private final LiveData<List<Lecture>> allLectures;
    private final LiveData<List<Exam>> allExams;


    public InstructorRepo(Application application) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        lectureDao = db.lectureDao();
        examDao = db.examDao();


        allLectures = lectureDao.getAllLectures();
        allExams = examDao.getAllExams();
    }

    public LiveData<List<Lecture>> getAllLectures() {
        return allLectures;
    }

    public LiveData<List<Exam>> getAllExams() {
        return allExams;
    }

    public long insertLecture(Lecture lecture) {
        AtomicLong id = new AtomicLong();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            id.set(lectureDao.insertLecture(lecture));
        });

        return id.get();
    }

    public long insertExam(Exam exam) {
        AtomicLong id = new AtomicLong();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            id.set(examDao.insertExam(exam));
        });

        return id.get();
    }

}
