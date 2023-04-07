package br.com.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hello.domain.User;

public interface UserRepository  extends JpaRepository<User, Integer> {

}
