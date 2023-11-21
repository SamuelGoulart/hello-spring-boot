package br.com.hello.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hello.domain.User;
import br.com.hello.exception.Exceptions;
import br.com.hello.exception.GenericException;
import br.com.hello.repository.AuthRepository;
import br.com.hello.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService  {
	@Autowired
	AuthRepository authRepository;
	
	public User auth(String email, String password) {
		Optional<User> optionalUser = authRepository.findByEmailAndPassword(email, password);
		
		System.out.println(!optionalUser.isPresent());

		
		if (!optionalUser.isPresent()) {
			throw new GenericException(Exceptions.UNAUTHORIZED);
		}

		return optionalUser.get();
	}
}
