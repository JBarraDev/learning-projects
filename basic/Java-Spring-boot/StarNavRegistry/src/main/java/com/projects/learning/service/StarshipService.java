package com.projects.learning.service;

import com.projects.learning.domain.ShipStatus;
import com.projects.learning.domain.Starship;
import com.projects.learning.dto.StarshipRequestDTO;
import com.projects.learning.dto.StarshipResponseDTO;
import com.projects.learning.dto.StarshipStatsResponseDTO;
import com.projects.learning.mapper.StarshipMapper;
import com.projects.learning.repository.StarshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StarshipService {

    private StarshipRepository starshipRepository;
    private final StarshipMapper starshipMapper;

    @Transactional(readOnly = true)
    public List<StarshipResponseDTO> findAll() {
        return starshipRepository.findAll().stream()
                .map(starshipMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public StarshipResponseDTO findById(Long id) {
        return starshipMapper.toResponseDto(findStarshipById(id));
    }

    @Transactional
    public StarshipResponseDTO save(StarshipRequestDTO starship) {
        Starship entity = starshipMapper.toEntity(starship);
        return starshipMapper.toResponseDto(starshipRepository.save(entity));
    }

    @Transactional
    public StarshipResponseDTO updateStatus(Long id, ShipStatus status) {
        Starship entity = findStarshipById(id);
        entity.setStatus(status);
        return starshipMapper.toResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public StarshipStatsResponseDTO getStats() {
        List<Starship> starships = starshipRepository.findAll();

        return new StarshipStatsResponseDTO(starships.size(),getPercentOperativesShips(starships), findStarshipByCrewCapacity(starships));
    }

    private Starship findStarshipById(Long id) {
        return starshipRepository.findById(id).orElse(null);
    }

    // Métodos auxiliares
    // Porcentaje de naves operativas
    private Integer getPercentOperativesShips(List<Starship> starships) {
        long operationalShips = starships.stream()
                .filter(starship -> starship.getStatus() == ShipStatus.OPERATIONAL)
                .count();

        return (int) ((double)operationalShips / starships.size())*100;
    }

    // Mayor capacidad de tripulación
    private List<Starship> findStarshipByCrewCapacity(List<Starship> starships) {

        int maxCrew = starships.stream()
                .mapToInt(Starship::getCrewCapacity)
                .max()
                .orElseThrow();

        return starships.stream()
                .filter(p -> p.getCrewCapacity() == maxCrew)
                .toList();
    }
}
