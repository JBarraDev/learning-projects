package com.projects.learning.service;

import com.projects.learning.domain.ShipStatus;
import com.projects.learning.domain.Starship;
import com.projects.learning.dto.StarshipRequestDTO;
import com.projects.learning.dto.StarshipResponseDTO;
import com.projects.learning.dto.StarshipStatsResponseDTO;
import com.projects.learning.dto.StatusUpdateDTO;
import com.projects.learning.exception.ResourceNotFoundException;
import com.projects.learning.mapper.StarshipMapper;
import com.projects.learning.repository.StarshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        long yearsSinceMaintenance = ChronoUnit.YEARS.between(entity.getLastMaintenance(), LocalDate.now());
        if (yearsSinceMaintenance > 2) {
            entity.setStatus(ShipStatus.UNDER_REPAIR);
        }

        return starshipMapper.toResponseDto(starshipRepository.save(entity));
    }

    @Transactional
    public StarshipResponseDTO updateStatus(Long id, StatusUpdateDTO status) {
        Starship entity = findStarshipById(id);
        entity.setStatus(status.getStatus());
        return starshipMapper.toResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public StarshipStatsResponseDTO getStats() {
        List<Starship> starships = starshipRepository.findAll();

        if (starships.isEmpty()) {
            return new StarshipStatsResponseDTO(0, 0, null);
        }

        int total = starships.size();
        int percent = getPercentOperativesShips(starships);
        List<StarshipResponseDTO> topShips = findStarshipByCrewCapacity(starships).stream()
                .map(starshipMapper::toResponseDto)
                .toList();

        return new StarshipStatsResponseDTO(total,percent, topShips);
    }

    private Starship findStarshipById(Long id) {
        return starshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La nave con ID " + id + " no existe en nuestros registros."));
    }

    // Métodos auxiliares
    // Porcentaje de naves operativas
    private Integer getPercentOperativesShips(List<Starship> starships) {
        if (starships.isEmpty()) return 0;

        long operationalShips = starships.stream()
                .filter(starship -> starship.getStatus() == ShipStatus.OPERATIONAL)
                .count();

        return (int) (((double)operationalShips / starships.size())*100);
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
