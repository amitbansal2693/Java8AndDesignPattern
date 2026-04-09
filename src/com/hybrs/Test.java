package com.hybrs;


import java.io.Serializable;
import java.util.*;

//how to make marker interface and how to use it.

public class Test implements Serializable {
    public void fun(Integer i) {
        System.out.println("fun(Integer ) ");
    }

    public void fun(String name) {
        System.out.println("fun(String ) ");
    }
    public static void main(String[] args) {

        double totalPriceWithoutDiscount=119.00;
        Double totalDiscounts=119.00;

        double negativeDiscount = totalPriceWithoutDiscount - totalDiscounts;
        System.out.println("negativeDiscount "+negativeDiscount + " negativeDiscount <0  "+(negativeDiscount<0));

        User user1=new User("John","John@gmail.com");
        System.out.println("public: "+user1.publicName + " -protected: "+user1.protectName + " -default "+user1.defaultName);
        final User user2=new User("amit","amit@gmail.com");
List<String> arrList=new ArrayList<>();
        arrList.stream();

        final int a;
       a=10;
       //how to check final keyword implementation.

        Test test=new Test();


    }
}

