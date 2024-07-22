package com.example.taskmanager.service;



import com.example.taskmanager.converter.UserConverter;
import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.entity.User;

import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;




    public User createUser(UserDTO userDTO) {
        User user = userConverter.convertUserDtoToEntity(userDTO);
        return userRepository.save(user);
    }




    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return userConverter.entityToDto(user);
        }
         return null;
    }


    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }



    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword); // Directly updating the password
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }


    public UserDTO entityToDto(User user) {
        return userConverter.entityToDto(user);
    }




}
