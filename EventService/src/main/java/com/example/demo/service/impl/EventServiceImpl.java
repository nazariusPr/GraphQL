package com.example.demo.service.impl;

import com.example.demo.dto.CreateEventDto;
import com.example.demo.dto.EventDto;
import com.example.demo.mapper.EventMapper;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
  private final EventRepository repository;
  private final EventMapper mapper;

  @Override
  public Event find(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Event with ID " + id + " not found"));
  }

  @Override
  public Event find(String title) {
    return repository
        .findByTitle(title)
        .orElseThrow(() -> new EntityNotFoundException("Event with title " + title + " not found"));
  }

  @Override
  public EventDto create(CreateEventDto createEventDto) {
    Event event = mapper.toEvent(createEventDto);
    Event savedEvent = repository.save(event);

    return mapper.toEventDto(savedEvent);
  }

  @Override
  public EventDto read(String title) {
    Event event = find(title);
    return mapper.toEventDto(event);
  }

  @Override
  public EventDto read(UUID id) {
    Event event = find(id);
    return mapper.toEventDto(event);
  }

  @Override
  public void delete(UUID id) {
    Event event = find(id);
    repository.delete(event);
  }
}
