package com.projects.learning.dto;

import com.projects.learning.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType transactionType;
    private String category;
}
