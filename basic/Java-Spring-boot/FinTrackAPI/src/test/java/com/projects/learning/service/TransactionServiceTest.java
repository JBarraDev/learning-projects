package com.projects.learning.service;

import com.projects.learning.domain.Transaction;
import com.projects.learning.domain.TransactionType;
import com.projects.learning.dto.TransactionSummaryDTO;
import com.projects.learning.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Inicializa Mockito para que podamos usar mocks.
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    // Crea un simulador de tu repositorio. No necesitamos que la base de datos H2 esté encendida.
    @Mock
    private TransactionRepository transactionRepository;
    // Crea una instancia real de TransactionService e inyecta dentro de ella el "falso" repositorio que creamos arriba.
    @InjectMocks
    private TransactionService transactionService;

    @Test
    @DisplayName("Debería calcular el resumen correctamente con ingresos y gastos")
    void shouldCalculateSummaryCorrectly() {
        // GIVEN (Preparación de datos)
        Transaction income = new Transaction(1L, "Sueldo", new BigDecimal("2000.00"),
                LocalDate.now(), TransactionType.INCOME, "Salario");
        Transaction expense = new Transaction(2L, "Alquiler", new BigDecimal("800.00"),
                LocalDate.now(), TransactionType.EXPENSE, "Vivienda");

        List<Transaction> transactions = Arrays.asList(income, expense);

        // Comportamiento del Mock: cuando el service pida todos, devuelve nuestra lista
        when(transactionRepository.findAll()).thenReturn(transactions);

        // WHEN (Ejecución)
        TransactionSummaryDTO summary = transactionService.calculateSummary();

        // THEN (Verificaciones)
        // Balance: 2000 - 800 = 1200
        assertEquals(new BigDecimal("1200.00"), summary.totalBalance(), "El balance total es incorrecto");
        assertEquals(new BigDecimal("2000.00"), summary.totalIncome(), "El total de ingresos es incorrecto");
        assertEquals(new BigDecimal("800.00"), summary.totalExpense(), "El total de gastos es incorrecto");
    }
}

