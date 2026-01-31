package com.projects.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorMessageDTO {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;

}
