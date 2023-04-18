package br.com.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hello.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
