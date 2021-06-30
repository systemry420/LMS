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
import com.example.lms.model.Exam;

import org.jetbrains.annotations.NotNull;

public class ExamAdapter extends ListAdapter<Exam, ExamAdapter.ExamsViewHolder> {

    public ExamAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Exam> DIFF_CALLBACK = new DiffUtil.ItemCallback<Exam>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Exam oldItem, @NonNull @NotNull Exam newItem) {
            return oldItem.getExamID() == newItem.getExamID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Exam oldItem, @NonNull @NotNull Exam newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    @NonNull
    @NotNull
    @Override
    public ExamsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ExamsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExamsViewHolder holder, int position) {
        Exam currentExam = getItem(position);
        holder.textView.setText(currentExam.getTitle());
    }

    static class ExamsViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public ExamsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
        }
    }

}

