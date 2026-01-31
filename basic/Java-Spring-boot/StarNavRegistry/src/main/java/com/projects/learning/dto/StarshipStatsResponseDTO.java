package com.projects.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StarshipStatsResponseDTO {

    private Integer numberOfShips;
    private Integer percentOfOperativeShips;
    private List<StarshipResponseDTO> starshipsWithMaxCrewCapacity;

}
