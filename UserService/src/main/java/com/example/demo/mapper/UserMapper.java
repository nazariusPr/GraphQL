package com.example.demo.mapper;

import com.example.demo.dto.DetailUserDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User registerDtoToUser(RegisterDto registerDto);

  DetailUserDto userToDetailUserDto(User user);
}
