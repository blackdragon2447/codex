package com.blackdragon2447.codex.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EDGES", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, length = 11)
    int id;

    @Column(name = "NAME", length = 127, nullable = false)
    String name;

    @Column(name = "REQUIREMENTS", nullable = false)
    @ElementCollection
    List<String> requirements;

    @Column(name = "DESCRIPTION")
    String description;

    public Edge() {

    }

    public Edge(String name, List<String> requirements, String description) {
        this.name = name;
        this.requirements = requirements;
        this.description = description;
    }

    public String name() {
        return this.name;
    }

    public List<String> requirements() {
        return this.requirements != null ? this.requirements : new ArrayList<>();
    }

    public String description() {
        return this.description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
