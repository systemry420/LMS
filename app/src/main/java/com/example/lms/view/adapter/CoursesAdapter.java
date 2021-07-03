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
import com.example.lms.model.Course;

import org.jetbrains.annotations.NotNull;

public class CoursesAdapter extends ListAdapter<Course, CoursesAdapter.CoursesViewHolder> {
    public interface OnCourseClickListener {
        void onClickCourse(Course course);
    }

    public interface OnDeleteCourseClickListener {
        void onDeleteCourse(Course course);
    }

    private OnCourseClickListener listener;
    private OnDeleteCourseClickListener deleteListener;

    public void setOnItemClickListener(OnCourseClickListener listener) {
        this.listener = listener;
    }

    public void setOnDeleteListener(OnDeleteCourseClickListener listener) {
        this.deleteListener = listener;
    }

    public CoursesAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Course> DIFF_CALLBACK = new DiffUtil.ItemCallback<Course>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Course oldItem, @NonNull @NotNull Course newItem) {
            return oldItem.getCourseID() == newItem.getCourseID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Course oldItem, @NonNull @NotNull Course newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @NotNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new CoursesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CoursesViewHolder holder, int position) {
        Course currentCourse = getItem(position);
        holder.textView.setText(currentCourse.getName());
    }

    class CoursesViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView deleteIcon;
        public CoursesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
            deleteIcon = itemView.findViewById(R.id.icon_delete_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onClickCourse(getItem(position));
                    }
                }
            });

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        deleteListener.onDeleteCourse(getItem(position));
                    }
                }
            });
        }
    }

}
