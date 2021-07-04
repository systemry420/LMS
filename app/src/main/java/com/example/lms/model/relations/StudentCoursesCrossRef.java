package com.example.lms.model.relations;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"student_id", "course_id"})
public class StudentCoursesCrossRef {

    @ColumnInfo(name = "student_id")
    private long studentID;
    @ColumnInfo(name = "course_id")
    private long courseID;

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }
}
