package br.com.hello.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.hello.domain.Announcement;
import br.com.hello.domain.AnnouncementDto;

public interface AnnouncementService {
	   
    List<AnnouncementDto> getAll();
    
    Announcement create(Announcement announcement);
    
    AnnouncementDto getById(int id) throws JsonProcessingException;
}
