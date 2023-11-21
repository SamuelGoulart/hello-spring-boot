package br.com.hello.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hello.domain.Auth;
import br.com.hello.domain.ResponseBody;
import br.com.hello.exception.Exceptions;
import br.com.hello.service.impl.AuthServiceImpl;
import br.com.hello.util.HttpResponse;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

	@Autowired
	private AuthServiceImpl authServiceImpl;

	@PostMapping("/auth")
	public ResponseEntity<ResponseBody> auth(@RequestBody @Valid Auth body) {
		try {
			String email = body.getEmail();
			String password = body.getPassword();
						
			return HttpResponse.ok("Autenticação realizada com sucesso!", authServiceImpl.auth(email, password));
		} catch (Exception error) {
			System.out.println(error);
			switch (error.getMessage()) {
			case Exceptions.UNAUTHORIZED:
				return HttpResponse.unauthorized(Exceptions.UNAUTHORIZED);
			default:
				return HttpResponse.serverError();
			}
		}
	}

}
