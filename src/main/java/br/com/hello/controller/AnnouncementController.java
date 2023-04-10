package br.com.hello.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.hello.domain.Announcement;
import br.com.hello.domain.ResponseBody;
import br.com.hello.exception.Exceptions;
import br.com.hello.service.impl.AnnouncementServiceImpl;
import br.com.hello.util.HttpResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api/v1")
public class AnnouncementController {

	@Autowired
	private AnnouncementServiceImpl announcementServiceImpl;
	
	@PostMapping("/announcement")
	private ResponseEntity<ResponseBody> create(@RequestBody @Valid Announcement announcement) {
		try {
			return HttpResponse.ok("Comunicado criado com sucesso", announcementServiceImpl.create(announcement));
		} catch (Exception error) {
			return HttpResponse.serverError();
		}
		
	}
	
	@GetMapping("/announcement")
	public ResponseEntity<ResponseBody> getAllAnnouncement() {
		try {
			return HttpResponse.ok("Comunicados listados com sucesso", announcementServiceImpl.getAll());
		} catch (Exception error) {
			switch (error.getMessage()) {
			case Exceptions.ANNOUNCEMENT_NOT_FOUND:
				  return HttpResponse.notFound(Exceptions.ANNOUNCEMENT_NOT_FOUND);
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
