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
import com.example.lms.model.Lecture;

import org.jetbrains.annotations.NotNull;

public class LecturesAdapter extends ListAdapter<Lecture, LecturesAdapter.LecturesViewHolder> {

    public LecturesAdapter() {
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
    public LecturesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new LecturesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LecturesViewHolder holder, int position) {
        Lecture currentLecture = getItem(position);
        holder.textView.setText(currentLecture.getLectureTitle());
    }

    static class LecturesViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public LecturesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
        }
    }

}

