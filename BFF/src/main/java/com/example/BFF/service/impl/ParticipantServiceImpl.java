package com.example.BFF.service.impl;

import com.example.BFF.dto.CreateParticipantDto;
import com.example.BFF.dto.ParticipantDto;
import com.example.BFF.service.ParticipantService;
import com.example.BFF.util.RequestUtils;
import com.example.BFF.util.SecurityUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
  private final RequestUtils requestUtils;
  private final SecurityUtils securityUtils;

  @Value("${service.event-service}")
  private String EVENT_SERVICE_URL;

  @Override
  public ParticipantDto createParticipant(CreateParticipantDto createParticipantDto) {
    UUID userId = securityUtils.getUserId();
    createParticipantDto.setUserId(userId);

    return requestUtils.postRequest(
        EVENT_SERVICE_URL + "/participants", createParticipantDto, ParticipantDto.class);
  }

  @Override
  public ParticipantDto findById(UUID id) {
    return requestUtils.getRequest(
        EVENT_SERVICE_URL + "/participants/{id}", ParticipantDto.class, id);
  }
}
