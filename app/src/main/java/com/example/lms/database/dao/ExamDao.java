package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lms.model.Exam;
import com.example.lms.model.Question;
import com.example.lms.model.relations.ExamQuestions;

import java.util.List;

@Dao
public abstract class ExamDao {
    
    @Insert
    public abstract long insertExam(Exam exam);

    @Insert
    public abstract void insertQuestions(List<Question> questionList);

    @Update
    public abstract void updateExam(Exam exam);

    @Delete
    public abstract void deleteExam(Exam exam);

    @Query("SELECT * FROM exams")
    public abstract LiveData<List<Exam>> getAllExams();

    @Query("SELECT * FROM exams where course_id = :courseID order by course_id")
    public abstract LiveData<List<Exam>> getExamsOfCourse(long courseID);

    @Transaction
    @Query("SELECT * FROM exams where exam_id = :examID")
    public abstract LiveData<List<ExamQuestions>> getQuestionsOfExam(long examID);
}
