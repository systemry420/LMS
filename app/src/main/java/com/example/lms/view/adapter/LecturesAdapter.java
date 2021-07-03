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

public class LecturesAdapter extends ListAdapter<Lecture, LecturesAdapter.LecturesViewHolder> {

    public interface OnLectureClickListener {
        void onClickLecture(Lecture lecture);
    }

    public interface OnDeleteLectureClickListener {
        void onDeleteLecture(Lecture lecture);
    }

    private OnLectureClickListener listener;
    private OnDeleteLectureClickListener deleteListener;

    public void setOnLectureClickListener(OnLectureClickListener listener) {
        this.listener = listener;
    }

    public void setOnDeleteLectureListener(OnDeleteLectureClickListener listener) {
        this.deleteListener = listener;
    }

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
                .inflate(R.layout.list_item_2, parent, false);
        return new LecturesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LecturesViewHolder holder, int position) {
        Lecture currentLecture = getItem(position);
        holder.textView.setText(currentLecture.getLectureTitle());
        holder.subTextView.setText(currentLecture.getLectureDate());
    }

    class LecturesViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView subTextView;
        private final ImageView deleteIcon;
        public LecturesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
            subTextView = itemView.findViewById(R.id.item_subtitle);
            deleteIcon = itemView.findViewById(R.id.icon_delete_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onClickLecture(getItem(position));
                    }
                }
            });

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        deleteListener.onDeleteLecture(getItem(position));
                    }
                }
            });
        }
    }

}

