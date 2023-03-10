package com.sacavix.todoapp.mapper;

import com.sacavix.todoapp.persistence.entity.Comment;
import com.sacavix.todoapp.service.dto.CommentInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MapperComment implements IMapper<CommentInDTO, Comment> {

    @Override
    public Comment mapearEntidad(CommentInDTO commentInDTO) {
        return Comment.builder()
                .text(commentInDTO.getText())
                .creationDate(LocalDateTime.now())
                .author(commentInDTO.getAuthor())
                .build();
    }

    @Override
    public CommentInDTO mapearDto(Comment comment) {
        return CommentInDTO.builder()
                .text(comment.getText())
                .author(comment.getAuthor())
                .build();
    }
}
