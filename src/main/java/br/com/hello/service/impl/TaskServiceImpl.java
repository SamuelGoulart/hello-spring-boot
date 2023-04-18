package br.com.hello.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hello.domain.Task;
import br.com.hello.exception.Exceptions;
import br.com.hello.exception.GenericException;
import br.com.hello.repository.TaskRepository;
import br.com.hello.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	public List<Task> getAll() {
		List<Task> tasks = taskRepository.findAll();
		 
		if(tasks.isEmpty()) {
			throw new GenericException(Exceptions.TASK_NOT_FOUND);
		}
		
		return tasks;
	}

	public Task create(Task task) {
		return taskRepository.save(task);
	}

}
