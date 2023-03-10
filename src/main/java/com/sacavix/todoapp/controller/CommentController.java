package com.sacavix.todoapp.controller;

import com.sacavix.todoapp.persistence.entity.Comment;
import com.sacavix.todoapp.service.CommentService;
import com.sacavix.todoapp.service.dto.CommentInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/task/{taskId}/comments")
    public ResponseEntity<CommentInDTO> createComment(@PathVariable Long taskId, @RequestBody CommentInDTO commentInDTO){
        return new ResponseEntity<>(commentService.createComment(taskId, commentInDTO), HttpStatus.CREATED);
    }
    @PutMapping("/task/{taskId}/coment/{id}")
    public ResponseEntity<CommentInDTO> updateComment(@PathVariable Long taskId, @PathVariable Long id, @RequestBody CommentInDTO commentInDTO){
        CommentInDTO commentUpdated = commentService.updateComment(taskId,id, commentInDTO);
        return new ResponseEntity<>(commentUpdated, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}/comments")
    public List<Comment> listCommentsForTaskId (@PathVariable Long taskId){
        return commentService.getCommentForTaskId(taskId);
    }

    @GetMapping("/tasks/{taskId}/comments/{id}")
    public ResponseEntity<Comment> getCommentsForId(@PathVariable Long taskId, @PathVariable Long id){
        Comment comment = commentService.getCommentForId(taskId,id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/task/{taskId}/comments{id}")
    public ResponseEntity<String> deleteComment (@PathVariable Long taskId, @PathVariable Long id){
        commentService.deleteComment(taskId,id);
        return new ResponseEntity<>("Comment Deleted with exit !!",HttpStatus.OK);
    }

}
