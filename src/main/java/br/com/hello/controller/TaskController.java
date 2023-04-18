package br.com.hello.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hello.domain.ResponseBody;
import br.com.hello.domain.Task;
import br.com.hello.exception.Exceptions;
import br.com.hello.service.impl.TaskServiceImpl;
import br.com.hello.util.HttpResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
     
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@PostMapping("/task")
	public ResponseEntity<ResponseBody> create(@RequestBody @Valid Task task) {
		try {
			return HttpResponse.ok("Tarefa criada com sucesso", taskServiceImpl.create(task));
		} catch (Exception error) {
			System.out.println(error.getMessage());
			return HttpResponse.serverError();
		}
	}
	
	@GetMapping("/task")
	public ResponseEntity<ResponseBody> getAllTasks() {
		try {
			return HttpResponse.ok("Tarefas listadas com sucesso", taskServiceImpl.getAll());
		} catch (Exception error) {
			switch (error.getMessage()) {
			case Exceptions.TASK_NOT_FOUND:
                return HttpResponse.notFound(Exceptions.TASK_NOT_FOUND);
			default:
				return HttpResponse.serverError();
			}
		}
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseBody> handleValidationExceptions(MethodArgumentNotValidException ex) {
		JSONArray errors = new JSONArray();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();

			JSONObject formatError = new JSONObject();
			formatError.put("menssage", errorMessage);
			formatError.put("param", fieldName);

			errors.add(formatError);
		});

		return HttpResponse.badRequest(errors);
	}
}
