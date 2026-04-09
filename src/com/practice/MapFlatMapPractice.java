package com.practice;

import java.util.*;
import java.util.stream.*;

public class MapFlatMapPractice {

    /**
     * 1️⃣ Given a list of names, return a list of uppercase names.
     * Input: ["john", "jane", "doe"]
     * Output: ["JOHN", "JANE", "DOE"]
     */
    public static List<String> uppercaseNames(List<String> names) {
        // TODO: Use map
        //return names.stream().map(n->n.toUpperCase()).toList();
        return names.stream().map(String::toUpperCase).toList();
    }

    /**
     * 2️⃣ Given a list of sentences, return list of word counts.
     * Input: ["hello world", "java streams", "map vs flatmap"]
     * Output: [2, 2, 3]
     */
    public static List<Integer> wordCounts(List<String> sentences) {
        // TODO: Use map
        // can use map(n-> n.length())
        return sentences.stream()
                .map(s -> s.split(" ").length)
                .toList();

       // return sentences.stream().map(String::length).toList();
    }

    /**
     * 3️⃣ Flatten a list of list of strings.
     * Input: [["a", "b"], ["c", "d"], ["e"]]
     * Output: ["a", "b", "c", "d", "e"]
     */
    public static List<String> flattenNestedList(List<List<String>> nestedList) {
        // TODO: Use flatMap
        // nestedList.stream().flatMap(Collection::stream).toList();
        return nestedList.stream().flatMap(n->n.stream()).toList();
    }

    /**
     * 4️⃣ From a list of CSV-style strings, return a flat list of all values.
     * Input: ["a,b", "c,d", "e"]
     * Output: ["a", "b", "c", "d", "e"]
     */
    public static List<String> splitCSVLines(List<String> csvLines) {
        // TODO: Use flatMap
        return csvLines.stream()
                .flatMap(line -> Arrays.stream(line.split(",")))
                .toList();

        //  return csvLines.stream().flatMap(n-> Arrays.stream(n.split(",")).map(String::toString).toList().stream()).toList();
    }

    public static void main(String[] args) {
        // You will test your code here after solving
    }
}
