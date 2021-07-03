package com.example.lms.util;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class SpinnerItem {
    private Long id;
    private String caption;

    public SpinnerItem(Long id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return id.toString() +" " + caption;
    }
}
