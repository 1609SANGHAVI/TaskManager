package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

//
//    @PostMapping("/create")
//    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO) {
//        try {
//            taskService.createTask(taskDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
//        if (taskDTO.getDescription() == null || taskDTO.getDescription().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Description cannot be null or empty");
//        }
//        try {
//            TaskDTO createdTask = taskService.createTask(taskDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Task created successfully", "task", createdTask));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            TaskDTO createdTask = taskService.createTask(taskDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Task created successfully", "task", createdTask));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @GetMapping("/get")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks=taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }


    @GetMapping("/user/{userId}")
     public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Integer userId) {
        List<Task> task = taskService.getAllTasksByUserId(userId);

         if (!task.isEmpty()) {
           return ResponseEntity.ok(task);
         } else {
            return ResponseEntity.notFound().build(); }



     }

      @PutMapping("/update/{id}")
            public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
                   Task updatedTask = taskService.updateTask(id,taskDTO);
                   if (updatedTask != null) {
                    return ResponseEntity.ok(updatedTask); // Return the updated task
                   }
                   else {
                       return ResponseEntity.notFound().build();
                   }


      }
//@PutMapping("/{id}")
//public ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
//    try {
//        Task updatedTask = taskService.updateTask(id, taskDTO);
//        return ResponseEntity.ok(updatedTask);
//    } catch (IllegalArgumentException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//}


    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
