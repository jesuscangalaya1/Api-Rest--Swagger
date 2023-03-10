package com.sacavix.todoapp.mapper;

import com.sacavix.todoapp.persistence.entity.Task;
import com.sacavix.todoapp.persistence.entity.TaskStatus;
import com.sacavix.todoapp.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task> {

    //Convierte DTO a Entidad.
    @Override
    public Task mapearEntidad(TaskInDTO taskInDTO) {
        Task task = new Task();
        task.setTitle(taskInDTO.getTitle());
        task.setDescription(taskInDTO.getDescription());
        task.setEta(taskInDTO.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }

    //Convierte Entidad a DTO.
    @Override
    public TaskInDTO mapearDto(Task task) {
        TaskInDTO taskInDTO = new TaskInDTO();
        taskInDTO.setTitle(task.getTitle());
        taskInDTO.setDescription(task.getDescription());
        taskInDTO.setEta(task.getEta());
        return taskInDTO;
    }


}
