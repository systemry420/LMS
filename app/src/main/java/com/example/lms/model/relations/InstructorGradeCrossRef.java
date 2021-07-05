package com.example.lms.model.relations;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;

@Entity(tableName = "instructor_grade",
        primaryKeys = {"instructor_id", "grade_id"},
        foreignKeys = {
                @ForeignKey(entity = Instructor.class,
                        parentColumns = "instructor_id",
                        childColumns = "instructor_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Grade.class,
                        parentColumns = "grade_id",
                        childColumns = "grade_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class InstructorGradeCrossRef {
    @ColumnInfo(name = "grade_id")
    private long gradeID;
    @ColumnInfo(name = "instructor_id")
    private long instructorID;

    public InstructorGradeCrossRef(long instructorID, long gradeID) {
        this.instructorID = instructorID;
        this.gradeID = gradeID;
    }

    public long getGradeID() {
        return gradeID;
    }

    public void setGradeID(long gradeID) {
        this.gradeID = gradeID;
    }

    public long getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(long instructorID) {
        this.instructorID = instructorID;
    }
}
