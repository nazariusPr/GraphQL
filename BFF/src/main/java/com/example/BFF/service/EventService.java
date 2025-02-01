package com.example.BFF.service;

import com.example.BFF.dto.CreateEventDto;
import com.example.BFF.dto.EventDto;

public interface EventService {
  EventDto findByTitle(String title);

  EventDto createEvent(CreateEventDto createEventDto);
}
