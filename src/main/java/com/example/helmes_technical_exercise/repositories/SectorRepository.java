package com.example.helmes_technical_exercise.repositories;

import com.example.helmes_technical_exercise.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {
}
