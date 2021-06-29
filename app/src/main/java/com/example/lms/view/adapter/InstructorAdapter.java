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
import com.example.lms.model.Instructor;

import org.jetbrains.annotations.NotNull;

public class InstructorAdapter extends ListAdapter<Instructor, InstructorAdapter.InstructorViewHolder> {

    public InstructorAdapter() {
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
    public InstructorViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InstructorViewHolder holder, int position) {
        Instructor currentInstructor = getItem(position);
        holder.textView.setText(currentInstructor.getName());
    }

    static class InstructorViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public InstructorViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
        }
    }

}
