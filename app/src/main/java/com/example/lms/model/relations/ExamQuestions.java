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

    public ExamQuestions(Exam exam, List<Question> examQuestions) {
        this.exam = exam;
        this.examQuestions = examQuestions;
    }
}
