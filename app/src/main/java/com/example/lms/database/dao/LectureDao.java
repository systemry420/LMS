package com.example.lms.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lms.model.Lecture;

import java.util.List;

@Dao
public abstract class LectureDao {
    @Insert
    public abstract long insertLecture(Lecture lecture);

    @Update
    public abstract void updateLecture(Lecture lecture);

    @Delete
    public abstract void deleteLecture(Lecture lecture);

    @Query("SELECT * FROM lectures")
    public abstract LiveData<List<Lecture>> getAllLectures();

}
