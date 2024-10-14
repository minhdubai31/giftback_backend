package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.UserDto;
import com.minhdubai.Giftback.service.UserService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {
        ResponseDto response = userService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(userDto -> ResponseEntity.ok(userDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody UserDto userDto) {
        ResponseDto response = userService.create(userDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody UserDto userDto) {
        ResponseDto response = userService.update(userDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer id) {
        ResponseDto response = userService.deleteById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllUsers() {
        ResponseDto response = userService.getAllUsers();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
