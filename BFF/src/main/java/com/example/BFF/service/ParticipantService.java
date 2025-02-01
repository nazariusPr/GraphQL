package com.example.BFF.service;

import com.example.BFF.dto.CreateParticipantDto;
import com.example.BFF.dto.ParticipantDto;
import java.util.UUID;

public interface ParticipantService {
  ParticipantDto createParticipant(CreateParticipantDto createParticipantDto);

  ParticipantDto findById(UUID id);
}
