package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exams")
public class Exam {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exam_id")
    private Long examID;
    @ColumnInfo(name = "course_id")
    private Long courseID;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private int score;
    @ColumnInfo
    private int duration;

    public Exam(Long courseID, String title, int duration, Long date, int score) {
        this.courseID = courseID;
        this.title = title;
        this.score = score;
        this.date = date;
    }

    public Long getExamID() {
        return examID;
    }

    public void setExamID(Long examID) {
        this.examID = examID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
