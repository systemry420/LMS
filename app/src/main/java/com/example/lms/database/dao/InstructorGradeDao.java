package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import com.example.lms.model.Course;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.model.Student;
import com.example.lms.model.relations.InstructorGradeCrossRef;
import com.example.lms.model.relations.StudentCoursesCrossRef;

import java.util.List;

@Dao
public abstract class InstructorGradeDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
            "SELECT * FROM grades INNER JOIN instructor_grade ON " +
                    " grades.grade_id = instructor_grade.grade_id WHERE " +
                    " instructor_grade.instructor_id = :instructorID"
    )
    public abstract LiveData<List<Grade>> getGradesWithInstructorId(long instructorID);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
            "SELECT * FROM instructor INNER JOIN instructor_grade ON " +
                    " instructor.instructor_id = instructor_grade.instructor_id WHERE " +
                    " instructor_grade.grade_id = :gradeID"
    )
    public abstract LiveData<List<Instructor>> getInstructorsWithGradeId(long gradeID);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertInstructorGradeJoin(InstructorGradeCrossRef join);

    @Delete
    public abstract void deleteInstructorGradeJoin(InstructorGradeCrossRef join);

}
