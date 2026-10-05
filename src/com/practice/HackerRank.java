package com.practice;

import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Map.Entry;
public class HackerRank {

    public static void main(String[] args) {
        migratoryBirds(List.of(1, 4, 4, 5, 5, 3));
        bonAppetit(List.of(3, 10, 2, 9), 1, 12);
        sockMerchant(7, List.of(1, 2, 1, 2, 1, 3, 2));
        pageCount(6, 2);
        pageCount(5, 4);
        pageCount(5, 3);
        pageCount(5, 2);
        pageCount(5, 1);
        pageCount(5, 0);

        pageCount(6, 2);
        pageCount(6, 3);
        pageCount(6, 4);
        pageCount(6, 5);
        pageCount(6, 6);
        countingValleys(8, "UDDDUDUU");

    }


    public static int countingValleys(int steps, String path) {
        // Write your code here
        //steps total steps.
        int valleyCount = 0;
        int base = 0;
        Integer[] index = new Integer[steps];
        int i = 0;
        for (String ch : path.split("")) {
            if (ch.equals("U")) {
                base++;
                index[i] = base;
            } else {
                base--;
                index[i] = base;
            }
            i++;
        }
        System.out.println(Arrays.toString(index));
        for (int j = 0; j < index.length - 1; j++) {
            if (index[j] == -1 && index[j + 1] == 0) {
                valleyCount++;
            }
        }
        System.out.println("valley count: " + valleyCount);
        return 0;
    }

private void findBestPair(List<Integer> a, List<Integer> b) {
    List<Integer> bestPair = a.stream()
            .flatMap(x -> b.stream().map(y -> List.of(x, y)))
            .max(Comparator.comparing(pair -> pair.get(0) + pair.get(1)))
            .orElse(List.of());

    System.out.println(bestPair);
}
    static int getMoneySpent(int[] keyboards, int[] drives, int b) {

        int maxSpend = -1;
        for (int k : keyboards) {
            for (int d : drives) {
                int total = k + d;
                if (total <= b && total > maxSpend) {
                    maxSpend = total;
                }
            }
        }
        return maxSpend;
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        List<Integer> result = new ArrayList<>();

       return grades.stream().map(m-> m>37 ?(m%5<3? m: m+(5-(m%5)))  :m ).collect(Collectors.toList());


    }

        public static int migratoryBirds(List<Integer> arr) {
        // Write your code here
        Map<Integer, Long> map=arr.stream().collect(Collectors.groupingBy(n-> n,Collectors.counting()));
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Integer, Long>::getKey).thenComparing(Entry::getValue)).findFirst();
        Optional<Integer> result= map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<Integer, Long>::getValue)
                                .reversed()
                                .thenComparing(Map.Entry::getKey))
                .findFirst().map(Map.Entry::getKey);
        System.out.println(map+ " Output: "+result);

        return result.get();
    }

    public static void bonAppetit(List<Integer> bill, int k, int b) {
        // Write your code here
        //k-> iten anna did not eat.
        //b-> anna paid

        AtomicInteger asum= new AtomicInteger();

        IntStream.range(0, bill.size()).filter(i-> i!=k).forEach(i-> asum.addAndGet(bill.get(i)));
        System.out.println(asum);
        //or
        int total= IntStream.range(0, bill.size()).filter(i-> i!=k).map(i-> bill.get(i)).sum();
        System.out.println(total);

        AtomicInteger sum=new AtomicInteger();
        for (int i=0; i< bill.size();i++) {
            if(i!=k){
                sum.addAndGet(bill.get(i));
            }
        }
        if(sum.get() /2 ==b){
            System.out.println("Bon Appetit");
        } else {
            System.out.println(b-(sum.get() /2));
        }

}

    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        System.out.println("Inout sockMerchant " +ar);
        Map<Integer, Long> frequencyMap = ar.stream().collect(Collectors.groupingBy(a->a, Collectors.counting()));
        System.out.println(frequencyMap);
        int totalPairs = frequencyMap.entrySet().stream().map(Map.Entry::getValue).map(v-> (int) (v/2)).reduce(0,(a,b)-> a+b);
        System.out.println("total pairs: "+totalPairs);
        return n;
    }

    public static int pageCount(int n, int p) {
        // Write your code here
        int totalFlip= n/2;
        int frontFlip = p/2;
        int backFlip = totalFlip - frontFlip;
        return frontFlip> backFlip? backFlip: frontFlip;
    }
}



