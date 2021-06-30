package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lms.model.Student;

import java.util.List;

@Dao
public abstract class StudentDao {
    @Insert
    public abstract long insertStudent(Student student);

    @Update
    public abstract void updateStudent(Student student);

    @Delete
    public abstract void deleteStudent(Student students);

    @Query("SELECT * FROM students")
    public abstract LiveData<List<Student>> getAllStudents();

}
