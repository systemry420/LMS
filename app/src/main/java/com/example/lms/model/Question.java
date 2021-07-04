package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    private long questionID;
    @ColumnInfo(name = "exam_id")
    private long examID;
    @ColumnInfo
    private String type;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String answer;
    @ColumnInfo
    private String option1;
    @ColumnInfo
    private String option2;
    @ColumnInfo
    private String option3;

    public Question() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setExamID(long examID) {
        this.examID = examID;
    }

    public long getExamID() {
        return examID;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }
}
