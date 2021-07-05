package com.example.lms.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.example.lms.model.Lecture;

import org.jetbrains.annotations.NotNull;

public class StudentLecturesAdapter extends ListAdapter<Lecture, StudentLecturesAdapter.StudentLecturesViewHolder> {

    public interface OnLectureClickListener {
        void onClickLecture(Lecture lecture);
    }

    private OnLectureClickListener listener;

    public void setOnLectureClickListener(OnLectureClickListener listener) {
        this.listener = listener;
    }


    public StudentLecturesAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Lecture> DIFF_CALLBACK = new DiffUtil.ItemCallback<Lecture>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Lecture oldItem, @NonNull @NotNull Lecture newItem) {
            return oldItem.getLectureID() == newItem.getLectureID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Lecture oldItem, @NonNull @NotNull Lecture newItem) {
            return oldItem.getLectureTitle().equals(newItem.getLectureTitle());
        }
    };

    @NonNull
    @NotNull
    @Override
    public StudentLecturesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_lecture, parent, false);
        return new StudentLecturesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentLecturesViewHolder holder, int position) {
        Lecture currentLecture = getItem(position);
        holder.textView.setText(currentLecture.getLectureTitle());
        holder.subTextView.setText(currentLecture.getLectureDate());
    }

    class StudentLecturesViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView subTextView;
        public StudentLecturesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.lecture_title);
            subTextView = itemView.findViewById(R.id.lecture_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onClickLecture(getItem(position));
                    }
                }
            });

        }
    }

}

