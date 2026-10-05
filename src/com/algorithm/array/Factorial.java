package com.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Find Factorial of a Large Number
 * Last Updated :
 * 1 Sep, 2026
 * Given a non-negative integer n, find n! and return a list of integers representing the digits of the factorial.
 */
public class Factorial {
    public static <ArrayList> void main(String[] args) {
        int n = 10;

        List<Integer> ans = factorial(n);
        int out =findfact(n);
        System.out.println(out + " characters: "+ String.valueOf(out).toCharArray());
        for(Character ch: String.valueOf(out).toCharArray())
            System.out.print(" : "+ch);
        System.out.println();
        System.out.println(ans);
    }

    private static List factorial(int n) {
        List<Integer> ans = new ArrayList<>();
        int i = 1;
        while (i < n) {
            if (n % i == 0) {
                ans.add(i);
            }
            i++;
        }
        return ans;
    }

    private static int findfact(int n) {
            if(n==0 || n==1)
                return 1;
            return findfact(n-1)*n;
    }
}
