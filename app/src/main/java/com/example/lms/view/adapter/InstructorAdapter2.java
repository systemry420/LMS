package com.example.lms.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.example.lms.model.Instructor;

import org.jetbrains.annotations.NotNull;

public class InstructorAdapter2 extends ListAdapter<Instructor, InstructorAdapter2.InstructorAdapter2ViewHolder> {
    public interface OnInstructorClickListener {
        void onClickItem(Instructor instructor);
    }

    public OnInstructorClickListener listener;

    public void setOnInstructorClickListener(OnInstructorClickListener listener) {
        this.listener = listener;
    }

    public InstructorAdapter2() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Instructor> DIFF_CALLBACK = new DiffUtil.ItemCallback<Instructor>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Instructor oldItem, @NonNull @NotNull Instructor newItem) {
            return oldItem.getInstructorID() == newItem.getInstructorID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Instructor oldItem, @NonNull @NotNull Instructor newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @NotNull
    @Override
    public InstructorAdapter2ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item2, parent, false);
        return new InstructorAdapter2ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InstructorAdapter2ViewHolder holder, int position) {
        Instructor currentInstructor = getItem(position);
        holder.textView.setText(currentInstructor.getName());
    }

    class InstructorAdapter2ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public InstructorAdapter2ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.user_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onClickItem(getItem(position));
                    }
                }
            });

        }
    }

}
