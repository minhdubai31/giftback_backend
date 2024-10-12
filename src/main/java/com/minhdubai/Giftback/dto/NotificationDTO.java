package com.minhdubai.Giftback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDTO {
    private String message;
    private Integer userId;
}
