package com.example.taskmanager.controller;


import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }





//    @PostMapping("/login")
//    public ResponseEntity<UserDTO> login(@RequestParam String email, @RequestParam String password) {
//        Optional<UserDTO> userDTO = userService.login(email, password);
//        return userDTO.map(ResponseEntity::ok)
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<UserDTO> login(@RequestParam String email, @RequestParam String password) {
//        UserDTO user = userService.login(email, password);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        UserDTO user = userService.login(email, password);
        if (user != null) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "user", user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }



@PutMapping("/{userId}/reset-password")
    public ResponseEntity<User> updatePassword(@PathVariable Integer userId, @RequestParam String newPassword) {
        Optional<User> user = userService.getUser(userId);
        if (user.isPresent()) {
            User updatedUser = userService.updatePassword(user.get(), newPassword);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




//    @GetMapping("/{userId}")
//    public ResponseEntity<User> getUserDetails(@PathVariable Integer userId) {
//        Optional<User> user = userService.getUser(userId);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Integer userId) {
        Optional<User> user = userService.getUser(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(userService.entityToDto(user.get()));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
