package br.com.hello.service;

import java.util.List;

import br.com.hello.domain.User;

public interface UserService {
    User getById(int id);
    
    List<User> getAll();
    
    User create(User user);
    
    void delete(int id);

}
