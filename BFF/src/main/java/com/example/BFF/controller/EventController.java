package com.example.BFF.controller;

import com.example.BFF.dto.CreateEventDto;
import com.example.BFF.dto.EventDto;
import com.example.BFF.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
public class EventController {
  private final EventService eventService;

  @PreAuthorize("isAuthenticated()")
  @MutationMapping
  public EventDto createEvent(@Argument CreateEventDto createEvent) {
    log.info("**/ Create event request received with title: {}", createEvent.getTitle());
    return eventService.createEvent(createEvent);
  }

  @QueryMapping
  public EventDto eventByTitle(@Argument String title) {
    log.info("**/ Request to fetch event by title: {}", title);
    return eventService.findByTitle(title);
  }
}
