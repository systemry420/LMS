package com.example.lms.model.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.lms.model.Course;
import com.example.lms.model.Student;

import java.util.List;

public class CourseWithStudents {
    @Embedded
    public Course course;
    @Relation(
            parentColumn = "course_id",
            entityColumn = "student_id",
            associateBy = @Junction(StudentCoursesCrossRef.class)
    )
    public List<Student> studentList;
}
