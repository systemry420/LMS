package com.example.lms.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.example.lms.model.Question;

import org.jetbrains.annotations.NotNull;

public class InstructorQuestionAdapter extends ListAdapter<Question, InstructorQuestionAdapter.QuestionsViewHolder> {

    public InstructorQuestionAdapter() {
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
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new QuestionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuestionsViewHolder holder, int position) {
        Question currentQuestion = getItem(position);
        if (currentQuestion.getType().equals("mcq")) {
            holder.textView.setText(
                    currentQuestion.getTitle() + "\n" +
                    currentQuestion.getOption1() + "\n" +
                    currentQuestion.getOption2() + "\n" +
                    currentQuestion.getOption3());
        }
        else {
            holder.textView.setText(currentQuestion.getTitle());
        }
    }

    static class QuestionsViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public QuestionsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
        }
    }

}
