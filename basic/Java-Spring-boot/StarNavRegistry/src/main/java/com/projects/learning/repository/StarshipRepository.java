package com.projects.learning.repository;

import com.projects.learning.domain.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipRepository extends JpaRepository<Starship,Long> {

}
