package br.com.hello.service;

import java.util.List;

import br.com.hello.domain.Task;

public interface TaskService {
	  
    List<Task> getAll();
    
    Task create(Task task);
}
