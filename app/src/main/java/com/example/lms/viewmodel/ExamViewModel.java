package com.example.lms.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.InstructorRepo;
import com.example.lms.model.Exam;
import com.example.lms.model.Lecture;
import com.example.lms.model.Question;
import com.example.lms.model.relations.ExamQuestions;

import java.util.List;

public class ExamViewModel extends AndroidViewModel {
    private final InstructorRepo instructorRepo;
    private final LiveData<List<Exam>> allExams;
    private LiveData<List<Question>> questionsList;

    public ExamViewModel(Application application) {
        super(application);
        instructorRepo = new InstructorRepo(application);
        allExams = instructorRepo.getAllExams();
    }

    public LiveData<List<Question>> getQuestionsOfExam(long examID) {
        return instructorRepo.getQuestionsOfExam(examID);
    }

    public void insertQuestions(Exam exam, List<Question> questionList) {
        instructorRepo.insertQuestionsToExam(exam, questionList);
    }

    public LiveData<List<Exam>> getExamsOfCourse(long courseID) {
        return instructorRepo.getExamsOfCourse(courseID);
    }

    public void updateExam(Exam exam) {
        instructorRepo.updateExam(exam);
    }

    public void deleteExam(Exam exam) {
        instructorRepo.deleteExam(exam);
    }
}
