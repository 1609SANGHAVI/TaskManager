package com.example.taskmanager.converter;


import com.example.taskmanager.dto.UserDTO;
import org.springframework.stereotype.Component;
import com.example.taskmanager.entity.User;


@Component
public class UserConverter {
//    public static UserDTO convertUserEntityToDto(User user ) {
//        UserDTO userDTO=new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.getPassword(user.getPassword());
//        return userDTO;
//    }
//    public static User convertUserDTOToEntity(UserDTO userDTO){
//        User user = new User();
//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        return user;
//    }
        public UserDTO entityToDto(User user) {
              return UserDTO.builder()
                      .id(user.getId())
                      .name(user.getName())
                      .email(user.getEmail())
                      .password(user.getPassword())
                      .build();
        }

        public User convertUserDtoToEntity(UserDTO userDTO) {


            return User.builder()
                    .name(userDTO.getName())
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .build();
        }
}
