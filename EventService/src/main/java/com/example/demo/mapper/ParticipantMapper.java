package com.example.demo.mapper;

import com.example.demo.dto.ParticipantDto;
import com.example.demo.model.Participant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
  ParticipantDto toParticipantDto(Participant participant);
}
