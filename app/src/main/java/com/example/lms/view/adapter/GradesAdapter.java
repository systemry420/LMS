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
import com.example.lms.model.Grade;

import org.jetbrains.annotations.NotNull;

public class GradesAdapter extends ListAdapter<Grade, GradesAdapter.GradesViewHolder> {

    public interface OnGradeClickListener {
        void onClickItem(Grade grade);
    }

    public interface OnDeleteGradeClickListener {
        void onDeleteItem(Grade grade);
    }

    private OnGradeClickListener listener;
    private OnDeleteGradeClickListener deleteListener;

    public void setOnItemClickListener(OnGradeClickListener listener) {
        this.listener = listener;
    }

    public void setOnDeleteListener(OnDeleteGradeClickListener listener) {
        this.deleteListener = listener;
    }

    public GradesAdapter() {
        super(DIFF_CALLBACK);
    }


    class GradesViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageButton deleteIcon;
        public GradesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
            deleteIcon = itemView.findViewById(R.id.icon_delete_item);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onClickItem(getItem(position));
                }
            });

            deleteIcon.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    deleteListener.onDeleteItem(getItem(position));
                }
            });
        }
    }

    public static final DiffUtil.ItemCallback<Grade> DIFF_CALLBACK = new DiffUtil.ItemCallback<Grade>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Grade oldItem, @NonNull @NotNull Grade newItem) {
            return oldItem.getGradeID() == newItem.getGradeID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Grade oldItem, @NonNull @NotNull Grade newItem) {
            return oldItem.getGradeName().equals(newItem.getGradeName());
        }
    };

    @NonNull
    @NotNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new GradesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GradesViewHolder holder, int position) {
        Grade currentGrade = getItem(position);
        holder.textView.setText(currentGrade.getGradeName());
    }

}
