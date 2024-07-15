package com.example.taskmanager.dto;

import lombok.*;



@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;


}
