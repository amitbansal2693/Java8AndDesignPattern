package com.practice;


import java.util.*;
import java.util.stream.IntStream;

/**
 * ============================================================================
 * Java Pairing / Combination Problems Assignment
 * ============================================================================
 *
 * Complete all TODO sections.
 *
 * Topics Covered:
 *
 * 1. Pair Sum
 * 2. Triplet Sum
 * 3. Adjacent Pair
 * 4. Adjacent Triplet
 * 5. Sliding Window
 * 6. Streams Practice
 *
 * ============================================================================
 */

public class PairingAndCombinationAssignment {

    public static void main(String[] args) {

        // ============================================================
        // INPUT
        // ============================================================

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

        // ============================================================
        // 1. TWO ELEMENT PAIR SUM
        // ============================================================

        int pairTarget = 7;

        System.out.println("\n==============================");
        System.out.println("1. TWO ELEMENT PAIR SUM");
        System.out.println("==============================");

        System.out.println("\nFOR LOOP:");
        twoElementPairForLoop(list, pairTarget);

        System.out.println("\nSTREAM:");
        twoElementPairStream(list, pairTarget);


        // ============================================================
        // 2. THREE ELEMENT TRIPLET SUM
        // ============================================================

        int tripletTarget = 10;

        System.out.println("\n==============================");
        System.out.println("2. THREE ELEMENT TRIPLET SUM");
        System.out.println("==============================");

        System.out.println("\nFOR LOOP:");
        threeElementTripletForLoop(list, tripletTarget);

        System.out.println("\nSTREAM:");
        threeElementTripletStream(list, tripletTarget);


        // ============================================================
        // 3. ADJACENT PAIR SUM
        // ============================================================

        int adjacentPairTarget = 5;

        System.out.println("\n==============================");
        System.out.println("3. ADJACENT PAIR SUM");
        System.out.println("==============================");

        System.out.println("\nFOR LOOP:");
        // adjacentPairForLoop(list, adjacentPairTarget);

        System.out.println("\nSTREAM:");
        //adjacentPairStream(list, adjacentPairTarget);


        // ============================================================
        // 4. ADJACENT TRIPLET SUM
        // ============================================================

        int adjacentTripletTarget = 9;

        System.out.println("\n==============================");
        System.out.println("4. ADJACENT TRIPLET SUM");
        System.out.println("==============================");

/*        System.out.println("\nFOR LOOP:");
        adjacentTripletForLoop(list, adjacentTripletTarget);

        System.out.println("\nSTREAM:");
        adjacentTripletStream(list, adjacentTripletTarget);


        // ============================================================
        // 5. FIXED WINDOW SUM
        // ============================================================

        int window = 3;
        int windowTarget = 12;

        System.out.println("\n==============================");
        System.out.println("5. FIXED WINDOW SUM");
        System.out.println("==============================");

        System.out.println("\nFOR LOOP:");
        fixedWindowForLoop(list, window, windowTarget);

        System.out.println("\nSTREAM:");
        fixedWindowStream(list, window, windowTarget);*/
    }


    // ========================================================================
    // 1. TWO ELEMENT PAIR SUM
    // ========================================================================

    /*
     * Problem:
     * Find all unique pairs whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 7
     *
     * Expected Output:
     * [1,6]
     * [2,5]
     * [3,4]
     */

