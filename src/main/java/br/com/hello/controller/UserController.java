package br.com.hello.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hello.domain.ResponseBody;
import br.com.hello.domain.User;
import br.com.hello.exception.Exceptions;
import br.com.hello.service.impl.UserServiceImpl;
import br.com.hello.util.HttpResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/user")
	public ResponseEntity<ResponseBody> getAllUsers() {
		try {
			return HttpResponse.ok("Usuários listados com sucesso", userServiceImpl.getAll());
		} catch (Exception error) {
			switch (error.getMessage()) {
			case Exceptions.NO_USER_FOUND:
				return HttpResponse.notFound(Exceptions.NO_USER_FOUND);
			default:
				return HttpResponse.serverError();
			}
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<ResponseBody> getCliente(@PathVariable long id) {
		try {
			return HttpResponse.ok("Usuário(a) listado com sucesso", userServiceImpl.getById(id));
		} catch (Exception error) {
			switch (error.getMessage()) {
			case Exceptions.USER_NOT_FOUND:
				return HttpResponse.notFound(Exceptions.USER_NOT_FOUND);
			default:
				return HttpResponse.serverError();
			}
		}
	}

	@PostMapping("/user")
	public ResponseEntity<ResponseBody> create(@RequestBody @Valid User user) {
		try {
			return HttpResponse.created("Usuário(a) criado com sucesso", userServiceImpl.create(user));
		} catch (Exception error) {
			return HttpResponse.serverError();
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<ResponseBody> delete(@PathVariable long id) {
		try {
			User user = userServiceImpl.getById(id);
			userServiceImpl.delete(user.getId());
			
			return HttpResponse.ok("Usuário(a) deletado com sucesso", new JSONObject());
		} catch (Exception error) {
			switch (error.getMessage()) {
			case Exceptions.USER_NOT_FOUND:
				return HttpResponse.notFound(Exceptions.USER_NOT_FOUND);
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
