package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.notification.NotificationDto;
import com.minhdubai.Giftback.domain.entity.Notification;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NotificationMapper implements Mapper<Notification, NotificationDto> {

   private final ModelMapper modelMapper;

   @Override
   public NotificationDto mapTo(Notification notification) {
      return modelMapper.map(notification, NotificationDto.class);
   }

   @Override
   public Notification mapFrom(NotificationDto notificationDto) {
      return modelMapper.map(notificationDto, Notification.class);
   }
}
