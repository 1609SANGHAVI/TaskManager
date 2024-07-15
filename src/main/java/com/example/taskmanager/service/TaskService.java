package com.example.taskmanager.service;


import com.example.taskmanager.converter.TaskConverter;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskConverter taskConverter;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();

    }

    public List<Task> getAllTasksByUserId(Integer userId) {
        return taskRepository.findByUserId(userId);
    }

//   public Task createTask(TaskDTO taskDTO){
//        Task task=TaskConverter.convertTaskDTOToEntity(taskDTO);
//        return taskRepository.save(task);
//
//    }
//
//    public void createTask(TaskDTO taskDTO) {
//        User user = userRepository.findById(taskDTO.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//        Task task = taskConverter.convertTaskDTOToEntity(taskDTO, user);
//        taskRepository.save(task);
//    }
//
    public TaskDTO createTask(TaskDTO taskDTO) {
        User user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Task task = taskConverter.convertTaskDTOToEntity(taskDTO, user);
        Task savedTask = taskRepository.save(task);
        return taskConverter.convertEntityToTaskDTO(savedTask);
    }




    public Task updateTask(Integer id, TaskDTO taskDTO) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
//            task.setCompleted(taskDTO.isCompleted());
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found");
        }

    }

     public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
}







}
