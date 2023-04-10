package br.com.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hello.domain.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

}
