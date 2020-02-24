package com.xyzcorp.javalambdas;

import com.xyzcorp.models.Employee;
import com.xyzcorp.models.Manager;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaLambdasTest {

    @Test
    void groceriesTest() {
        List<String> groceries = List.of("Milk", "Eggs", "Naan", "Tea",
            "Coffee",
            "Spinach", "Celery", "Tomatoes, Arugula");

        List<String> sortedGroceries =
            groceries.stream().sorted().collect(Collectors.toList());

        Stream<String> stringStream =
            IntStream.range(0, groceries.size() - 1)
                     .boxed()
                     .map(i -> String.format("%d. %s", i + 1,
                         sortedGroceries.get(i)));

        //I wish I didn't have to collect during development
        String collect = stringStream.collect(Collectors.joining("\n"));
        System.out.println(collect);
    }


    @Test
    void testCreditCards() {
        Random random = new Random();
        Supplier<Integer> supplier = () -> 1 + random.nextInt(4);
        String result =
            Stream.generate(supplier).map(Object::toString).limit(4).collect(Collectors.joining("-"));
        System.out.println(result);
    }

    @Test
    void testCreditCardsWorkaround() {
        Random random = new Random();
        Supplier<Integer> supplier = () -> 1000 + random.nextInt(9999 - 1000);
        String result =
            Stream.generate(supplier).map(Object::toString).limit(4).collect(Collectors.joining("-"));
        System.out.println(result);
    }

    @Test
    void testEmployeeSalaries() {
        Employee brian = new Employee("Brian", "Sletten", 40000);
        Employee chandra = new Employee("Chandra", "Gunter", 42000);
        Employee nate = new Employee("Nate", "Schutta", 43000);
        Employee raju = new Employee("Raju", "Ghandi", 43000);

        Manager venkat = new Manager("Venkat", "Subramaniam", 50000,
            java.util.List.of(brian, chandra, nate, raju));

        Employee james = new Employee("James", "Ward", 44000);
        Employee lyndsey = new Employee("Lyndsey", "Padget", 45000);
        Employee ken = new Employee("Ken", "Kousen", 43000);
        Employee angie = new Employee("Angie", "Jones", 45000);

        Manager neal = new Manager("Neal", "Ford", 50000,
            java.util.List.of(james, lyndsey, ken, angie));

        int result = Stream.of(venkat, neal)
                           .flatMap(x ->
                               Stream.concat(Stream.of(x),
                                   x.getEmployeeList().stream()))
                           .mapToInt(Employee::getSalary)
                           .sum();
        System.out.println(result);
    }

    @Test
    void testErrorHandling() {
        Stream<Integer> integerStream =
            Stream.of(10, 40, 90, 0, 120).flatMap(x -> {
                try {
                    return Stream.of(4000 / x);
                } catch (Exception e) {
                    return Stream.empty();
                }
            });

        integerStream.forEach(System.out::println);
    }

    @Test
    void testSafeMap() {
        String result = Map.of(1, "One", 2, "Two", 3, "Three").get(4);
        System.out.println(result);
    }

    public boolean isPrime(int n) {
        if (n == 1) return false;
        else
            return IntStream
                .range(2, (int)(Math.sqrt(n) + 1))
                .allMatch(i -> n % i != 0);
    }

    @Test
    public void testForAll() {
        System.out.println(isPrime(2));
        System.out.println(isPrime(4));
        System.out.println(isPrime(5));
        System.out.println(isPrime(7));
        System.out.println(isPrime(11));
        System.out.println(isPrime(5449));
        System.out.println(isPrime(5550));
    }

}
