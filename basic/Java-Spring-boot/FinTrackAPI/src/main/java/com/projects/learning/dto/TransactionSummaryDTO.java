package com.projects.learning.dto;

import java.math.BigDecimal;

public record TransactionSummaryDTO (
        BigDecimal totalBalance,
        BigDecimal totalIncome,
        BigDecimal totalExpense
) {}
