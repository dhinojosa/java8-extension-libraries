package com.xyzcorp.eclipsecollections;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EclipseCollectionsTest {
    @Test
    void testGroceries() {
        List<String> groceries = List.of("Milk", "Eggs", "Naan", "Tea",
            "Coffee",
            "Spinach", "Celery", "Tomatoes, Arugula");

        ImmutableList<String> immutableList = Lists.immutable.ofAll(groceries);
        String result = immutableList
            .toSortedList()
            .zipWithIndex()
            .collect(p -> String.format("%d. %s", p.getTwo() + 1, p.getOne()))
            .makeString("\n");
        System.out.println(result);
    }

    @Test
    void testCreditCards() {

    }

    @Test
    void testExceptionHandling() {
        ImmutableList<Integer> result = Lists.immutable.of(10, 40, 90, 0,
            120).flatCollect(x -> {
            try {
                return Lists.immutable.of(4000 / x);
            } catch (Exception e) {
                return Lists.immutable.empty();
            }
        });

        System.out.println(result);
    }

    @Test
    void testGrouping() {
        String ipsumLorem = "Lorem ipsum dolor sit amet, consectetur " +
            "adipiscing elit, " +
            "sed do " +
            "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut " +
            "enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
            "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor " +
            "in reprehenderit in voluptate velit esse cillum dolore eu fugiat" +
            " nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
            " sunt in culpa qui officia deserunt mollit anim id est laborum.";


//        ImmutableListMultimap<String, String> multimap =
//            Lists.immutable.of(ipsumLorem.split("\\s+")).groupBy(x -> x);
//
//        multimap.collectValues(v ->)
//
//
//        System.out.println(result);
    }

    //TODO: fix there is no until
    public boolean isPrime(int n) {
        if (n == 1) return false;
        else
            return
                IntInterval
                    .fromTo(2, ((int) (Math.sqrt(n) + 1)) -1)
                    .allSatisfy(value -> n % value == 0);
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

