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

public class StudentAdapter2 extends ListAdapter<Student, StudentAdapter2.Student2ViewHolder> {
    public interface OnStudent2ClickListener {
        void onClickStudent(Student student);
    }

    public OnStudent2ClickListener listener;

    public void setOnStudentClickListener(OnStudent2ClickListener listener) {
        this.listener = listener;
    }


    public StudentAdapter2() {
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
    public Student2ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item2, parent, false);
        return new Student2ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Student2ViewHolder holder, int position) {
        Student currentStudent = getItem(position);
        holder.textView.setText(currentStudent.getName());
    }

    class Student2ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public Student2ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.user_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onClickStudent(getItem(pos));
                    }
                }
            });

        }
    }

}

