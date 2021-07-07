package com.example.lms.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.example.lms.model.Question;

import org.jetbrains.annotations.NotNull;

public class StudentQuestionsAdapter extends ListAdapter<Question, StudentQuestionsAdapter.QuestionsViewHolder> {

    public StudentQuestionsAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Question> DIFF_CALLBACK = new DiffUtil.ItemCallback<Question>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Question oldItem, @NonNull @NotNull Question newItem) {
            return oldItem.getQuestionID() == newItem.getQuestionID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Question oldItem, @NonNull @NotNull Question newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    @NonNull
    @NotNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_question_list_item, parent, false);
        return new QuestionsViewHolder(root);
    }

    View mcqView, qaView, tfView;

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuestionsViewHolder holder, int position) {
        Question currentQuestion = getItem(position);
        Log.i("Adapter", "onBindViewHolder: " + currentQuestion.getQuestionID());
        if (currentQuestion.getType().equals("mcq")) {
            mcqView.setVisibility(View.VISIBLE);
            holder.questionMCQ.setText("Question " + (position + 1) + ". " + currentQuestion.getTitle());
            holder.radioButton1.setText(currentQuestion.getOption1());
            holder.radioButton2.setText(currentQuestion.getOption2());
            holder.radioButton3.setText(currentQuestion.getOption3());
        }
        if (currentQuestion.getType().equals("tf")){
            tfView.setVisibility(View.VISIBLE);
            holder.questionTrueFalse.setText("Question " + (position + 1) + ". " + currentQuestion.getTitle());
        }
        if (currentQuestion.getType().equals("qa")) {
            qaView.setVisibility(View.VISIBLE);
            holder.questionQA.setText("Question " + (position + 1) + ". " + currentQuestion.getTitle());
        }
    }

    class QuestionsViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionQA;
        private final TextView questionMCQ;
        private final TextView questionTrueFalse;
        private final RadioButton radioButton1;
        private final RadioButton radioButton2;
        private final RadioButton radioButton3;

        public QuestionsViewHolder(@NonNull @NotNull View root) {
            super(root);
            mcqView = root.findViewById(R.id.mcq_view);
            qaView = root.findViewById(R.id.question_answer_view);
            tfView = root.findViewById(R.id.true_false_view);

//            mcqView.setVisibility(View.VISIBLE);
//            qaView.setVisibility(View.VISIBLE);
//            tfView.setVisibility(View.VISIBLE);

            questionQA = qaView.findViewById(R.id.question_answer_placeholder);
            questionMCQ = mcqView.findViewById(R.id.question_mcq_placeholder);
            radioButton1 = mcqView.findViewById(R.id.mcq_option1);
            radioButton2 = mcqView.findViewById(R.id.mcq_option2);
            radioButton3 = mcqView.findViewById(R.id.mcq_option3);

            questionTrueFalse = tfView.findViewById(R.id.question_true_false_placeholder);
        }
    }

}

