package com.example.events.services;

import com.example.events.dto.EventDto;
import com.example.events.repositories.EventAttendeesRepository;
import com.example.events.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final UserService userService;

    private final EventRepository eventRepository;
    private final EventAttendeesRepository eventAttendeesRepository;

    public List<EventDto> getEvents() { return eventRepository.getEvents(); }

    public void registerForEvent(Long eventId) {
        Long userId = userService.getCurrentUserId();
        eventAttendeesRepository.registerForEvent(eventId, userId);
    }

    public void unregisterFromEvent(Long eventId) {
        Long userId = userService.getCurrentUserId();
        eventAttendeesRepository.unregisterFromEvent(eventId, userId);
    }

    public List<EventDto> getRegisteredEvents() {
        Long userId = userService.getCurrentUserId();
        return eventRepository.getRegisteredEvents(userId);
    }

    public boolean isRegisteredToEvent(Long eventId) {
        Long userId = userService.getCurrentUserId();
        List<EventDto> eventDtos = eventRepository.isRegisteredToEvent(userId, eventId);
        if(eventDtos.size()>0){
            return true;
        } else {
            return false;
        }
    }
}
