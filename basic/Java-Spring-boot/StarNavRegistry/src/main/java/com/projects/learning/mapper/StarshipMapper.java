package com.projects.learning.mapper;

import com.projects.learning.domain.Starship;
import com.projects.learning.dto.StarshipRequestDTO;
import com.projects.learning.dto.StarshipResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StarshipMapper {

    StarshipResponseDTO toResponseDto(Starship entity);

    @Mapping(target = "id", ignore = true)  // Se ignora id que genera solo
    Starship toEntity(StarshipRequestDTO dto);
}


