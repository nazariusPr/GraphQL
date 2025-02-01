package com.example.demo.mapper;

import com.example.demo.dto.CreateEventDto;
import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
  Event toEvent(CreateEventDto createEventDto);

  EventDto toEventDto(Event event);
}
