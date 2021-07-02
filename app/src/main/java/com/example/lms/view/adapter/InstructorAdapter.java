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
import com.example.lms.model.Instructor;

import org.jetbrains.annotations.NotNull;

public class InstructorAdapter extends ListAdapter<Instructor, InstructorAdapter.InstructorViewHolder> {
    public interface OnInstructorClickListener {
        void onClickItem(Instructor instructor);
    }

    public interface OnDeleteInstructorClickListener {
        void onDeleteItem(Instructor instructor);
    }

    public OnInstructorClickListener listener;
    public OnDeleteInstructorClickListener deleteListener;

    public void setOnInstructorClickListener(OnInstructorClickListener listener) {
        this.listener = listener;
    }

    public void setOnInstructorDeleteListener(OnDeleteInstructorClickListener listener) {
        this.deleteListener = listener;
    }
    
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
                .inflate(R.layout.instructor_list_item, parent, false);
        return new InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InstructorViewHolder holder, int position) {
        Instructor currentInstructor = getItem(position);
        holder.textView.setText(currentInstructor.getName());
    }

    class InstructorViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageButton deleteIcon;
        public InstructorViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
            deleteIcon = itemView.findViewById(R.id.icon_delete_instructor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onClickItem(getItem(position));
                    }
                }
            });

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (deleteListener != null && position != RecyclerView.NO_POSITION) {
                        deleteListener.onDeleteItem(getItem(position));
                    }
                }
            });


        }
    }

}
