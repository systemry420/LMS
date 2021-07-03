package com.example.lms.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lms.database.AppDatabase;
import com.example.lms.database.dao.CourseDao;
import com.example.lms.database.dao.GradeDao;
import com.example.lms.database.dao.InstructorDao;
import com.example.lms.database.dao.LectureDao;
import com.example.lms.database.dao.StudentDao;
import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.model.Lecture;
import com.example.lms.model.Student;
import com.example.lms.model.relations.CourseWithStudents;
import com.example.lms.model.relations.StudentWithCourses;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AdminRepo {
    private static final String TAG = "AdminRepo";
    private final GradeDao gradeDao;
    private final InstructorDao instructorDao;
    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final LiveData<List<Grade>> allGrades;
    private final LiveData<List<Instructor>> allInstructors;
    private final LiveData<List<Course>> allCourses;
    private final LiveData<List<Student>> allStudents;

    public AdminRepo(Application application) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        gradeDao = db.gradeDao();
        instructorDao = db.instructorDao();
        courseDao = db.courseDao();
        studentDao = db.studentDao();


        allGrades = gradeDao.getAllGrades();
        allInstructors = instructorDao.getAllInstructor();
        allCourses = courseDao.getAllCourses();
        allStudents = studentDao.getAllStudents();
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

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public LiveData<List<StudentWithCourses>> getCoursesOfStudent(long studentID) {
        return studentDao.getCoursesOfStudent(studentID);
    }

    public LiveData<List<CourseWithStudents>> getStudentsOfCourse(long courseID) {
        return courseDao.getCourseWithStudents(courseID);
    }

    public long insertStudent(Student student) {
        AtomicLong id = new AtomicLong();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            id.set(studentDao.insertStudent(student));
        });

        return id.get();
    }

    public long insertGrade(Grade grade) {
        AtomicLong id = new AtomicLong();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            id.set(gradeDao.insertGrade(grade));
        });

        return id.get();
    }

    public long insertInstructor(Instructor instructor) {
        AtomicLong id = new AtomicLong();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            id.set(instructorDao.insertInstructor(instructor));
        });

        return id.get();
    }

    public long insertCourse(Course course) {
        AtomicLong id = new AtomicLong();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            id.set(courseDao.insertCourse(course));
        });

        return id.get();
    }

    public void deleteGrade(Grade grade) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            gradeDao.deleteGrade(grade);
        });
    }

    public void deleteInstructor(Instructor instructor) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            instructorDao.deleteInstructor(instructor);
        });
    }

    public void updateInstructor(Instructor instructor) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            instructorDao.updateInstructor(instructor);
        });
    }

    public void deleteCourse(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourse(course);
        });
    }

    public void updateCourse(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.updateCourse(course);
        });
    }

    public void deleteStudent(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(student);
        });
    }

    public void updateStudent(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(student);
        });
    }

}
