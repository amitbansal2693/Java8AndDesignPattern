package com.marker;

import java.io.Serializable;

import java.io.Serializable;

// No serialVersionUID provided
public class Person implements Serializable {

    private String name;
    private int age;
    private String email;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}