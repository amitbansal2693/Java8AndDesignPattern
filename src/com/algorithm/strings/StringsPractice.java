package com.algorithm.strings;

import java.util.*;

/**
 * Given a string s having lowercase characters, find the length of the longest substring without repeating characters.
 *
 * Examples:
 *
 * Input: s = "geeksforgeeks"
 * Output: 7
 * Explanation: The longest substrings without repeating characters are "eksforg” and "ksforge", with lengths of 7.
 *
 * Input: s = "aaa"
 * Output: 1
 * Explanation: The longest substring without repeating characters is "a"
 *
 * Input: s = "abcdefabcbb"
 * Output: 6
 * Explanation: The longest substring without repeating characters is "abcdef".
 */
public class StringsPractice {
    public static void main(String[] args){
        String s = "geeksforgeeks";
        System.out.println(longestUniqueSubstr(s));
    }

    private static String longestUniqueSubstr(String s) {
        String result="";
        Set<Character> set=new HashSet<>();
        List<String> sring=new ArrayList<>();
        for(Character ch: s.toCharArray()){
            if(!set.isEmpty() && set.contains(ch)) {
                if(set.size()>result.length()) {
                    System.out.println("update result");
                    result = set.toString();
                }
                set.remove(ch);

                System.out.println("Adter Remove: "+result + "set "+set.toString());
            }
            set.add(ch);
            System.out.println("before add: result "+result + "set "+set.toString()+ "\n");
        }


        return result;
    }
}
