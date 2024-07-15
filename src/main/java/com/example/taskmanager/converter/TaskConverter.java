package com.example.taskmanager.converter;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import org.springframework.stereotype.Component;


@Component
public class TaskConverter {


   public Task convertTaskDTOToEntity(TaskDTO taskDTO, User user) {
       Task task = new Task();
       task.setTitle(taskDTO.getTitle());
       task.setDescription(taskDTO.getDescription());
       task.setUser(user);
//       task.setCompleted(taskDTO.isCompleted());
       return task;

   }
    public TaskDTO convertEntityToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setUserId(task.getUser().getId());
        return taskDTO;
    }





}
