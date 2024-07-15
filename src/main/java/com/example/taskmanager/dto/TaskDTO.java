package com.example.taskmanager.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer userId;
//    private boolean completed;


}
