package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int courseID;

    @ColumnInfo(name = "grade_id")
    private int gradeID;

    @ColumnInfo(name = "instructor_id")
    private int instructorID;

    @ColumnInfo
    private String name;
    @ColumnInfo
    private String description;

    public Course(int gradeID, int instructorID, String name, String description) {
        this.gradeID = gradeID;
        this.instructorID = instructorID;
        this.name = name;
        this.description = description;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
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
