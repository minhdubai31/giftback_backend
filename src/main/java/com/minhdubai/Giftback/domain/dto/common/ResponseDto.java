package com.minhdubai.Giftback.domain.dto.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto {
   private Integer status;
   private String message;

   @Builder.Default
   private Object data = null;
}