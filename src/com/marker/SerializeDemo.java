package com.marker;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

public class SerializeDemo {


    public static void main(String[] args) throws Exception {

        Person person = new Person("Amit", 30);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"));

        oos.writeObject(person);
        ObjectStreamClass osc = ObjectStreamClass.lookup(Person.class);
        oos.close();
        System.out.println("Object Serialized Successfully" + osc.getSerialVersionUID());
    }
}