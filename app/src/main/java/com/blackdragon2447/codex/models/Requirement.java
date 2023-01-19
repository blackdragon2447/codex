package com.blackdragon2447.codex.models;

import java.io.Serializable;

public class Requirement<T> implements Serializable {

    private Type type;
    private T value;

    public enum Type {

        RANK,
        ATTRIBUTE,
        SKILL,
        EDGE;

    }

}
