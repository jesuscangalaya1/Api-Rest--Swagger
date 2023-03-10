package com.sacavix.todoapp.service;

import com.sacavix.todoapp.exceptions.ToDoExceptions;
import com.sacavix.todoapp.mapper.TaskInDTOToTask;
import com.sacavix.todoapp.persistence.entity.Task;
import com.sacavix.todoapp.persistence.entity.TaskStatus;
import com.sacavix.todoapp.persistence.repository.TaskRepository;
import com.sacavix.todoapp.service.dto.TaskInDTO;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TaskInDTO createTask(TaskInDTO taskInDTO) {
        Task task = mapper.mapearEntidad(taskInDTO);
        Task newTask = this.repository.save(task);
        return mapper.mapearDto(newTask);
    }

    public List<Task> findAll() {
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.markTaskAsFinished(id);
    }

    public TaskInDTO updateTaskInDto(TaskInDTO taskInDTO, Long id){
        Task task = this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " not found"));
        task.setTitle(taskInDTO.getTitle());
        task.setDescription(taskInDTO.getDescription());
        task.setEta(taskInDTO.getEta());
        Task taskUpdate = this.repository.save(task);
        return mapper.mapearDto(taskUpdate);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
    }

}
