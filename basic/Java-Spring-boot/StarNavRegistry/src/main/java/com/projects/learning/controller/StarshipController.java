package com.projects.learning.controller;

import com.projects.learning.dto.StarshipRequestDTO;
import com.projects.learning.dto.StarshipResponseDTO;
import com.projects.learning.dto.StarshipStatsResponseDTO;
import com.projects.learning.dto.StatusUpdateDTO;
import com.projects.learning.service.StarshipService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
public class StarshipController {

    private StarshipService starshipService;

    public StarshipController(StarshipService starshipService) {
        this.starshipService = starshipService;
    }

    @GetMapping
    public ResponseEntity<List<StarshipResponseDTO>> getAllStarships(@RequestParam(required = false) String quadrant) {
        return ResponseEntity.ok(starshipService.findAll(quadrant));
    }

    @GetMapping("/{id}")
    ResponseEntity<StarshipResponseDTO> getStarshipById(@PathVariable Long id){
        return ResponseEntity.ok(starshipService.findById(id));
    }

    @PostMapping
    ResponseEntity<StarshipResponseDTO> createStarship(@Valid @RequestBody StarshipRequestDTO starshipRequestDTO){
        return ResponseEntity.ok(starshipService.save(starshipRequestDTO));
    }

    @PutMapping("{id}/status")
    ResponseEntity<StarshipResponseDTO> updateStarshipStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateDTO status){
        return ResponseEntity.ok(starshipService.updateStatus(id, status));
    }

    @GetMapping("/stats")
    ResponseEntity<StarshipStatsResponseDTO> getStarshipStats(){
        return ResponseEntity.ok(starshipService.getStats());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<StarshipResponseDTO> decommissionStarship(@PathVariable Long id){
        return ResponseEntity.ok(starshipService.decommissionStarship(id));
    }

}
