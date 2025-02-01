package com.example.BFF.controller;

import com.example.BFF.dto.CreateParticipantDto;
import com.example.BFF.dto.ParticipantDto;
import com.example.BFF.service.ParticipantService;
import java.util.UUID;
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
public class ParticipantController {
  private final ParticipantService participantService;

  @PreAuthorize("isAuthenticated()")
  @MutationMapping
  public ParticipantDto createParticipant(@Argument CreateParticipantDto createParticipant) {
    log.info("**/ Create participant request received");
    return participantService.createParticipant(createParticipant);
  }

  @QueryMapping
  public ParticipantDto participantById(@Argument UUID id) {
    log.info("**/ Request to fetch participant by ID: {}", id);
    return participantService.findById(id);
  }
}
