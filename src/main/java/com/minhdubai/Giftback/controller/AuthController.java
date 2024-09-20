package com.minhdubai.Giftback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.TokenVerifier.VerificationException;
import com.minhdubai.Giftback.domain.dto.auth.RegisterDto;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
   @Autowired
   private AuthService authService;

   @PostMapping("/register")
   public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterDto userInfo) {
      ResponseDto response = authService.register(userInfo);
      return ResponseEntity.status(response.getStatus()).body(response);
   }

   @PostMapping("/login")
   public ResponseEntity<ResponseDto> login(@RequestBody User user) {
      ResponseDto response = authService.login(user.getUsername(), user.getPassword());
      return ResponseEntity.status(response.getStatus()).body(response);
   }

   @DeleteMapping("/logout")
   public ResponseEntity<ResponseDto> logout(HttpServletRequest request) {
      String authHeader = request.getHeader("Authorization");
      String token = authHeader.substring(7);

      ResponseDto response = authService.logout(token);
      return ResponseEntity.status(response.getStatus()).body(response);
   }

   @PostMapping("/oauth2")
   public ResponseEntity<ResponseDto> oauthLogin(@RequestBody String token) throws VerificationException {
      ResponseDto response = authService.oauthLogin(token);
      return ResponseEntity.status(response.getStatus()).body(response);
   }
}
