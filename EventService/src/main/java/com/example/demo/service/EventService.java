package com.example.demo.service;

import com.example.demo.dto.CreateEventDto;
import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;
import java.util.UUID;

public interface EventService {
  Event find(UUID id);

  Event find(String title);

  EventDto create(CreateEventDto createEventDto);

  EventDto read(String title);

  EventDto read(UUID id);

  void delete(UUID id);
}
