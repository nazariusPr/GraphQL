package com.example.demo.service;

import com.example.demo.dto.CreateParticipantDto;
import com.example.demo.dto.ParticipantDto;
import java.util.UUID;

public interface ParticipantService {
  ParticipantDto create(CreateParticipantDto createParticipantDto);

  ParticipantDto read(UUID id);

  void delete(UUID id);
}
