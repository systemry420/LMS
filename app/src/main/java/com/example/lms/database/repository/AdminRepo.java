package com.example.lms.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lms.database.AppDatabase;
import com.example.lms.database.dao.CourseDao;
import com.example.lms.database.dao.GradeDao;
import com.example.lms.database.dao.InstructorDao;
import com.example.lms.database.dao.InstructorGradeDao;
import com.example.lms.database.dao.StudentCourseDao;
import com.example.lms.database.dao.StudentDao;
import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.model.Student;
import com.example.lms.model.relations.InstructorGradeCrossRef;
import com.example.lms.model.relations.StudentCoursesCrossRef;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AdminRepo {
    private static final String TAG = "AdminRepo";
    private final GradeDao gradeDao;
    private final InstructorDao instructorDao;
    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final StudentCourseDao studentCourseDao;
    private final InstructorGradeDao instructorGradeDao;
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
        studentCourseDao = db.studentCoursesDao();
        instructorGradeDao = db.instructorGradeDao();


        allGrades = gradeDao.getAllGrades();
        allInstructors = instructorDao.getAllInstructor();
        allCourses = courseDao.getAllCourses();
        allStudents = studentDao.getAllStudents();
    }

    public LiveData<List<Course>> getCoursesOfStudent(long studentID) {
        return studentCourseDao.getCoursesWithStudentId(studentID);
    }

    public LiveData<List<Grade>> getGradesOfInstructor(long instructorID) {
        return instructorGradeDao.getGradesWithInstructorId(instructorID);
    }

    public void deleteInstructorGradeJoin(InstructorGradeCrossRef join) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            instructorGradeDao.deleteInstructorGradeJoin(join);
        });
    }

    public void deleteStudentCourseJoin(StudentCoursesCrossRef join) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.deleteStudentCoursesJoin(join);
        });
    }

    public LiveData<List<Instructor>> getInstructorsOfGrade(long gradeID) {
        return instructorGradeDao.getInstructorsWithGradeId(gradeID);
    }

    public void insertInstructorGradeJoin(InstructorGradeCrossRef join) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            instructorGradeDao.insertInstructorGradeJoin(join);
        });
    }

    public LiveData<Course> getCourse(long courseID) {
        return courseDao.getCourse(courseID);
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

    public LiveData<List<Student>> getStudentsOfGrade(long gradeID) {
        return studentDao.getStudentsOfGrade(gradeID);
    }

    public LiveData<List<Course>> getCoursesOfGrade(long gradeID) {
        return courseDao.getCoursesOfGrade(gradeID);
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


    public void insertCourseToStudent(StudentCoursesCrossRef join) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.insertStudentCoursesJoin(join);
        });
    }

}
