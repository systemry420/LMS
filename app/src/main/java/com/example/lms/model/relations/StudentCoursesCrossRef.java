package com.example.lms.model.relations;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import com.example.lms.model.Course;
import com.example.lms.model.Student;

@Entity(tableName = "student_courses",
        primaryKeys = {"student_id", "course_id"},
        foreignKeys = {
            @ForeignKey(entity = Student.class,
            parentColumns = "student_id",
            childColumns = "student_id",
            onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Course.class,
            parentColumns = "course_id",
            childColumns = "course_id",
            onDelete = ForeignKey.CASCADE)
        }
)
public class StudentCoursesCrossRef {
    @ColumnInfo(name = "student_id", index = true)
    private long studentID;
    @ColumnInfo(name = "course_id", index = true)
    private long courseID;

    public StudentCoursesCrossRef(long studentID, long courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public StudentCoursesCrossRef() {
    }

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
