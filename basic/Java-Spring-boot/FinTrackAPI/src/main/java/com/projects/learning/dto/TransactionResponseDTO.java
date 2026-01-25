package com.projects.learning.dto;

import com.projects.learning.domain.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionResponseDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType transactionType;
    private String category;
}
