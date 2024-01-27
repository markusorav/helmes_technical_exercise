package com.example.helmes_technical_exercise.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sectors")
public class Sector {
    @Id
    private Integer id;
    private Integer level;
    private String name;
    @Nullable
    private Integer parentId;

    public Sector(Integer id, Integer level, String name, Integer parentId) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public Integer getParentId() {
        return parentId;
    }

    protected Sector() {}
}
