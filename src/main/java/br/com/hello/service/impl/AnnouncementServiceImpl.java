package br.com.hello.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hello.domain.Announcement;
import br.com.hello.exception.Exceptions;
import br.com.hello.exception.GenericException;
import br.com.hello.repository.AnnouncementRepository;
import br.com.hello.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    
	@Autowired
	AnnouncementRepository announcementRepository;
	
	public Announcement create(Announcement announcement) {
		return announcementRepository.save(announcement);
	}
	
	public List<Announcement> getAll() {
		List<Announcement> announcements = announcementRepository.findAll();
		
		if(announcements.isEmpty()) {
			throw new GenericException(Exceptions.ANNOUNCEMENT_NOT_FOUND);
		}
		
		return announcements;
	}
}
