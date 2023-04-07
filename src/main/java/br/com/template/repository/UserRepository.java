package br.com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.template.domain.User;

public interface UserRepository  extends JpaRepository<User, Long> {

}
