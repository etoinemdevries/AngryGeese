package com.teamsenseo.angrygeese.utils;

public final class Field {
    private final String id;
    private final String name;
    private final String damage;

    public Field(final String id, final String name, final String damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDamage() {
        return this.damage;
    }
}