package com.example.lms.view.activity.instructor.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.model.relations.ExamQuestions;
import com.example.lms.util.DateConverter;
import com.example.lms.util.SpinnerItem;
import com.example.lms.model.Course;
import com.example.lms.model.Exam;
import com.example.lms.model.Question;
import com.example.lms.view.adapter.InstructorQuestionAdapter;
import com.example.lms.viewmodel.CourseViewModel;
import com.example.lms.viewmodel.ExamViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExamDetailsActivity extends AppCompatActivity {

    private LinearLayout detailsLayout, questionsLayout, qaLayout, mcqLayout;
    private CalendarView calendarView;
    private TimePicker timePicker;
    private TextInputEditText txtExamTitle, txtExamScore,
            txtExamDuration, txtQaQuestion, txtMcqQuestion,
            txtMcqOption1, txtMcqOption2, txtMcqOption3;
    private String examTitle, qaQuestion, mcqQuestion, mcqOption1, mcqOption2, mcqOption3;
    private int examScore, examDuration;
    private Button btnProceed, btnNextQuestion, btnSubmitExam;
    private Spinner spinnerQuestionType, spinnerCourse;
    private String questionType;
    private List<Question> questionList;
    private RecyclerView recyclerView;
    private InstructorQuestionAdapter questionsAdapter;
    private ExamViewModel examViewModel;
    private CourseViewModel courseViewModel;
    private List<SpinnerItem> coursesList = new ArrayList<>();
    private SpinnerItem selectedCourse;
    private Long examDate;
    private Exam exam;
    private int year, month, dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);

        initMembers();

        setButtonsListeners();

        setSpinnerListener();

        setTimepickerListener();

        questionsAdapter = new InstructorQuestionAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(questionsAdapter);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        setCourseSpinner();

        setCalendarListener();

        questionsAdapter.setOnDeleteListener(new InstructorQuestionAdapter.OnDeleteQuestionClickListener() {
            @Override
            public void onDeleteItem(Question question) {
                removeQuestion(question);
            }
        });
    }

    private void setTimepickerListener() {
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                examDate = DateConverter
                        .calendarToLong(year, month, dayOfMonth, hourOfDay, minute);
            }
        });
    }

    private void removeQuestion(Question question) {
        questionList.remove(question);
        questionsAdapter.submitList(questionList);
    }

    private void setCalendarListener() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ExamDetailsActivity.this.year = year;
                ExamDetailsActivity.this.month = month;
                ExamDetailsActivity.this.dayOfMonth = dayOfMonth;
            }
        });
    }

    private void submitExam() {
        //todo : save time in database
        examViewModel.insertQuestions(exam, questionList);
        Toast.makeText(this, "Exam is added", Toast.LENGTH_LONG).show();
        finish();
    }

    private void proceedToQuestions() {
        if(txtExamTitle.getText().toString().equals("")) {
            txtExamTitle.setError("Please provide a title for the exam");
            return;
        }
        if(txtExamDuration.getText().toString().equals("")) {
            txtExamDuration.setError("Please provide a duration for the exam");
            return;
        }
        if(txtExamScore.getText().toString().equals("")) {
            txtExamScore.setError("Please provide a score for the exam");
            return;
        }
        if(spinnerCourse.getSelectedItemId() == 0) {
            Toast.makeText(this, "Please select a course", Toast.LENGTH_LONG).show();
            return;
        }

        examTitle = txtExamTitle.getText().toString();
        examScore = Integer.parseInt(txtExamScore.getText().toString());
        examDuration = Integer.parseInt(txtExamDuration.getText().toString());

        txtExamDuration.setText("");
        txtExamTitle.setText("");
        txtExamScore.setText("");
        detailsLayout.setVisibility(View.GONE);
        questionsLayout.setVisibility(View.VISIBLE);

        Long courseID = selectedCourse.getId();
        exam = new Exam(courseID, examTitle, examDuration, examDate, examScore);

    }

    private void nextQuestion() {
        if (questionType.equals("qa") || questionType.equals("tf")) {
            String title = txtQaQuestion.getText().toString();
            Question question = new Question();
            question.setTitle(title);
            question.setType(questionType);
            questionList.add(question);
            txtQaQuestion.setText("");

        }
        else  {
            String title = txtMcqQuestion.getText().toString();
            String option1 = txtMcqOption1.getText().toString();
            String option2 = txtMcqOption2.getText().toString();
            String option3 = txtMcqOption3.getText().toString();

            Question questionMcq = new Question();
            questionMcq.setType(questionType);
            questionMcq.setTitle(title);
            questionMcq.setOption1(option1);
            questionMcq.setOption2(option2);
            questionMcq.setOption3(option3);

            questionList.add(questionMcq);

            txtMcqQuestion.setText("");
            txtMcqOption1.setText("");
            txtMcqOption2.setText("");
            txtMcqOption3.setText("");
        }

        questionsAdapter.submitList(questionList);

    }


    private void setCourseSpinner() {
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        ArrayAdapter<SpinnerItem> coursesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, coursesList);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(coursesAdapter);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                coursesList.add(new SpinnerItem((long) 0, "Please select a course"));
                for(Course course: courses) {
                    coursesList.add(new SpinnerItem(course.getCourseID(), course.getName()));
                }
                coursesAdapter.notifyDataSetChanged();
            }
        });

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (SpinnerItem) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpinnerListener() {
        spinnerQuestionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    questionType = "mcq";
                    mcqLayout.setVisibility(View.VISIBLE);
                    qaLayout.setVisibility(View.GONE);
                }
                else if(position == 1) {
                    questionType = "qa";
                    qaLayout.setVisibility(View.VISIBLE);
                    mcqLayout.setVisibility(View.GONE);
                }
                else {
                    questionType = "tf";
                    qaLayout.setVisibility(View.VISIBLE);
                    mcqLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setButtonsListeners() {
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedToQuestions();
            }
        });

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        btnSubmitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitExam();
            }
        });
    }




    private void initMembers() {
        questionList = new ArrayList<>();
        timePicker = findViewById(R.id.exam_time_picker);
        detailsLayout = findViewById(R.id.layout_exam_details);
        questionsLayout = findViewById(R.id.layout_exam_questions);
        qaLayout = findViewById(R.id.qa_layout);
        mcqLayout = findViewById(R.id.mcq_layout);
        calendarView = findViewById(R.id.calendar_exam_date);
        txtExamDuration = findViewById(R.id.txt_exam_duration);
        txtExamTitle = findViewById(R.id.txt_exam_title);
        txtExamScore = findViewById(R.id.txt_exam_score);
        btnProceed = findViewById(R.id.btn_proceed);
        txtQaQuestion = findViewById(R.id.txt_question_qa);
        txtMcqQuestion = findViewById(R.id.txt_question_mcq);
        txtMcqOption1 = findViewById(R.id.txt_mcq_option1);
        txtMcqOption2 = findViewById(R.id.txt_mcq_option2);
        txtMcqOption3 = findViewById(R.id.txt_mcq_option3);
        btnNextQuestion = findViewById(R.id.btn_next_question);
        btnSubmitExam = findViewById(R.id.btn_submit_exam);
        spinnerQuestionType = findViewById(R.id.spinner_question_type);
        spinnerCourse = findViewById(R.id.spinner_exam_course);
        recyclerView = findViewById(R.id.exam_questions_recycler_view);
    }


}