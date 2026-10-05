package com.marker;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class DeserializeDemo {

    public static void main(String[] args) throws Exception {

        ObjectInputStream ois =
                new ObjectInputStream(
                        new FileInputStream("person.ser"));

        Person person = (Person) ois.readObject();

        ois.close();

        System.out.println(person);
    }
}
