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
import com.example.lms.model.Student;

import org.jetbrains.annotations.NotNull;

public class StudentsAdapter extends ListAdapter<Student, StudentsAdapter.StudentsViewHolder> {
    public interface OnStudentClickListener {
        void onClickStudent(Student student);
    }

    public interface OnDeleteStudentClickListener {
        void onDeleteStudent(Student student);
    }

    public OnStudentClickListener listener;
    public OnDeleteStudentClickListener deleteListener;

    public void setOnStudentClickListener(OnStudentClickListener listener) {
        this.listener = listener;
    }

    public void setOnStudentDeleteListener(OnDeleteStudentClickListener listener) {
        this.deleteListener = listener;
    }

    public StudentsAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Student> DIFF_CALLBACK = new DiffUtil.ItemCallback<Student>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Student oldItem, @NonNull @NotNull Student newItem) {
            return oldItem.getStudentID() == newItem.getStudentID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Student oldItem, @NonNull @NotNull Student newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @NotNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new StudentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentsViewHolder holder, int position) {
        Student currentStudent = getItem(position);
        holder.textView.setText(currentStudent.getName());
    }

    class StudentsViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView deleteIcon;
        public StudentsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.user_name);
            deleteIcon = itemView.findViewById(R.id.icon_delete_user);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onClickStudent(getItem(pos));
                    }
                }
            });

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (deleteListener != null && pos != RecyclerView.NO_POSITION) {
                        deleteListener.onDeleteStudent(getItem(pos));
                    }
                }
            });
        }
    }

}

