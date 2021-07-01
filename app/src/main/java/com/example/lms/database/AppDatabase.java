package com.example.lms.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.lms.util.DateConverter;
import com.example.lms.database.dao.CourseDao;
import com.example.lms.database.dao.ExamDao;
import com.example.lms.database.dao.GradeDao;
import com.example.lms.database.dao.InstructorDao;
import com.example.lms.database.dao.LectureDao;
import com.example.lms.database.dao.QuestionDao;
import com.example.lms.database.dao.StudentDao;
import com.example.lms.model.Course;
import com.example.lms.model.Exam;
import com.example.lms.model.Grade;
import com.example.lms.model.Instructor;
import com.example.lms.model.Lecture;
import com.example.lms.model.Question;
import com.example.lms.model.Student;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Grade.class,
        Instructor.class,
        Course.class,
        Student.class,
        Lecture.class,
        Exam.class,
        Question.class}, version = 7, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final int NB_OF_THREADS = 5;
    public abstract GradeDao gradeDao();
    public abstract InstructorDao instructorDao();
    public abstract CourseDao courseDao();
    public abstract StudentDao studentDao();
    public abstract LectureDao lectureDao();
    public abstract ExamDao examDao();
    public abstract QuestionDao questionDao();

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NB_OF_THREADS);

    public static AppDatabase getInstance(final Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "a6"
                    ).fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }

}
