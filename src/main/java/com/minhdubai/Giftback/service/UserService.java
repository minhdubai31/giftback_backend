package com.minhdubai.Giftback.service;

import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.dto.UserDto;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.UserRepository;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
   private UserRepository userRepository;
   private Mapper<User, UserDto> userMapper;

   public ResponseDto findById(Integer userId) {
      User foundedUser = userRepository.findById(userId).orElse(null);
      if (foundedUser != null) {
         return ResponseDto.builder()
                 .status(200)
                 .message("User found")
                 .data(userMapper.mapTo(foundedUser))
                 .build();
      }
      return ResponseDto.builder()
              .status(404)
              .message("User not found")
              .build();
   }

   public Optional<UserDto> findByUsername(String username) {
      User foundedUser = userRepository.findByUsername(username);
      return foundedUser != null ? Optional.of(userMapper.mapTo(foundedUser)) : Optional.empty();
   }

   public ResponseDto create(UserDto userDto) {
      User user = userMapper.mapFrom(userDto);
      User savedUser = userRepository.save(user);
      return ResponseDto.builder()
              .status(201)
              .message("User created successfully")
              .data(userMapper.mapTo(savedUser))
              .build();
   }

   public ResponseDto update(UserDto userDto) {
      User user = userMapper.mapFrom(userDto);
      User updatedUser = userRepository.save(user);
      return ResponseDto.builder()
              .status(200)
              .message("User updated successfully")
              .data(userMapper.mapTo(updatedUser))
              .build();
   }

   public ResponseDto deleteById(Integer userId) {
      userRepository.deleteById(userId);
      return ResponseDto.builder()
              .status(200)
              .message("User deleted successfully")
              .build();
   }

   public ResponseDto getAllUsers() {
      List<User> users = userRepository.findAll();
      List<UserDto> userDtos = users.stream()
              .map(userMapper::mapTo)
              .toList();
      return ResponseDto.builder()
              .status(200)
              .message("Users retrieved successfully")
              .data(userDtos)
              .build();
   }
}
