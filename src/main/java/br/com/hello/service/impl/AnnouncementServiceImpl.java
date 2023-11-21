package br.com.hello.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.hello.domain.Announcement;
import br.com.hello.domain.AnnouncementDto;
import br.com.hello.domain.User;
import br.com.hello.exception.Exceptions;
import br.com.hello.exception.GenericException;
import br.com.hello.repository.AnnouncementRepository;
import br.com.hello.repository.UserRepository;
import br.com.hello.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AnnouncementRepository announcementRepository;

	public Announcement create(Announcement announcement) {
		int userId = announcement.getUserId();

		Optional<User> optionalUser = userRepository.findById(userId);

		if (!optionalUser.isPresent()) {
			throw new GenericException(Exceptions.USER_NOT_FOUND);
		}
		
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        String formattedDate = dateFormat.format(currentDate);
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormat.format(currentDate);
        

        announcement.setDate(formattedDate);
        announcement.setTime(formattedTime);
        
		User user = optionalUser.get();
		announcement.setUser(user);

		return announcementRepository.save(announcement);
	}

	public List<AnnouncementDto> getAll() {
		List<Announcement> announcements = announcementRepository.findAll();

		if (announcements.isEmpty()) {
			throw new GenericException(Exceptions.ANNOUNCEMENT_NOT_FOUND);
		}
		
		  return announcements.stream()
	                .map(AnnouncementDto::fromEntity)
	                .collect(Collectors.toList());
	}
	
	public AnnouncementDto getById(int id) throws JsonProcessingException {
		Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);

		if (!optionalAnnouncement .isPresent()) {
			throw new GenericException(Exceptions.ANNOUNCEMENT_NOT_FOUND);
		}
		
	    Announcement announcement = optionalAnnouncement.get();
	    
	    return AnnouncementDto.fromEntity(announcement);
	}
}
