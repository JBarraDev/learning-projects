package com.projects.learning.dto;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

public record TransactionSummaryDTO (
        BigDecimal totalBalance,
        BigDecimal totalIncome,
        BigDecimal totalExpense
) {}
