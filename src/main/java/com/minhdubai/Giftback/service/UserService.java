package com.minhdubai.Giftback.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.dto.user.UserDto;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
   private UserRepository userRepository;
   private Mapper<User, UserDto> userMapper;

   public Optional<UserDto> findById(Integer userId) {
      User foundedUser = userRepository.findById(userId).orElse(null);
      return foundedUser != null ? Optional.of(userMapper.mapTo(foundedUser)) : Optional.empty();
   }

   public Optional<UserDto> findByUsername(String username) {
      User foundedUser = userRepository.findByUsername(username);
      return foundedUser != null ? Optional.of(userMapper.mapTo(foundedUser)) : Optional.empty();
   }

   public void create(User user) {
      userRepository.save(user);
   }

   public void update(User user) {
      userRepository.save(user);
   }

   public void deleteById(Integer userId) {
      userRepository.deleteById(userId);
   }
}
