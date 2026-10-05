package com.records;

public record Animal(int id, String name) {

    public Animal {
        if (id < 18)
            throw new IllegalArgumentException();
    }
}
