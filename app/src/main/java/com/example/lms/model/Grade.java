package com.example.lms.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grades")
public class Grade {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "grade_id")
    private long gradeID;

    @ColumnInfo(name = "g_name")
    private String gradeName;

    public Grade(String gradeName) {
        this.gradeName = gradeName;
    }

    public long getGradeID() {
        return gradeID;
    }

    public void setGradeID(long gradeID) {
        this.gradeID = gradeID;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
