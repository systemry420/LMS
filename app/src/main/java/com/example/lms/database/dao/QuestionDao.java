package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lms.model.Course;
import com.example.lms.model.Question;

import java.util.List;

@Dao
public abstract class QuestionDao {
//    @Insert
//    public abstract long insertQuestion(Question question);

    @Update
    public abstract void updateQuestion(Question question);

    @Delete
    public abstract void deleteQuestion(Question question);

    @Query("SELECT * FROM questions where exam_id = :examID")
    public abstract LiveData<List<Question>> getQuestionsOfExam(long examID);

    @Insert
    public abstract void insertQuestions(List<Question> questions);
}
