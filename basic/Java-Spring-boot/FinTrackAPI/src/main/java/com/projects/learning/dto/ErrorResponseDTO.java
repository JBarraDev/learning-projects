package com.projects.learning.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO (
    int status,
    String message,
    LocalDateTime timestamp
){}
