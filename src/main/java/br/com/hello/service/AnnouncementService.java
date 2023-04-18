package br.com.hello.service;

import java.util.List;

import br.com.hello.domain.Announcement;

public interface AnnouncementService {
	   
    List<Announcement> getAll();
    
    Announcement create(Announcement announcement);
}
