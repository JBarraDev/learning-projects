package com.projects.learning;

import com.projects.learning.domain.ShipStatus;
import com.projects.learning.domain.Starship;
import com.projects.learning.dto.StarshipRequestDTO;
import com.projects.learning.dto.StarshipResponseDTO;
import com.projects.learning.mapper.StarshipMapper;
import com.projects.learning.repository.StarshipRepository;
import com.projects.learning.service.StarshipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StarshipServiceTest {

    @Mock
    private StarshipRepository starshipRepository;
    @Mock
    private StarshipMapper starshipMapper;

    @InjectMocks
    private StarshipService starshipService;

    @Test
    void shouldSetStatusToUnderRepairWhenMaintenanceIsOlderThanTwoYears() {
        // GIVEN: Una nave de hace 3 años
        StarshipRequestDTO request = new StarshipRequestDTO();
        request.setStatus(ShipStatus.OPERATIONAL);
        request.setLastMaintenance(LocalDate.now().minusYears(3));

        Starship entity = new Starship();
        entity.setStatus(ShipStatus.OPERATIONAL);
        entity.setLastMaintenance(request.getLastMaintenance());

        when(starshipMapper.toEntity(any())).thenReturn(entity);
        when(starshipRepository.save(any())).thenReturn(entity);
        when(starshipMapper.toResponseDto(any())).thenReturn(new StarshipResponseDTO());

        // WHEN: Guardamos
        starshipService.save(request);

        // THEN: El estado debe haber cambiado
        assertEquals(ShipStatus.UNDER_REPAIR, entity.getStatus());
    }
}

