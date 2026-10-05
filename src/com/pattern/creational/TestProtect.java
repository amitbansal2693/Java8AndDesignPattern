package com.pattern.creational;


import com.hybrs.User;

public class TestProtect extends User {
    public  void main(String[] args) {

        String a=protectName;
        //why protected is not accessible here
        var var=2;
        System.out.println(var);
    }
}
