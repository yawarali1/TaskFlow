package com.taskflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
	
	public boolean toggleTaskCompletion(int id) {
		Optional<Task> taskOpt = tasks.stream().filter(t->t.id() == id).findFirst();
		if(taskOpt.isPresent()) {
			Task oldTask = taskOpt.get();
			int index = tasks.indexOf(oldTask);
			tasks.set(index, oldTask.toggleComplete());
			return true;
		}
		return false;
			
		}
	
	public double calculateCompletionRate() {
		if(tasks.isEmpty()) {
			return 0.0;
		}
		long completedCount = tasks.stream ().filter(Task::isCompleted).count();
		
		double rate = ((double) completedCount/ tasks.size()) * 100;
		
		return Math.round(rate * 100) * 100.0;
		
		
	}
	
	
	
	
	
	
	
	}
	
	

