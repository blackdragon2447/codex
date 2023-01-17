package com.blackdragon2447.codex.models;

import jakarta.persistence.*;

@Entity
@Table(name = "HINDRANCES", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Hindrance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, length = 11)
    int id;

    @Column(name = "NAME", length = 127, nullable = false)
    String name;

    @Column(name = "DESCRIPTION", length = 65535, nullable = true)
    String description;

    @Column(name = "TYPE", nullable = false)
    HindranceType type;

    protected Hindrance() {
    }

    public Hindrance(String name, String description, HindranceType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public HindranceType type() {
        return this.type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum HindranceType {
        MAYOR,
        MINOR,
    }


}
