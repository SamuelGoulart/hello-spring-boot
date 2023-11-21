package br.com.hello.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.hello.domain.User;

public interface AuthRepository extends JpaRepository<User, Integer> {

	 @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
	  Optional<User> findByEmailAndPassword(String email, String password);
}
