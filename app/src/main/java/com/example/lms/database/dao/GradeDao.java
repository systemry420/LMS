package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lms.model.Grade;

import java.util.List;

@Dao
public abstract class GradeDao {
    @Insert
    public abstract long insertGrade(Grade gradeModel);

    @Update
    public abstract void updateGrade(Grade gradeModel);

    @Delete
    public abstract void deleteGrade(Grade gradeModel);

    @Query("SELECT * FROM grades")
    public abstract LiveData<List<Grade>> getAllGrades();

}
