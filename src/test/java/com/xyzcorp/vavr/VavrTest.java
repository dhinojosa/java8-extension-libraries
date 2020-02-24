package com.xyzcorp.vavr;

import com.xyzcorp.models.Employee;
import com.xyzcorp.models.Manager;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;
import io.vavr.collection.Traversable;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.Random;


public class VavrTest {
    @Test
    void testGroceries() {
        java.util.List<String> groceries =
            java.util.List.of("Milk", "Eggs", "Naan", "Tea", "Coffee",
                "Spinach", "Celery", "Tomatoes, Arugula");

        String result = List.ofAll(groceries)
                            .sorted()
                            .zipWithIndex()
                            .map(t -> t.map2(v -> v + 1))
                            .map(t -> String.format("%d. " +
                                "%s", t._2, t._1))
                            .mkString("\n");

        System.out.println(result);
    }

    @Test
    void testCreditCards() {
        Random random = new Random();
        String creditCard = Stream.fill(16, () -> random.nextInt(10))
                                  .sliding(4, 4)
                                  .map(Traversable::mkString)
                                  .mkString("-");
        System.out.println(creditCard);
    }

    @Test
    void testCreditCardsAlternate() {

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

        Number sum = List.of(neal, venkat)
                         .flatMap(man ->
                             Stream.concat(man.getEmployeeList(),
                                 Stream.of(man)))
                         .map(Employee::getSalary)
                         .sum();

        System.out.println(sum);
    }


    @Test
    void testErrorHandling() {
        Stream<Try<Integer>> map = Stream.of(10, 40, 90, 0, 120)
                                         .map(x -> Try.of(() -> 400 / x));
        Stream<Integer> integers = map.flatMap(x -> x);
        System.out.println(integers.toList());
    }

    @Test
    void testErrorHandlingRefactored() {
        Stream<Integer> integers = Stream.of(10, 40, 90, 0, 120)
                                         .flatMap(x -> Try.of(() -> 400 / x));

        System.out.println(integers.toList());
    }


    @Test
    public void testGrouping() {
        String ipsumLorem = "Lorem ipsum dolor sit amet, consectetur " +
            "adipiscing elit, " +
            "sed do " +
            "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut " +
            "enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
            "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor " +
            "in reprehenderit in voluptate velit esse cillum dolore eu fugiat" +
            " nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
            " sunt in culpa qui officia deserunt mollit anim id est laborum.";


        Map<String, Integer> result =
            Stream.of(ipsumLorem.split("\\s+"))
                  .groupBy(x -> x)
                  .mapValues(Traversable::size);
        System.out.println(result);
    }

    public Try<String> grid(int size) {
        if (size < 0)
            return Try.failure(new RuntimeException("Size is less than zero"));
        else {
            var squared = size * size;
            var stringSize = Integer.toString(squared).length();
            return Try.success(
                Stream.from(1)
                      .take(squared)
                      .map(n -> {
                          var initString = String.format("%%0%dd", stringSize);
                          return String.format(initString, n);
                      })
                      .grouped(size)
                      .map(i -> i.mkString(","))
                      .mkString("\n"));
        }
    }

    @Test
    void testGrid() {
        var grid2 = grid(2).getOrElse("Unable to make grid");
        System.out.println(grid2);
        System.out.println();
        var grid10 = grid(10).getOrElse("Unable to make grid");
        System.out.println(grid10);
        System.out.println();
        var grid20 = grid(20).getOrElse("Unable to make grid");
        System.out.println(grid20);
    }

    public boolean isPrime(int n) {
        if (n == 1) return false;
        else
            return Stream
                    .range(2, (int)(Math.sqrt(n) + 1))
                    .forAll(i -> n % i != 0);
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
