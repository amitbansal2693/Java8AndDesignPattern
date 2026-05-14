package com.practice;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *  reduce() in Java – Deep Dive (Hierarchy Style). Returns single output Optional<T>
 *
 *      ├── Purpose:
 * │   ├── Reduces all elements into a **single result**
 * │   ├── Similar to manually looping and accumulating
 * │
 * ├── Types of reduce():
 * │
 */
public class ReduceFunctionExamples {

    // Mid-to-advanced practice set.
    // Each method below is intentionally left incomplete so you can solve it.
    // Use the sample helper methods as ready-made arguments while practicing.

    public static void main(String[] args) {
        // You can test your implementations here by calling the methods with sample data.
        // For example:
         System.out.println(sumOfSquares(sampleNumbers()));

    }
    /** Sample call: sumOfSquares(sampleNumbers()) */
    public static int sumOfSquares(List<Integer> numbers) {
        numbers.stream().reduce(0, (n,m)-> n*m);
        return 0;
    }

    /** Sample call: productOfDistinctPositiveNumbers(sampleNumbers()) */
    public static int productOfDistinctPositiveNumbers(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO: solve using filter + distinct + reduce");
    }

    /** Sample call: longestWord(sampleWords()) */
    public static Optional<String> longestWord(List<String> words) {
        throw new UnsupportedOperationException("TODO: solve using reduce");
    }

    /** Sample call: joinUniqueSortedWords(sampleWords()) */
    public static String joinUniqueSortedWords(List<String> words) {
        throw new UnsupportedOperationException("TODO: solve using distinct + sorted + joining");
    }

