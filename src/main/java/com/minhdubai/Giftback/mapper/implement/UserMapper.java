package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.user.UserDto;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

   private final ModelMapper modelMapper;

   @Override
   public UserDto mapTo(User user) {
      return modelMapper.map(user, UserDto.class);
   }

   @Override
   public User mapFrom(UserDto userDto) {
      return modelMapper.map(userDto, User.class);
   }
}
