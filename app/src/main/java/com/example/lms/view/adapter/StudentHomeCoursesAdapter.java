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
import com.example.lms.model.Course;
import org.jetbrains.annotations.NotNull;

public class StudentHomeCoursesAdapter extends ListAdapter<Course, StudentHomeCoursesAdapter.HomeCoursesViewHolder> {
    public interface OnCourseClickListener {
        void onClickCourse(Course student);
    }

    public OnCourseClickListener listener;

    public void setOnCourseClickListener(OnCourseClickListener listener) {
        this.listener = listener;
    }

    public StudentHomeCoursesAdapter() {
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
    public HomeCoursesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_home_course, parent, false);
        return new HomeCoursesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeCoursesViewHolder holder, int position) {
        Course currentCourse = getItem(position);
        holder.courseTitle.setText(currentCourse.getName());
        holder.courseDesc.setText(currentCourse.getDescription());
    }

    class HomeCoursesViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitle;
        private final TextView courseDesc;
        // todo assign course image
        //        private final ImageView courseImage;
        public HomeCoursesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.course_title);
            courseDesc= itemView.findViewById(R.id.course_description);
//            courseImage = itemView.findViewById(R.id.course_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onClickCourse(getItem(pos));
                    }
                }
            });
        }
    }

}

