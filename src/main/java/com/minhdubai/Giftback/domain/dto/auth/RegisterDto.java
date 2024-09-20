package com.minhdubai.Giftback.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {
   @NotBlank(message = "Name is required")
   @Size(min = 3, message = "Name should be at least 3 characters")
   private String name;

   @Email(message = "Invalid email")
   @NotBlank(message = "Username is required")
   private String username;

   @Size(min = 6, message = "Password should be at least 6 characters")
   @NotBlank(message = "Password is required")
   private String password;
}
