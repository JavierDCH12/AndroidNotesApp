package com.example.androidnotesapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @Nullable
    private String category;

    public Note(@NonNull String title, @NonNull String content, @Nullable String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @Nullable
    public String getCategory() {
        return category;
    }

    public void setCategory(@Nullable String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}