    public static void twoElementPairForLoop(List<Integer> list,
                                             int target) {

        // TODO:
        //
        // 1. Create result list
        // 2. Use nested loops
        // 3. Start second loop from i + 1
        // 4. Check if pair sum equals target
        // 5. Print result
        //
        // Hint:
        // list.get(i) + list.get(j)
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) + list.get(j) == target) {
                    result.add(List.of(list.get(i), list.get(j)));
                }
            }
        }

        System.out.println("result:vtwoElementPairForLoop " + result);

    }


    public static void twoElementPairStream(List<Integer> list,
                                            int target) {

        // TODO:
        //
        // 1. Use IntStream.range -outer loop 0 to 2nd last last
        // 2. Use flatMap for nested looping
        // 3. Filter matching pairs
        // 4. Convert to List
        // 5. Print output
        //
        // Hint:
        // mapToObj()

        List<List<Integer>> result = IntStream.range(0, list.size() - 1)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, list.size()). //from 2nd element till last element
                        filter(j -> list.get(i) + list.get(j) == target)
                        .mapToObj(j -> List.of(list.get(i) + list.get(j)))
                ).toList();
        System.out.println("result:vtwoElementPairForLoop " + result);
    }


    // ========================================================================
    // 2. THREE ELEMENT TRIPLET SUM
    // ========================================================================

    /*
     * Problem:
     * Find all unique triplets whose sum equals target. Those pairs can be in any location not adjacent
     * Since these are not consecutive, maybe sort and then check substring
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 10
     *
     * Expected Output:
     * [1,3,6]
     * [1,4,5]
     * [2,3,5]
     */

    public static void threeElementTripletForLoop(List<Integer> list,
                                                  int target) {

        // TODO:
        //
        // 1. Use 3 nested loops: first loop 0 to 3rd last element
        // 2. j starts from i + 1
        // 3. k starts from j + 1
        // 4. Check triplet sum
        // 5. Print matching triplets
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size() - 2; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    if (list.get(i) + list.get(j) + list.get(k) == target) {
                        result.add(list.get(i) + "," + list.get(j) + "," + list.get(k));

                    }
                }
            }
        }
        System.out.println(result);
    }


    public static void threeElementTripletStream(List<Integer> list,
                                                 int target) {

        // TODO:
        //
        // 1. Create nested IntStreams
        // 2. Use flatMap
        // 3. Filter matching target
        // 4. Convert to object
        // 5. Print result

        List<List<Integer>> result =IntStream.range(0,list.size()-2).boxed()
                .flatMap(i->
                        IntStream.range(i+1, list.size()-1).boxed()
                                .flatMap(j->
                                        IntStream.range(j+1, list.size()).boxed()
                                                .filter(k-> (list.get(i)+ list.get(j)+list.get(k))==target)
                                                .map(k -> List.of(list.get(i) , list.get(j),list.get(k)))
                                )
                ).toList();



            System.out.println(result);
        }



    // ========================================================================
    // 3. ADJACENT PAIR SUM
    // ========================================================================


    /*
     * Problem:
     * Find adjacent pairs whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 5
     *
     * Adjacent Pairs:
     * [1,2]
     * [2,3]
     * [3,4]
     * [4,5]
     * [5,6]
     *
     * Expected Output:
     * [2,3]
     *//*


    public static void adjacentPairForLoop(List<Integer> list,
                                           int target) {

        // TODO:
        //
        // 1. Loop till size - 1
        // 2. Compare current + next element
        // 3. Print matching pair

    }


    public static void adjacentPairStream(List<Integer> list,
                                          int target) {

        // TODO:
        //
        // 1. Use IntStream.range
        // 2. Filter adjacent pair sum
        // 3. Convert to List
        // 4. Print result

    }


    // ========================================================================
    // 4. ADJACENT TRIPLET SUM
    // ========================================================================

    */
    /*
     * Problem:
     * Find adjacent triplets whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * target = 9
     *
     * Adjacent Triplets:
     * [1,2,3]
     * [2,3,4]
     * [3,4,5]
     * [4,5,6]
     *
     * Expected Output:
     * [2,3,4]
     *//*


    public static void adjacentTripletForLoop(List<Integer> list,
                                              int target) {

        // TODO:
        //
        // 1. Loop till size - 2
        // 2. Calculate adjacent triplet sum
        // 3. Compare with target
        // 4. Print matching triplet

    }


    public static void adjacentTripletStream(List<Integer> list,
                                             int target) {

        // TODO:
        //
        // 1. Use IntStream.range
        // 2. Filter adjacent triplet sum
        // 3. Convert to List
        // 4. Print result

    }


    // ========================================================================
    // 5. FIXED WINDOW ADJACENT SUM
    // ========================================================================

    */
    /*
     * Problem:
     * Find all adjacent windows of size N whose sum equals target.
     *
     * Input:
     * [1,2,3,4,5,6]
     * window = 3
     * target = 12
     *
     * Windows:
     * [1,2,3]
     * [2,3,4]
     * [3,4,5]
     * [4,5,6]
     *
     * Expected Output:
     * [3,4,5]
     *//*


    public static void fixedWindowForLoop(List<Integer> list,
                                          int window,
                                          int target) {

        // TODO:
        //
        // 1. Loop till size - window + 1
        // 2. Calculate sliding window sum
        // 3. Compare with target
        // 4. Print matching window
        //
        // Hint:
        // list.subList(start, end)

    }


    public static void fixedWindowStream(List<Integer> list,
                                         int window,
                                         int target) {

        // TODO:
        //
        // 1. Generate valid indexes
        // 2. Create subList window
        // 3. Calculate sum using stream
        // 4. Filter matching window
        // 5. Print result

    }

*/

    // ========================================================================
    // BONUS QUESTIONS
    // ========================================================================

    /*
     * BONUS 1:
     *
     * Find pairs whose multiplication equals target.
     *
     * Example:
     *
     * target = 6
     *
     * Output:
     * [1,6]
     * [2,3]
     */


    /*
     * BONUS 2:
     *
     * Print all windows of size 4.
     *
     * Example:
     *
     * [1,2,3,4]
     * [2,3,4,5]
     * [3,4,5,6]
     */


    // ========================================================================
    // INTERVIEW QUESTIONS
    // ========================================================================

    /*
     * 1. Difference between map() and flatMap()?
     *
     * 2. Why use streams?
     *
     * 3. What is sliding window technique?
     *
     * 4. Time complexity of pair/triplet problems?
     *
     * 5. How to optimize pair sum using HashSet?
     */

}