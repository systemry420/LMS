package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import com.example.lms.model.Course;
import com.example.lms.model.Student;
import com.example.lms.model.relations.StudentCoursesCrossRef;

import java.util.List;

@Dao
public abstract class StudentCourseDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        "SELECT * FROM courses INNER JOIN student_courses ON " +
        " courses.course_id = student_courses.course_id WHERE " +
        " student_courses.student_id = :studentID"
        )
    public abstract LiveData<List<Course>> getCoursesWithStudentId(long studentID);


    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
            "SELECT * FROM students, student_courses WHERE " +
            " students.student_id = student_courses.student_id AND " +
            " student_courses.course_id = :courseID"
    )
    public abstract LiveData<List<Student>> getStudentsWithCourseId(long courseID);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertStudentCoursesJoin(StudentCoursesCrossRef join);

}
