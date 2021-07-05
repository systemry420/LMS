package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lms.model.Student;

import java.util.List;

@Dao
public abstract class StudentDao {
    @Transaction
    @Insert
    public abstract long insertStudent(Student student);

    @Update
    public abstract void updateStudent(Student student);

    @Delete
    public abstract void deleteStudent(Student students);

    @Query("SELECT * FROM students")
    public abstract LiveData<List<Student>> getAllStudents();

    @Transaction
    @Query("select * from students where grade_id = :gradeID")
    public abstract LiveData<List<Student>> getStudentsOfGrade(long gradeID);

    @Query("SELECT * FROM students WHERE student_id =:id")
    public abstract Student getStudent(int id);


}
