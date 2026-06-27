package com.taskflow;

import java.util.ArrayList;
import java.util.List;


public class TaskManager {
	private final List<Task>  tasks= new ArrayList<>();
	private int idCounter = 1;
	
	public Task addTask(String description) {
		Task newTask = new Task(idCounter++,description,false);
		tasks.add(newTask);
		return newTask;
	}
	
	public List<Task> getAllTasks(){
		return new ArrayList<>(tasks);
	}
	
	
}
