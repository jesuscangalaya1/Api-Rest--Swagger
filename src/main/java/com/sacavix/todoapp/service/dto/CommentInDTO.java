package com.sacavix.todoapp.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CommentInDTO {

    private String text;
    private String author;
}
