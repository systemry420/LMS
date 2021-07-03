package com.example.lms.model.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"student_id", "course_id"})
public class StudentCoursesCrossRef {
    private long studentID;
    private long courseID;


}
