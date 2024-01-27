package com.example.helmes_technical_exercise.controllers;

import com.example.helmes_technical_exercise.entities.Sector;
import com.example.helmes_technical_exercise.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {
    @Autowired
    private SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<Sector>> getAllSectors() {
        return ResponseEntity.ok(sectorService.getAllSectors());
    }
}

