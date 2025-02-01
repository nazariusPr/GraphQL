package com.example.demo.controller;

import com.example.demo.dto.CreateEventDto;
import com.example.demo.dto.EventDto;
import com.example.demo.service.EventService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

  private final EventService eventService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EventDto createEvent(@Valid @RequestBody CreateEventDto createEventDto) {
    log.info("**/ Create event request received with title: {}", createEventDto.getTitle());
    return eventService.create(createEventDto);
  }

  @GetMapping("/title/{title}")
  @ResponseStatus(HttpStatus.OK)
  public EventDto getEventByTitle(@PathVariable String title) {
    log.info("**/ Request to fetch event by title: {}", title);
    return eventService.read(title);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEvent(@PathVariable UUID id) {
    log.info("**/ Request to delete event with ID: {}", id);
    eventService.delete(id);
  }
}
