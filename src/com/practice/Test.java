package com.practice;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = 1;
        for(int a0 = 0; a0 < t; a0++){
            int n = 100;
            int result =  getMultiples(n);
            System.out.println(result);
            result= getMultiplesRecursion(--n);
            System.out.println(result);
        }
    }

    private static int getMultiples(int n) {
        n--;
        int sum = 0;
        while (n > 0) {
            if (n % 3 == 0)
                sum += n;
            else if (n % 5 == 0)
                sum += n;
            n--;
        }
        return sum;
    }
    private static int getMultiplesRecursion(int n) {
        if(n<=1)
            return 0;
        int sum = 0;
            if (n % 3 == 0)
                sum += n;
            else if (n % 5 == 0)
                sum += n;
        return sum + getMultiplesRecursion(--n);
    }
}


