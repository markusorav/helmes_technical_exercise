package com.example.helmes_technical_exercise.services;

import com.example.helmes_technical_exercise.entities.Sector;
import com.example.helmes_technical_exercise.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    public Optional<Sector> findSectorById(int id) {
        return sectorRepository.findById(id);
    }

    public Sector insertSector(Sector sector) {
        return sectorRepository.save(sector);
    }
}

