package com.sacavix.todoapp.service;

import com.sacavix.todoapp.mapper.MapperComment;
import com.sacavix.todoapp.persistence.entity.Comment;
import com.sacavix.todoapp.persistence.entity.Task;
import com.sacavix.todoapp.persistence.repository.CommentRepository;
import com.sacavix.todoapp.persistence.repository.TaskRepository;
import com.sacavix.todoapp.service.dto.CommentInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MapperComment mapper;
    private final TaskRepository taskRepository;

    public CommentInDTO createComment (Long taskId, CommentInDTO commentInDTO){
        Comment comment = mapper.mapearEntidad(commentInDTO);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + taskId+ " not found"));

        comment.setTask(task);
        Comment newComment = commentRepository.save(comment);
        task.getComments().add(newComment);
        return mapper.mapearDto(newComment);
    }

    public CommentInDTO updateComment(Long taskId, Long commentID, CommentInDTO commentInDTO){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + taskId+ " not found"));
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> new NoSuchElementException("Comment with id " + commentID+ " not found"));

        if (!comment.getTask().getId().equals(task.getId()))
            throw new NoSuchElementException("El comentario no pertenece! al task"+ taskId+ "Bad_Request" );

    comment.setText(commentInDTO.getText());
        comment.setAuthor(commentInDTO.getAuthor());

        Comment commentUpdate = commentRepository.save(comment);
        return mapper.mapearDto(commentUpdate);
    }

    public List<Comment> getCommentForTaskId(Long taskId){
        return commentRepository.findByTaskId(taskId);

    }

    public Comment getCommentForId (Long taskId, Long commentId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + taskId+ " not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment with id " + commentId+ " not found"));

        if (!comment.getTask().getId().equals(task.getId()))
            throw new NoSuchElementException("El comentario no pertenece! al task"+ taskId+ "Bad_Request" );

        return comment;
    }

    public void deleteComment (Long taskId, Long commentId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + taskId+ " not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment with id " + commentId+ " not found"));

        if (!comment.getTask().getId().equals(task.getId()))
            throw new NoSuchElementException("El comentario no pertenece! al task"+ taskId+ "Bad_Request" );

        commentRepository.delete(comment);
    }


}
