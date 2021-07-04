package com.example.lms.model.relations;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import com.example.lms.model.Course;
import com.example.lms.model.Student;

@Entity(tableName = "student_courses")
public abstract class StudentCoursesCrossRef {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_course_id")
    private long studentCourseID;
    @ColumnInfo(name = "student_id", index = true)
    private long studentID;
    @ColumnInfo(name = "course_id", index = true)
    private long courseID;

    public long getStudentCourseID() {
        return studentCourseID;
    }

    public void setStudentCourseID(long studentCourseID) {
        this.studentCourseID = studentCourseID;
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

    @Dao
    public abstract static class StudentCoursesCrossRefDao {
        @Insert
        public abstract void insertCourseToStudent(Student student, Course course);
    }

}
