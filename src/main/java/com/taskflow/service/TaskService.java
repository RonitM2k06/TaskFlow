package com.taskflow.service;

import com.taskflow.model.Task;
import com.taskflow.storage.JsonFileManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Provides business‑logic operations for managing tasks.
 */
public class TaskService {
    private List<Task> tasks;

    public TaskService() {
        this.tasks = JsonFileManager.loadAll();
    }

    /** Returns a copy of the current task list. */
    public List<Task> list() {
        return new ArrayList<>(tasks);
    }

    public Task add(String title, String description, String[] tags, LocalDate dueDate) {
        Task task = new Task(title, description, tags, dueDate);
        tasks.add(task);
        persist();
        return task;
    }

    public boolean delete(String id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));
        if (removed) persist();
        return removed;
    }

    public boolean complete(String id) {
        Optional<Task> opt = tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (opt.isPresent()) {
            opt.get().setCompleted(true);
            persist();
            return true;
        }
        return false;
    }

    public List<Task> filterByTag(String tag) {
        return tasks.stream()
                .filter(t -> {
                    for (String tg : t.getTags()) {
                        if (tg.equalsIgnoreCase(tag)) return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private void persist() {
        JsonFileManager.saveAll(tasks);
    }
}
