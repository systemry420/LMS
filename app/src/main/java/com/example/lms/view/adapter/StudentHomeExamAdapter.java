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
import com.example.lms.util.DateConverter;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class StudentHomeExamAdapter extends ListAdapter<Exam, StudentHomeExamAdapter.HomeExamsViewHolder> {
    public interface OnExamClickListener {
        void onClickExam(Exam student);
    }

    public OnExamClickListener listener;

    public void setOnExamClickListener(OnExamClickListener listener) {
        this.listener = listener;
    }

    public StudentHomeExamAdapter() {
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
    public HomeExamsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_lecture, parent, false);
        return new HomeExamsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeExamsViewHolder holder, int position) {
        Exam currentExam = getItem(position);
        holder.examTitle.setText(currentExam.getTitle());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        String date = simpleDateFormat.format(DateConverter.longToCalendar(currentExam.getDate()));

        holder.examDate.setText(currentExam.getDate().toString());
    }

    class HomeExamsViewHolder extends RecyclerView.ViewHolder {
        private final TextView examTitle;
        private final TextView examDate;

        public HomeExamsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            examTitle = itemView.findViewById(R.id.lecture_title);
            examDate = itemView.findViewById(R.id.lecture_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onClickExam(getItem(pos));
                    }
                }
            });
        }
    }

}

