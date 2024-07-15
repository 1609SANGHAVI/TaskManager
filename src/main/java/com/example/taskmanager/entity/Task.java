package com.example.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name ="TaskTable")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
//    @Column
//    private boolean completed;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


}

