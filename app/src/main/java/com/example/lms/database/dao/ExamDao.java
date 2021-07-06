package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lms.model.Exam;
import com.example.lms.model.Question;
import com.example.lms.model.relations.ExamQuestions;
import com.example.lms.model.relations.InstructorGradeCrossRef;

import java.util.List;

@Dao
public abstract class ExamDao {
    @Insert
    public abstract long insertExam(Exam exam);

    @Insert
    public void insertQuestions(Exam exam, List<Question> questionList) {
        long id = insertExam(exam);
        for (Question question : questionList) {
            question.setExamID(id);
            insertQuestion(question);
        }
    }

    @Insert
    public abstract long insertQuestion(Question question);

    @Update
    public abstract void updateExam(Exam exam);

    @Delete
    public abstract void deleteExam(Exam exam);

    @Query("SELECT * FROM exams")
    public abstract LiveData<List<Exam>> getAllExams();

    @Query("SELECT * FROM exams where course_id = :courseID order by course_id")
    public abstract LiveData<List<Exam>> getExamsOfCourse(long courseID);

    @Transaction
    @Query("SELECT * FROM questions where exam_id = :examID")
    public abstract LiveData<List<Question>> getQuestionsOfExam(long examID);
}
