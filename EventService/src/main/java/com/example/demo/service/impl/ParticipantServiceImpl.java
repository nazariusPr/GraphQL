package com.example.demo.service.impl;

import com.example.demo.dto.CreateParticipantDto;
import com.example.demo.dto.ParticipantDto;
import com.example.demo.mapper.ParticipantMapper;
import com.example.demo.model.Event;
import com.example.demo.model.Participant;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.service.EventService;
import com.example.demo.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
  private final EventService eventService;
  private final ParticipantRepository repository;
  private final ParticipantMapper mapper;

  @Override
  public ParticipantDto create(CreateParticipantDto createParticipantDto) {
    Event event = eventService.find(createParticipantDto.getEventId());

    Participant participant = new Participant();
    participant.setUserId(createParticipantDto.getUserId());
    participant.setEvent(event);

    Participant savedParticipant = repository.save(participant);
    return mapper.toParticipantDto(savedParticipant);
  }

  @Override
  public ParticipantDto read(UUID id) {
    Participant participant = find(id);
    return mapper.toParticipantDto(participant);
  }

  @Override
  public void delete(UUID id) {
    Participant participant = find(id);
    repository.delete(participant);
  }

  private Participant find(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Participant not found"));
  }
}
