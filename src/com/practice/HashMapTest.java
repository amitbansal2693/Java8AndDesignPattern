package com.practice;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer,String> map=new HashMap<>();
        map.put(null, "");
        map.put(null,"asda");
        System.out.println(map);
    }
}
