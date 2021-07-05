package com.example.lms.model.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.lms.model.Exam;
import com.example.lms.model.Question;

import java.util.List;

public class ExamQuestions {
    @Embedded
    private Exam exam;

    @Relation(
            parentColumn = "exam_id",
            entityColumn = "exam_id"
    )
    public List<Question> examQuestions;

    public ExamQuestions() {
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Question> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<Question> examQuestions) {
        this.examQuestions = examQuestions;
    }
}
