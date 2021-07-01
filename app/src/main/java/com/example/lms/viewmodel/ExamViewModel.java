package com.example.lms.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lms.database.repository.InstructorRepo;
import com.example.lms.model.Exam;
import com.example.lms.model.Lecture;
import com.example.lms.model.Question;

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

    public LiveData<List<Question>> getQuestionsList(long examID) {
        questionsList = instructorRepo.getQuestionsOfExam(examID);
        return questionsList;
    }

    public void insertQuestionsToExam(List<Question> questionList) {
        instructorRepo.insertQuestionsToExam(questionList);
    }

    public LiveData<List<Exam>> getAllExams() {
        return allExams;
    }

    public long insertExam(Exam exam) {
        return instructorRepo.insertExam(exam);
    }


}
