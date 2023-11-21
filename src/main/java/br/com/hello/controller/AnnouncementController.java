package br.com.hello.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.hello.domain.Announcement;
import br.com.hello.domain.ResponseBody;
import br.com.hello.exception.Exceptions;
import br.com.hello.service.impl.AnnouncementServiceImpl;
import br.com.hello.util.HttpResponse;

@RestController
@RequestMapping("/api/v1")
public class AnnouncementController {

	@Autowired
	private AnnouncementServiceImpl announcementServiceImpl;

	@PostMapping("/announcements")
	private ResponseEntity<ResponseBody> create(@RequestBody @Valid Announcement announcement) {
		try {
			return HttpResponse.ok("Comunicado criado com sucesso", announcementServiceImpl.create(announcement));
		} catch (Exception error) {
			switch (error.getMessage()) {
			case Exceptions.USER_NOT_FOUND:
				return HttpResponse.notFound(Exceptions.USER_NOT_FOUND);
			default:
				return HttpResponse.serverError();
			}
		}

	}

	@GetMapping("/announcements")
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
	
	@GetMapping("/announcements/{id}")
	public ResponseEntity<ResponseBody> getById(@PathVariable int id) {
		try {
			return HttpResponse.ok("Comunicado listado com sucesso", announcementServiceImpl.getById(id));
		} catch (Exception error) {
			switch (error.getMessage()) {
			case Exceptions.ANNOUNCEMENT_NOT_FOUND:
				return HttpResponse.notFound(Exceptions.ANNOUNCEMENT_NOT_FOUND);
			default:
				return HttpResponse.serverError();
			}
		}
	}
}
