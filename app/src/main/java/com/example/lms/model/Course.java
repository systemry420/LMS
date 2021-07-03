package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "courses")
public class Course implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private long courseID;

    @ColumnInfo(name = "grade_id")
    private long gradeID;

    @ColumnInfo(name = "instructor_id")
    private long instructorID;

    @ColumnInfo
    private String name;
    @ColumnInfo
    private String description;

    public Course(long gradeID, long instructorID, String name, String description) {
        this.gradeID = gradeID;
        this.instructorID = instructorID;
        this.name = name;
        this.description = description;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public long getGradeID() {
        return gradeID;
    }

    public void setGradeID(long gradeID) {
        this.gradeID = gradeID;
    }

    public long getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(long instructorID) {
        this.instructorID = instructorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
