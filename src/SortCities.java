import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;
import java.util.stream.IntStream;

public class SortCities {
    private static final Map<String, Integer> cityDistances = new HashMap<>();

    static {
        cityDistances.put("Delhi-Dubai", 10);
        cityDistances.put("Mumbai-Shanghai", 25);
        cityDistances.put("Delhi-Mumbai", 15);
        cityDistances.put("Berlin-Paris", 8);
        cityDistances.put("New York-London", 35);
        cityDistances.put("Tokyo-Seoul", 12);
        cityDistances.put("Sydney-Auckland", 20);
        cityDistances.put("Toronto-Vancouver", 40);
        cityDistances.put("Cairo-Riyadh", 18);
        cityDistances.put("Bangkok-Singapore", 22);
    }

    /**
     * Sorted Teachniques:
     * Sorted()
     * Function can be passed 2 types
     * 1. Entry.comparingByValue()
     * 2. Comparator.comparing(Entry::getKey)
     * @param args
     */
    public static void main(String[] args) {

        //Common steps: a; convert map into Set of MAP
        //cityDistances.entrySet();


        System.out.println("Original:" +cityDistances);
        //1. sort by distance
        System.out.println(" ======== 1.sort by distance==============");
        List<Entry<String, Integer>> sorted =cityDistances.entrySet().stream()
                .sorted(Comparator.comparing(Entry::getValue)) //sort here
                .collect(Collectors.toList());
        printEntries(sorted);
        System.out.println("\n  ======== 2. sort by distance 2==============");

        sorted=cityDistances.entrySet().stream().sorted(Entry.comparingByValue()).toList();
        printEntries(sorted);

        //Sort by key
        System.out.println(" \n ======== 3. Sort by key:========");
                sorted = cityDistances.entrySet().stream()
                        .sorted(Comparator.comparing(Entry::getKey)).toList();
        printEntries(sorted);
        System.out.println("  ======== 4. Sort by key: ========");
        sorted=cityDistances.entrySet().stream().sorted(Entry.comparingByKey()).toList();
        printEntries(sorted);


        countApplesAndOranges(7,11,5,15,List.of(-2,2,1), List.of(5,-6));
        breakingRecords( List.of(10 ,5, 20 ,20, 4 ,5, 2, 25, 1));
        birthday(List.of(1,2,1,3,2), 3,2);
    }

    private static void sortByDistance(boolean ascending) {
        Comparator<Entry<String, Integer>> comparator = Comparator.comparing(Entry::getValue);
        if (!ascending) {
            comparator = comparator.reversed();
        }

        List<Entry<String, Integer>> sorted = cityDistances.entrySet()
                .stream()
                .sorted(comparator.thenComparing(Entry::getKey))
                .collect(Collectors.toList());

        printEntries(sorted);
    }

    private static void sortByCityName() {
        List<Entry<String, Integer>> sorted = cityDistances.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .collect(Collectors.toList());

        printEntries(sorted);
    }

    public static void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
        // Write your code here

        apples.stream().map(ap -> ap+a).filter(ap-> ap>=s && ap<=t).collect(Collectors.toList()).size();
        apples.stream().map(ap -> ap+a).toList();

    }
    private static void filterByMinimumDistance(int min) {
        List<Entry<String, Integer>> filtered = cityDistances.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= min)
                .sorted(Comparator.comparing(Entry::getValue))
                .collect(Collectors.toList());

        printEntries(filtered);
    }

    public static int birthday(List<Integer> s, int d, int m) {
//d=sum of consecutive integers, birth day
        //m=month - length of segment
        int count = (int) IntStream.rangeClosed(0, s.size() - m)
                .filter(i ->
                        s.subList(i, i + m)
                                .stream()
                                .mapToInt(Integer::intValue)
                                .sum() == d
                )

                .count();
        System.out.println("count: "+count);
        return count;
    }

    public static List<Integer> breakingRecords(List<Integer> scores) {
        // Write your code here
//10 5 20 20 4 5 2 25 1
        List<Integer> result = new ArrayList<>(Arrays.asList(0,0));

        int max = scores.get(0);
        int min = scores.get(0);
        for (Integer i : scores.stream().skip(1).collect(Collectors.toList())) {
            if (i > max) {
                result.set(0, result.get(0) + 1);
                max=i;
            }
            if (i < min) {
                result.set(1, result.get(1) + 1);
                min=i;
            }
        }
        System.out.println(result);
        return result;
    }
    private static void printEntries(Collection<Entry<String, Integer>> entries) {
        if (entries.isEmpty()) {
            System.out.println("No entries to display.");
            return;
        }

        for (Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + "km");
        }
    }
}
