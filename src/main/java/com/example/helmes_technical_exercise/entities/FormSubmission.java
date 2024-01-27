package com.example.helmes_technical_exercise.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "form_submissions")
public class FormSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean agreeToTerms;

    @Transient // This field is not stored in the database
    private List<Integer> sectorIds = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "submission_sectors",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id")
    )
    private List<Sector> sectors;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAgreeToTerms() {
        return agreeToTerms;
    }

    public List<Integer> getSectorIds() {
        return sectorIds;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgreeToTerms(boolean agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }

    public void setSectorIds(List<Integer> sectorIds) {
        this.sectorIds = sectorIds;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }
}
