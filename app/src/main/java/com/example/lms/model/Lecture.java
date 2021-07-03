package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "lectures")
public class Lecture implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lecture_id")
    private long lectureID;
    @ColumnInfo(name = "course_id")
    private long courseID;
    @ColumnInfo(name = "lecture_title")
    private String lectureTitle;
    @ColumnInfo(name = "lecture_link")
    private String lectureLink;
    @ColumnInfo(name = "lecture_date")
    private String lectureDate;

    public Lecture(long courseID, String lectureTitle, String lectureLink, String lectureDate) {
        this.courseID = courseID;
        this.lectureTitle = lectureTitle;
        this.lectureLink = lectureLink;
        this.lectureDate = lectureDate;
    }

    public long getLectureID() {
        return lectureID;
    }

    public void setLectureID(long lectureID) {
        this.lectureID = lectureID;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public String getLectureLink() {
        return lectureLink;
    }

    public void setLectureLink(String lectureLink) {
        this.lectureLink = lectureLink;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(String lectureDate) {
        this.lectureDate = lectureDate;
    }

//    public Date getLectureDate() {
//        return DateConverter.fromTimestamp(lectureDate);
//    }
//
//    public void setLectureDate(Date date) {
//        this.lectureDate = lectureDate;
//    }
}
