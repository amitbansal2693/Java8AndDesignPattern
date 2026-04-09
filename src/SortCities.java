import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

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

    private static void filterByMinimumDistance(int min) {
        List<Entry<String, Integer>> filtered = cityDistances.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= min)
                .sorted(Comparator.comparing(Entry::getValue))
                .collect(Collectors.toList());

        printEntries(filtered);
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
