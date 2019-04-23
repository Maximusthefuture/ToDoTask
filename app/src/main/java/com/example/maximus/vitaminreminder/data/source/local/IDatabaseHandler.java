package com.example.maximus.vitaminreminder.data.source.local;

import com.example.maximus.vitaminreminder.data.Task;

import java.util.List;

public interface IDatabaseHandler {

    void addTask(Task task);

    List<Task> getTasks();

    Task getTaskById(String taskId);

    int deleteTaskById(String taskId);

    void deleteTasks();

    int deleteCompletedTasks();

    void updateCompleted(String taskId, boolean completed);

}
