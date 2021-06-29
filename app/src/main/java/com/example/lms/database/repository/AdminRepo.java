package com.example.lms.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lms.database.AppDatabase;
import com.example.lms.database.dao.CourseDao;
import com.example.lms.database.dao.GradeDao;
import com.example.lms.database.dao.InstructorDao;
import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;

import java.util.List;

public class AdminRepo {
    private static final String TAG = "AdminRepo";
    private final GradeDao gradeDao;
    private final InstructorDao instructorDao;
    private final CourseDao courseDao;
    private final LiveData<List<Grade>> allGrades;
    private final LiveData<List<Instructor>> allInstructors;
    private final LiveData<List<Course>> allCourses;

    public AdminRepo(Application application) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        gradeDao = db.gradeDao();
        instructorDao = db.instructorDao();
        courseDao = db.courseDao();


        allGrades = gradeDao.getAllGrades();
        allInstructors = instructorDao.getAllInstructor();
        allCourses = courseDao.getAllCourses();
    }

    public LiveData<List<Grade>> getAllGrades() {
        return allGrades;
    }

    public LiveData<List<Instructor>> getAllInstructors() {
        return allInstructors;
    }

    public LiveData<List<Course>> getAllICourses() {
        return allCourses;
    }

    public void insertGrade(Grade grade) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gradeDao.insertGrade(grade);
        });
    }

    public void insertInstructor(Instructor instructor) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            instructorDao.insertInstructor(instructor);
        });
    }

    public void insertCourse(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.insertCourse(course);
        });
    }

}
