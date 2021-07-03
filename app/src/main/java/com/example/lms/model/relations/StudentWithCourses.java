package com.example.lms.model.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.lms.model.Course;
import com.example.lms.model.Student;

import java.util.List;

public class StudentWithCourses {
    @Embedded
    public Student student;
    @Relation(
            parentColumn = "student_id",
            entityColumn = "course_id",
            associateBy = @Junction(StudentCoursesCrossRef.class)
    )
    public List<Course> courseList;
}
