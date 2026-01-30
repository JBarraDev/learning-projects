package com.projects.learning.dto;

import com.projects.learning.domain.Starship;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@AllArgsConstructor
public class StarshipStatsResponseDTO {

    private Integer numberOfShips;
    private Integer percentOfOperativeShips;
    private List<Starship> starships;

}
