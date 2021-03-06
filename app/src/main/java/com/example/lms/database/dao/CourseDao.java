package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lms.model.Course;

import java.util.List;

@Dao
public abstract class CourseDao {

    @Insert
    public abstract long insertCourse(Course course);

    @Update
    public abstract void updateCourse(Course course);

    @Delete
    public abstract void deleteCourse(Course course);

    @Query("SELECT * FROM courses")
    public abstract LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM courses where course_id = :courseID")
    public abstract LiveData<Course> getCourse(long courseID);

    @Query("SELECT * FROM courses where grade_id = :gradeID ")
    public abstract LiveData<List<Course>> getCoursesOfGrade(long gradeID);
}
