package com.example.lms.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.AdminRepo;
import com.example.lms.model.Course;
import com.example.lms.model.Student;
import com.example.lms.model.relations.StudentCoursesCrossRef;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private final AdminRepo adminRepo;
    private final LiveData<List<Student>> allStudents;

    public StudentViewModel(Application application) {
        super(application);
        adminRepo = new AdminRepo(application);
        allStudents = adminRepo.getAllStudents();
    }

    public void deleteStudentCourseJoin(StudentCoursesCrossRef join) {
        adminRepo.deleteStudentCourseJoin(join);
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public LiveData<List<Student>> getStudentsOfGrade(long gradeID) {
        return adminRepo.getStudentsOfGrade(gradeID);
    }

    public LiveData<List<Course>> getCoursesOfStudent(long studentID) {
        return adminRepo.getCoursesOfStudent(studentID);
    }

    public void insertCourseToStudent(StudentCoursesCrossRef join) {
        adminRepo.insertCourseToStudent(join);
    }

    public long insertStudent(Student Student) {
        return adminRepo.insertStudent(Student);
    }

    public void updateStudent(Student student) {
        adminRepo.updateStudent(student);
    }

    public void deleteStudent(Student student) {
        adminRepo.deleteStudent(student);
    }

}
