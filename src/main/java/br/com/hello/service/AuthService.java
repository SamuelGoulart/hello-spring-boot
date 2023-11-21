package br.com.hello.service;

import br.com.hello.domain.User;

public interface AuthService {
    User auth(String email, String password);

}
