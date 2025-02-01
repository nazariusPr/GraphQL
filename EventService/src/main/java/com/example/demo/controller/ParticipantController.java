package com.example.demo.controller;

import com.example.demo.dto.CreateParticipantDto;
import com.example.demo.dto.ParticipantDto;
import com.example.demo.service.ParticipantService;
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
@RequestMapping("/participants")
@AllArgsConstructor
public class ParticipantController {

  private final ParticipantService participantService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ParticipantDto createParticipant(
      @Valid @RequestBody CreateParticipantDto createParticipantDto) {
    log.info("**/ Create participant request received");
    return participantService.create(createParticipantDto);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ParticipantDto getParticipantById(@PathVariable UUID id) {
    log.info("**/ Request to fetch participant by ID: {}", id);
    return participantService.read(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteParticipant(@PathVariable UUID id) {
    log.info("**/ Request to delete participant with ID: {}", id);
    participantService.delete(id);
  }
}
