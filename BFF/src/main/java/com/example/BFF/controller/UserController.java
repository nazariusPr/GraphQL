package com.example.BFF.controller;

import com.example.BFF.dto.AccessTokenDto;
import com.example.BFF.dto.AuthDto;
import com.example.BFF.dto.EventDto;
import com.example.BFF.dto.ParticipantDto;
import com.example.BFF.dto.RegisterDto;
import com.example.BFF.dto.UserDto;
import com.example.BFF.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @MutationMapping
  public AccessTokenDto auth(@Argument AuthDto auth) {
    log.info("**/ Authentication request received for username: {}", auth.getIdentifier());
    return userService.auth(auth);
  }

  @MutationMapping
  public AccessTokenDto register(@Argument RegisterDto register) {
    log.info("**/ Registration request received for username: {}", register.getUsername());
    return userService.register(register);
  }

  @QueryMapping
  public UserDto userByUsername(@Argument String username) {
    log.info("**/ Request to fetch user by username: {}", username);
    return userService.findByUsername(username);
  }

  @SchemaMapping(typeName = "Participant", field = "user")
  public UserDto resolveUser(ParticipantDto participant) {
    log.info("**/ Resolving user for participant with ID: {}", participant.getUserId());
    return userService.findById(participant.getUserId());
  }

  @SchemaMapping(typeName = "Event", field = "owner")
  public UserDto resolveUser(EventDto eventDto) {
    log.info("**/ Resolving user for event with ID: {}", eventDto.getOwnerId());
    return userService.findById(eventDto.getOwnerId());
  }
}
