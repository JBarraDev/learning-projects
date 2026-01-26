package com.projects.learning.service;

import com.projects.learning.domain.Transaction;
import com.projects.learning.domain.TransactionType;
import com.projects.learning.dto.TransactionRequestDTO;
import com.projects.learning.dto.TransactionResponseDTO;
import com.projects.learning.mapper.TransactionMapper;
import com.projects.learning.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Transactional(readOnly = true)
    public List<TransactionResponseDTO> findAll() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TransactionResponseDTO create(TransactionRequestDTO dto) {
        Transaction entity = transactionMapper.toEntity(dto);
        Transaction saved = transactionRepository.save(entity);
        return transactionMapper.toResponseDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transacción no encontrada con ID: " + id);
        }
        transactionRepository.deleteById(id);
    }

}
