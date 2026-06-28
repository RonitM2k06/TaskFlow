package com.taskflow.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.taskflow.model.Task;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles persistence of tasks to a JSON file.
 */
public class JsonFileManager {
    private static final Path STORAGE_PATH = Path.of("tasks.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Loads all tasks from the JSON storage. If the file does not exist, an empty list is returned.
     */
    public static List<Task> loadAll() {
        if (!Files.exists(STORAGE_PATH)) {
            return new ArrayList<>();
        }
        try (Reader reader = Files.newBufferedReader(STORAGE_PATH)) {
            return GSON.fromJson(reader, new TypeToken<List<Task>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Failed to read tasks.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Persists the provided list of tasks to the JSON file.
     */
    public static void saveAll(List<Task> tasks) {
        try (Writer writer = Files.newBufferedWriter(STORAGE_PATH)) {
            GSON.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Failed to write tasks.json: " + e.getMessage());
        }
    }

    /**
     * Finds a task by its ID.
     */
    public static Optional<Task> findById(String id) {
        return loadAll().stream().filter(t -> t.getId().equals(id)).findFirst();
    }
}
