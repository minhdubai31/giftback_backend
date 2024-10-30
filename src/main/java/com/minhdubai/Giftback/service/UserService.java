package com.minhdubai.Giftback.service;

import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.constant.Role;
import com.minhdubai.Giftback.domain.dto.UserDto;
import com.minhdubai.Giftback.domain.dto.WalletDto;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.domain.entity.Wallet;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.GroupRepository;
import com.minhdubai.Giftback.repository.UserRepository;
import com.minhdubai.Giftback.repository.WalletRepository;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
   private UserRepository userRepository;
   private GroupRepository groupRepository;
   private WalletRepository walletRepository;
   private Mapper<User, UserDto> userMapper;
   private Mapper<Wallet, WalletDto> walletMapper;

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
      userDto.setRole(Role.USER);
      User user = userMapper.mapFrom(userDto);
      // Check if the group is not null and is transient
      if (user.getGroup() != null && user.getGroup().getId() == null) {
         // Save the group first if it's a new transient instance
         user.setGroup(groupRepository.save(user.getGroup()));
      }

      if (user.getWallet() != null && user.getWallet().getId() == null) {
         // Save the wallet first if it's a new transient instance
         user.setWallet(walletRepository.save(user.getWallet()));
      }

      // Save the user first to ensure the wallet can reference it
      User savedUser = userRepository.save(user);

      return ResponseDto.builder()
            .status(201)
            .message("User created successfully")
            .data(userMapper.mapTo(savedUser))
            .build();
   }

   public ResponseDto update(Integer id, UserDto userDto) {
      User existingUser = userRepository.findById(id).orElse(null);
      if (existingUser != null) {
         existingUser.setUsername(userDto.getUsername());
         existingUser.setName(userDto.getName());
         existingUser.setRole(userDto.getRole());
         existingUser.setWallet(walletMapper.mapFrom(userDto.getWallet()));
         User updatedUser = userRepository.save(existingUser);
         return ResponseDto.builder()
               .status(200)
               .message("User updated successfully")
               .data(userMapper.mapTo(updatedUser))
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("User not found")
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
