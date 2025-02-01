package com.example.BFF.service.impl;

import com.example.BFF.dto.CreateEventDto;
import com.example.BFF.dto.EventDto;
import com.example.BFF.service.EventService;
import com.example.BFF.util.RequestUtils;
import com.example.BFF.util.SecurityUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
  private final RequestUtils requestUtils;
  private final SecurityUtils securityUtils;

  @Value("${service.event-service}")
  private String EVENT_SERVICE_URL;

  @Override
  public EventDto findByTitle(String title) {
    return requestUtils.getRequest(
        EVENT_SERVICE_URL + "/events/title/{title}", EventDto.class, title);
  }

  @Override
  public EventDto createEvent(CreateEventDto createEventDto) {
    UUID userId = securityUtils.getUserId();
    createEventDto.setOwnerId(userId);

    return requestUtils.postRequest(EVENT_SERVICE_URL + "/events", createEventDto, EventDto.class);
  }
}
