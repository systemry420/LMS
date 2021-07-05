package com.example.lms.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lms.database.AppDatabase;
import com.example.lms.database.dao.CourseDao;
import com.example.lms.database.dao.ExamDao;
import com.example.lms.database.dao.LectureDao;
import com.example.lms.database.dao.QuestionDao;
import com.example.lms.model.Course;
import com.example.lms.model.Exam;
import com.example.lms.model.Grade;
import com.example.lms.model.Lecture;
import com.example.lms.model.Question;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InstructorRepo {
    private final LectureDao lectureDao;
    private final ExamDao examDao;
    private final QuestionDao questionDao;
    private final CourseDao courseDao;
    private final LiveData<List<Lecture>> allLectures;
    private final LiveData<List<Exam>> allExams;
    private LiveData<List<Question>> questionsOfExam;


    public InstructorRepo(Application application) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        lectureDao = db.lectureDao();
        examDao = db.examDao();
        questionDao = db.questionDao();
        courseDao = db.courseDao();


        allLectures = lectureDao.getAllLectures();
        allExams = examDao.getAllExams();
    }

    public LiveData<List<Question>> getQuestionsOfExam(long examID) {
        questionsOfExam = questionDao.getQuestionsOfExam(examID);
        return questionsOfExam;
    }


    public LiveData<List<Course>> getCoursesOfGrade(long gradeID) {
        return courseDao.getCoursesOfGrade(gradeID);
    }

    public LiveData<List<Lecture>> getAllLectures() {
        return allLectures;
    }

    public LiveData<List<Lecture>> getLecturesOfCourse(long courseID) {
        return lectureDao.getLecturesOfCourse(courseID);
    }

    public LiveData<List<Exam>> getExamsOfCourse(long courseID) {
        return examDao.getExamsOfCourse(courseID);
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

    public void insertQuestionsToExam(List<Question> questionList) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            examDao.insertQuestions(questionList);
        });
    }

    public void updateLecture(Lecture lecture) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            lectureDao.updateLecture(lecture);
        });
    }

    public void deleteLecture(Lecture lecture) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            lectureDao.deleteLecture(lecture);
        });
    }

    public void updateExam(Exam exam) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            examDao.updateExam(exam);
        });
    }

    public void deleteExam(Exam exam) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            examDao.deleteExam(exam);
        });
    }


}
