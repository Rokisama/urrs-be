package com.example.events.services;

import com.example.events.dto.EventDto;
import com.example.events.dto.EventRequestDto;
import com.example.events.model.EventType;
import com.example.events.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final UserService userService;
    private final EventRepository eventRepository;

    public List<EventDto> getEvents() { return eventRepository.getEvents(); }

    public List<EventDto> getEventsByFilter(EventRequestDto requestDto) {
        // Parse eventDate as LocalDate (ignoring the time)
        LocalDate parsedDate = requestDto.getEventDate() != null
                ? LocalDate.parse(requestDto.getEventDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : null;

        return eventRepository.getEventsByFilter(
                requestDto.getTitle(),
                parsedDate,
                requestDto.getEventType()
        );
    }
    public void deleteEvent(Long eventId) { eventRepository.deleteEvent(eventId); }

    public void updateEvent(Long eventId,EventRequestDto eventRequestDto) {
        Timestamp eventDate = Timestamp.valueOf(eventRequestDto.getEventDate());
        eventRepository.updateEvent(eventId, eventRequestDto.getTitle(), eventRequestDto.getDescription(), eventRequestDto.getLocation(), eventRequestDto.getEventType(), eventDate);
    }

    public void createEvent(EventRequestDto eventRequestDto) {
        Timestamp createdOn = Timestamp.from(Instant.now());
        Timestamp eventDate = Timestamp.valueOf(eventRequestDto.getEventDate());
        int userId = userService.getCurrentUserId().intValue();
        EventDto eventDto = EventDto.builder()
                .title(eventRequestDto.getTitle())
                .description(eventRequestDto.getDescription())
                .location(eventRequestDto.getLocation())
                .eventType(eventRequestDto.getEventType())
                .eventDate(eventDate)
                .createdOn(createdOn)
                .userId(userId)
                .build();
        eventRepository.createEvent(eventDto);
    }


    public List<EventDto> getRegisteredEvents() {
        Long userId = userService.getCurrentUserId();
        return eventRepository.getRegisteredEvents(userId);
    }

    public List<EventType> getEventTypes() {
        return Arrays.stream(EventType.values()).toList();
    }
}
