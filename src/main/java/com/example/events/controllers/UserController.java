package com.example.events.controllers;

import com.example.events.dto.UserResponseDto;
import com.example.events.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponseDto> getUsers () {
        return userService.getUsers();
    }

    @GetMapping("/current")
    public Long getCurrentUserId () {return userService.getCurrentUserId();}
}
