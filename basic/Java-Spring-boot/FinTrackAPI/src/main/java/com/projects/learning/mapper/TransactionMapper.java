package com.projects.learning.mapper;

import com.projects.learning.domain.Transaction;
import com.projects.learning.dto.TransactionRequestDTO;
import com.projects.learning.dto.TransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Esto permite inyectarlo con @Autowired
public interface TransactionMapper {

    // Convierte el DTO de entrada en la Entidad para guardar en DB
    // Ignoramos el ID porque se genera automáticamente
    @Mapping(target = "id", ignore = true)
    Transaction toEntity(TransactionRequestDTO dto);

    // Convierte la Entidad en el DTO de salida para la API
    TransactionResponseDTO toResponseDTO(Transaction entity);
}
