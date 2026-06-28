package com.taskflow.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a single task in the TaskFlow application.
 */
public class Task {
    private final String id;
    private String title;
    private String description;
    private boolean completed;
    private String[] tags;
    private LocalDate dueDate;

    public Task(String title, String description, String[] tags, LocalDate dueDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.tags = tags != null ? tags : new String[0];
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
