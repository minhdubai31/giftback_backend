package com.minhdubai.Giftback.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
      List<Map<String, Object>> listErrors = ex.getBindingResult()
         .getFieldErrors()
         .stream()
         .map(x -> {
            Map<String, Object> error = new HashMap<>();
            error.put("field", x.getField());
            error.put("message", x.getDefaultMessage());
            return error;
         })
         .collect(Collectors.toList());

      ResponseDto response = ResponseDto.builder()
            .status(400)
            .message("Invalid data")
            .data(listErrors)
            .build();
      return ResponseEntity.status(response.getStatus()).body(response);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ResponseDto> handleException(Exception ex) {
      ResponseDto response = ResponseDto.builder()
            .status(400)
            .message(ex.getMessage())
            .data(ex)
            .build();

      return ResponseEntity.status(response.getStatus()).body(response);
   }
}
