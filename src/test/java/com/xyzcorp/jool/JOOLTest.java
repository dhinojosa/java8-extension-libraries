package com.xyzcorp.jool;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class JOOLTest {

    //Let's talk about those joins

    @Test
    void testGroceries() {
        List<String> groceries = List.of("Milk", "Eggs", "Naan", "Tea",
            "Coffee",
            "Spinach", "Celery", "Tomatoes, Arugula");
        var result =
            Seq.seq(groceries)
               .sorted()
               .zipWithIndex()
               .map(t -> t.map2(i -> i + 1))
               .map(t -> String.format("%d. %s", t.v2, t.v1))
               .toString("\n");
        System.out.println(result);
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


        Seq<Tuple2<String, Long>> result =
            Seq.of(ipsumLorem.split("\\s+"))
               .grouped(x -> x)
               .map(t -> t.map2(Seq::count));
        System.out.println(result.toMap(t -> t.v1, t -> t.v2));
    }

    public Optional<String> grid(int size) {
        if (size < 0)
            return Optional.empty();
        else {
            var squared = size * size;
            var stringSize = Integer.toString(squared).length();
            return Optional.of(
                Seq.iterate(1, x -> x + 1)
                   .take(squared)
                   .map(n -> {
                       var initString = String.format("%%0%dd", stringSize);
                       return String.format(initString, n);
                   })
                   .sliding(size)
                   .map(w -> w.toString(","))
                   .toString("\n"));
        }
    }

    @Test
    void testGrid() {
        var grid2 = grid(2).orElse("Unable to make grid");
        System.out.println(grid2);
        System.out.println();
        var grid10 = grid(10).orElse("Unable to make grid");
        System.out.println(grid10);
        System.out.println();
        var grid20 = grid(20).orElse("Unable to make grid");
        System.out.println(grid20);
    }

    public boolean isPrime(int n) {
        if (n == 1) return false;
        else
            return
                Seq.range(2, (int) (Math.sqrt(n) + 1))
                   .allMatch(value -> n % value != 0);
    }

    @Test
    public void testForAll() {
        System.out.println(isPrime(2));
        System.out.println(isPrime(4));
        System.out.println(isPrime(5));
        System.out.println(isPrime(7));
        System.out.println(isPrime(5449));
        System.out.println(isPrime(5550));
    }

}