    /** Sample call: wordFrequency(sampleWords()) */
    public static Map<String, Long> wordFrequency(List<String> words) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + counting");
    }

    /** Sample call: characterFrequency(sampleSentence()) */
    public static Map<Character, Long> characterFrequency(String input) {
        throw new UnsupportedOperationException("TODO: solve using chars + groupingBy");
    }

    /** Sample call: groupWordsByLength(sampleWords()) */
    public static Map<Integer, List<String>> groupWordsByLength(List<String> words) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy(String::length)");
    }

    /** Sample call: partitionPalindromes(samplePalindromeWords()) */
    public static Map<Boolean, List<String>> partitionPalindromes(List<String> words) {
        throw new UnsupportedOperationException("TODO: solve using partitioningBy");
    }

    /** Sample call: averageSalaryByDepartment(sampleEmployees()) */
    public static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + averagingDouble");
    }

    /** Sample call: totalSalaryByDepartment(sampleEmployees()) */
    public static Map<String, Double> totalSalaryByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + summingDouble");
    }

    /** Sample call: highestPaidEmployeeByDepartment(sampleEmployees()) */
    public static Map<String, Optional<Employee>> highestPaidEmployeeByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + maxBy");
    }

    /** Sample call: uniqueCitiesByDepartment(sampleEmployees()) */
    public static Map<String, Set<String>> uniqueCitiesByDepartment(List<Employee> employees) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + mapping + toSet");
    }

    /** Sample call: studentNamesByDepartment(sampleStudents()) */
    public static Map<String, List<String>> studentNamesByDepartment(List<Student> students) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + mapping");
    }

    /** Sample call: countStudentsByGrade(sampleStudents()) */
    public static Map<String, Long> countStudentsByGrade(List<Student> students) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + counting");
    }

    /** Sample call: topScorerByDepartment(sampleStudents()) */
    public static Map<String, Optional<Student>> topScorerByDepartment(List<Student> students) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + maxBy");
    }

    /** Sample call: revenueByCategory(sampleOrders()) */
    public static Map<String, Double> revenueByCategory(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: solve using groupingBy + summingDouble");
    }

    /** Sample call: topCategoryByRevenue(sampleOrders()) */
    public static Optional<String> topCategoryByRevenue(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: solve using revenue aggregation + max");
    }

    /** Sample call: orderCountByStatusAndCity(sampleOrders()) */
    public static Map<String, Map<String, Long>> orderCountByStatusAndCity(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: solve using nested groupingBy + counting");
    }

    /** Sample call: secondHighestDistinctNumber(sampleNumbers()) */
    public static Optional<Integer> secondHighestDistinctNumber(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO: solve using distinct + sorted/reduce");
    }

    /** Sample call: summarizeNumbers(sampleNumbers()) */
    public static IntSummaryStatistics summarizeNumbers(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO: solve using mapToInt + summaryStatistics");
    }

    public static List<Integer> sampleNumbers() {
        return List.of(4, 7, 2, 9, 2, 5, 8, 0, -3, 7);
    }

    public static List<String> sampleWords() {
        return List.of("stream", "reduce", "collector", "java", "level", "stream", "radar");
    }

    public static String sampleSentence() {
        return "java stream reduce practice";
    }

    public static List<String> samplePalindromeWords() {
        return List.of("level", "java", "radar", "stream", "madam", "code");
    }

    public static List<Employee> sampleEmployees() {
        return List.of(
                new Employee("Amit", "Engineering", "Pune", 120000, 7),
                new Employee("Sara", "Engineering", "Bengaluru", 145000, 9),
                new Employee("Ravi", "Finance", "Pune", 98000, 5),
                new Employee("Neha", "Finance", "Mumbai", 110000, 6),
                new Employee("John", "HR", "Mumbai", 87000, 4),
                new Employee("Priya", "HR", "Pune", 91000, 5)
        );
    }

    public static List<Student> sampleStudents() {
        return List.of(
                new Student("Anu", "CS", "A", 91),
                new Student("Karan", "CS", "B", 78),
                new Student("Meera", "IT", "A", 88),
                new Student("Dev", "IT", "C", 67),
                new Student("Ishita", "ECE", "B", 81),
                new Student("Rohan", "ECE", "A", 93)
        );
    }

    public static List<Order> sampleOrders() {
        return List.of(
                new Order(101, "Books", "Pune", "DELIVERED", 850.0),
                new Order(102, "Electronics", "Mumbai", "PENDING", 54000.0),
                new Order(103, "Books", "Pune", "DELIVERED", 1200.0),
                new Order(104, "Groceries", "Bengaluru", "DELIVERED", 2100.0),
                new Order(105, "Electronics", "Mumbai", "DELIVERED", 32000.0),
                new Order(106, "Groceries", "Pune", "CANCELLED", 900.0)
        );
    }

    public static class Employee {
        private final String name;
        private final String department;
        private final String city;
        private final double salary;
        private final int experienceYears;

        Employee(String name, String department, String city, double salary, int experienceYears) {
            this.name = name;
            this.department = department;
            this.city = city;
            this.salary = salary;
            this.experienceYears = experienceYears;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public String getCity() {
            return city;
        }

        public double getSalary() {
            return salary;
        }

        public int getExperienceYears() {
            return experienceYears;
        }

        @Override
        public String toString() {
            return name + "{" + department + ", " + city + ", salary=" + salary + ", exp=" + experienceYears + "}";
        }
    }

    public static class Student {
        private final String name;
        private final String department;
        private final String grade;
        private final int marks;

        Student(String name, String department, String grade, int marks) {
            this.name = name;
            this.department = department;
            this.grade = grade;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public String getGrade() {
            return grade;
        }

        public int getMarks() {
            return marks;
        }

        @Override
        public String toString() {
            return name + "{" + department + ", grade=" + grade + ", marks=" + marks + "}";
        }
    }

    public static class Order {
        private final int orderId;
        private final String category;
        private final String city;
        private final String status;
        private final double amount;

        Order(int orderId, String category, String city, String status, double amount) {
            this.orderId = orderId;
            this.category = category;
            this.city = city;
            this.status = status;
            this.amount = amount;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCategory() {
            return category;
        }

        public String getCity() {
            return city;
        }

        public String getStatus() {
            return status;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Order{" + orderId + ", " + category + ", " + city + ", " + status + ", amount=" + amount + "}";
        }
    }
}
