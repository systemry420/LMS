package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lms.model.Instructor;

import java.util.List;

@Dao
public abstract class InstructorDao {

    @Insert
    public abstract long insertInstructor(Instructor instructor);

    @Update
    public abstract void updateInstructor(Instructor instructor);

    @Delete
    public abstract void deleteInstructor(Instructor instructor);

    @Query("SELECT * FROM instructor")
    public abstract LiveData<List<Instructor>> getAllInstructor();

}
