package com.basics;

import java.util.*;

public class ArrayListAssignment {

    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        ArrayList<Integer> duplicateNumbers = new ArrayList<>();
        duplicateNumbers.add(1);
        duplicateNumbers.add(2);
        duplicateNumbers.add(2);
        duplicateNumbers.add(3);
        duplicateNumbers.add(4);
        duplicateNumbers.add(4);

        ArrayList<String> names = new ArrayList<>();
        names.add("Amit");
        names.add("Rahul");
        names.add("Java");
        names.add("Developer");


        printList(numbers);

        addElement(numbers, 60);

        removeElement(numbers, 20);

        searchElement(numbers, 30);

        updateElement(numbers, 0, 100);

        listSize(numbers);

        sumOfElements(numbers);

        findLargest(numbers);

        findSmallest(numbers);

        reverseList(numbers);

        sortList(numbers);

        countEvenNumbers(numbers);

        countOddNumbers(numbers);

        printStrings(names);

        longestString(names);

        shortestString(names);

        convertToUpperCase(names);

        checkElementExists(names, "Java");

        removeDuplicates(duplicateNumbers);

        mergeLists(numbers, duplicateNumbers);
    }


    // ============================================================
    // METHOD 1
    // Expectation:
    // Print all elements in ArrayList.
    // Input: ArrayList<Integer>
    // Output: Print all elements
    // ============================================================
    public static void printList(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 2
    // Expectation:
    // Add element into ArrayList.
    // Input: list and integer value
    // Output: Print updated list
    // ============================================================
    public static void addElement(ArrayList<Integer> list, int value) {

    }


    // ============================================================
    // METHOD 3
    // Expectation:
    // Remove element from ArrayList.
    // Input: list and integer value
    // Output: Print updated list
    // ============================================================
    public static void removeElement(ArrayList<Integer> list, int value) {

    }


    // ============================================================
    // METHOD 4
    // Expectation:
    // Search element in list.
    // Input: list and value
    // Output: Found or Not Found
    // ============================================================
    public static void searchElement(ArrayList<Integer> list, int value) {

    }


    // ============================================================
    // METHOD 5
    // Expectation:
    // Update element at index.
    // Input: list, index, newValue
    // Output: Print updated list
    // ============================================================
    public static void updateElement(ArrayList<Integer> list, int index, int newValue) {

    }


    // ============================================================
    // METHOD 6
    // Expectation:
    // Find size of ArrayList.
    // Input: list
    // Output: Print size
    // ============================================================
    public static void listSize(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 7
    // Expectation:
    // Find sum of elements.
    // Input: list
    // Output: Print total sum
    // ============================================================
    public static void sumOfElements(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 8
    // Expectation:
    // Find largest number.
    // Input: list
    // Output: Print largest number
    // ============================================================
    public static void findLargest(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 9
    // Expectation:
    // Find smallest number.
    // Input: list
    // Output: Print smallest number
    // ============================================================
    public static void findSmallest(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 10
    // Expectation:
    // Reverse ArrayList.
    // Input: list
    // Output: Print reversed list
    // ============================================================
    public static void reverseList(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 11
    // Expectation:
    // Sort list in ascending order.
    // Input: list
    // Output: Print sorted list
    // ============================================================
    public static void sortList(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 12
    // Expectation:
    // Count even numbers.
    // Input: list
    // Output: Print even count
    // ============================================================
    public static void countEvenNumbers(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 13
    // Expectation:
    // Count odd numbers.
    // Input: list
    // Output: Print odd count
    // ============================================================
    public static void countOddNumbers(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 14
    // Expectation:
    // Print all strings.
    // Input: ArrayList<String>
    // Output: Print all names
    // ============================================================
    public static void printStrings(ArrayList<String> list) {

    }


    // ============================================================
    // METHOD 15
    // Expectation:
    // Find longest string.
    // Input: list of strings
    // Output: Print longest string
    // ============================================================
    public static void longestString(ArrayList<String> list) {

    }


    // ============================================================
    // METHOD 16
    // Expectation:
    // Find shortest string.
    // Input: list of strings
    // Output: Print shortest string
    // ============================================================
    public static void shortestString(ArrayList<String> list) {

    }


    // ============================================================
    // METHOD 17
    // Expectation:
    // Convert all strings to uppercase.
    // Input: list of strings
    // Output: Print updated list
    // ============================================================
    public static void convertToUpperCase(ArrayList<String> list) {

    }


    // ============================================================
    // METHOD 18
    // Expectation:
    // Check if element exists.
    // Input: list and string value
    // Output: Exists or Not Exists
    // ============================================================
    public static void checkElementExists(ArrayList<String> list, String value) {

    }


    // ============================================================
    // METHOD 19
    // Expectation:
    // Remove duplicate values.
    // Input: list with duplicates
    // Output: Print unique values
    // ============================================================
    public static void removeDuplicates(ArrayList<Integer> list) {

    }


    // ============================================================
    // METHOD 20
    // Expectation:
    // Merge two ArrayLists.
    // Input: two lists
    // Output: Print merged list
    // ============================================================
    public static void mergeLists(ArrayList<Integer> list1, ArrayList<Integer> list2) {

    }
}