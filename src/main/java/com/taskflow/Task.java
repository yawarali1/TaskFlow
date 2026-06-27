package com.taskflow;

public record Task(int id, String description, boolean isCompleted) {
 
    public Task toggleComplete() {
        return new Task(this.id, this.description, !this.isCompleted);
    }
}