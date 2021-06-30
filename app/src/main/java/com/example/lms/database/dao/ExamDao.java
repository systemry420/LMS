package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lms.model.Exam;

import java.util.List;

@Dao
public abstract class ExamDao {
    
    @Insert
    public abstract long insertExam(Exam exam);

    @Update
    public abstract void updateExam(Exam exam);

    @Delete
    public abstract void deleteExam(Exam exam);

    @Query("SELECT * FROM exams")
    public abstract LiveData<List<Exam>> getAllExams();


}
