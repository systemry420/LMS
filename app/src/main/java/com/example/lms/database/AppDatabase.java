package com.example.lms.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lms.database.dao.GradeDao;
import com.example.lms.model.Grade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Grade.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final int NB_OF_THREADS = 5;
    public abstract GradeDao gradeDao();

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NB_OF_THREADS);

    public static AppDatabase getInstance(final Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "lms_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

}
