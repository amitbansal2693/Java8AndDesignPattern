package com.hybrs;

import java.util.*;

public class GenericsTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("Hello");
        list.add(123);  // allowed 😬
        //print the list
        System.out.println(list);

    }
}
