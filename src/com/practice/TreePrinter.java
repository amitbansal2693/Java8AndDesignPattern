package com.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreePrinter {

    public static void printTree(Map<String, List<String>> hierarchyMap, String currentNode, String indent) {
        // Print the current node (the key)
        System.out.println(indent + "- " + currentNode);

        // Check if the current node has children in the map
        if (hierarchyMap.containsKey(currentNode)) {
            List<String> children = hierarchyMap.get(currentNode);
            // Recursively call printTree for each child with increased indentation
            for (String child : children) {
                // Pass the map, child node, and increase the indent
                printTree(hierarchyMap, child, indent + "  ");
            }
        }
    }

    public static void main(String[] args) {
        // Example Usage:
        Map<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("A", Arrays.asList("B", "C"));
        dataMap.put("B", Arrays.asList("D", "E"));
        dataMap.put("C", Arrays.asList("F"));
        dataMap.put("D", Arrays.asList());
        dataMap.put("E", Arrays.asList());
        dataMap.put("F", Arrays.asList());

        // Start the printing process from the root node (e.g., "A")
        printTree(dataMap, "A", "");
    }
}
