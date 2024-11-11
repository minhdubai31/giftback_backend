package com.minhdubai.Giftback.service;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.auth.oauth2.TokenVerifier;
import com.google.auth.oauth2.TokenVerifier.VerificationException;
import com.minhdubai.Giftback.domain.constant.AuthProvider;
import com.minhdubai.Giftback.domain.dto.UserDto;
import com.minhdubai.Giftback.domain.dto.auth.RegisterDto;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import com.minhdubai.Giftback.domain.entity.BlacklistToken;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.BlacklistTokenRepository;
import com.minhdubai.Giftback.repository.UserRepository;
import com.minhdubai.Giftback.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
      private final UserRepository userRepository;
      private final BlacklistTokenRepository blacklistTokenRepository;
      private final JwtUtil jwtUtil;
      private final PasswordEncoder passwordEncoder;
      private final Mapper<User, UserDto> userMapper;

      @Value("${oauth2.google.client-id}")
      private String googleClientId;

      public ResponseDto register(RegisterDto userInfo) {
            User newUser = User.builder()
                        .name(userInfo.getName())
                        .username(userInfo.getUsername())
                        .password(passwordEncoder.encode(userInfo.getPassword()))
                        .build();

            userRepository.save(newUser);

            ResponseDto response = ResponseDto.builder()
                        .status(201)
                        .message("User created successfully")
                        .data(userMapper.mapTo(newUser))
                        .build();

            return response;
      }

      public ResponseDto login(String username, String password) {
            User user = userRepository.findByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                  String token = jwtUtil.generateToken(username);

                  ResponseDto response = ResponseDto.builder()
                              .status(200)
                              .message("Accepted")
                              .data(Map.of("token", token))
                              .build();

                  return response;
            }

            ResponseDto response = ResponseDto.builder()
                        .status(403)
                        .message("Email or password is invalid")
                        .build();
            return response;
      }

      public ResponseDto logout(String token) {
            BlacklistToken blacklistToken = BlacklistToken.builder()
                        .token(token)
                        .build();

            blacklistTokenRepository.save(blacklistToken);
            SecurityContextHolder.clearContext();

            ResponseDto response = ResponseDto.builder()
                        .status(200)
                        .message("Logged out successfully")
                        .build();

            return response;
      }

      public ResponseDto oauthLogin(String idToken) throws VerificationException {
            TokenVerifier verifier = TokenVerifier.newBuilder()
                        .setAudience(googleClientId)
                        .build();

            JsonWebSignature signature = verifier.verify(idToken);
            System.out.println(signature);
            var payload = signature.getPayload();
            String username = AuthProvider.GOOGLE.getPrefix() + (String) payload.get("email");
            User user = userRepository.findByUsername(username);

            if (user == null) {
                  user = User.builder()
                              .name((String) payload.get("name"))
                              .username(username)
                              .password(passwordEncoder.encode(RandomStringUtils.secureStrong().toString()))
                              .authProvider(AuthProvider.GOOGLE)
                              .build();

                  userRepository.save(user);
            }

            String token = jwtUtil.generateToken(username);

            ResponseDto response = ResponseDto.builder()
                        .status(200)
                        .message("Accepted")
                        .data(Map.of("token", token, "userData", userMapper.mapTo(user)))
                        .build();

            return response;
      }
}
