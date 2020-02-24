package com.xyzcorp.eclipsecollections;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EclipseCollectionsTest {

    /**
     * Take this list of groceries, sort them, and make it so that they look
     * like a shopping list in the following format:
     *
     * 1. Apples
     * 2. Celery
     * 3. Coffee
     * 4. Eggs
     * 5. Milk
     * 6. Naan
     * 7. Spinach
     * 8. Tea
     * 9. Tomatoes
     */
    @Test
    void testGroceries() {
        List<String> groceries = List.of("Milk", "Eggs", "Naan", "Tea",
            "Coffee",
            "Spinach", "Celery", "Tomatoes", "Apples");

        ImmutableList<String> immutableList = Lists.immutable.ofAll(groceries);
        String result = immutableList
            .toSortedList()
            .zipWithIndex()
            .collect(p -> String.format("%d. %s", p.getTwo() + 1, p.getOne()))
            .makeString("\n");
        System.out.println(result);
    }

    /**
     * Create fake credit card numbers, all numbers random, in this format
     * 3003-1230-1203-1030
     */
    @Test
    void testCreditCards() {

    }

    /**
     * The best way to handle errors given evalulating 4000/x where x is a list
     * (10, 40, 90, 0, 120)
     */
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

    /**
     * Standard Word Count
     */
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
    }


    /**
     * Each grid is given a number, and then results in an n x n grid of that
     * number.  All numbers should be prepended with zeros to make it uniform
     *
     * For example: grid(2)
     * 1,2
     * 3,4
     *
     * For example: grid(10)
     * 001,002,003,004,005,006,007,008,009,010
     * 011,012,013,014,015,016,017,018,019,020
     * 021,022,023,024,025,026,027,028,029,030
     * 031,032,033,034,035,036,037,038,039,040
     * 041,042,043,044,045,046,047,048,049,050
     * 051,052,053,054,055,056,057,058,059,060
     * 061,062,063,064,065,066,067,068,069,070
     * 071,072,073,074,075,076,077,078,079,080
     * 081,082,083,084,085,086,087,088,089,090
     * 091,092,093,094,095,096,097,098,099,100
     */
    @Test
    public void testGrid() {

    }


    /**
     * Find the combinations of words. Given:
     * List("White", "Red", "Purple", "Red", "White", "Green"))
     *
     * The result should be a List of 3-tuples:
     *
     * List((White,Red,4), (White,Purple,2), (White,Green,2), (Red,Purple,2),
     * (Red,Green,2), (Purple,Green,1), (Green,Purple,1), (Purple,Red,2),
     * (Green,Red,2), (Red,White,4), (Purple,White,2), (Green,White,2))
     *
     */

    @Test
    public void testWordCombos() {

    }


    //TODO: fix there is no Range.until
    public boolean isPrime(int n) {
        if (n == 1) return false;
        else
            return
                IntInterval
                    .fromTo(2, ((int) (Math.sqrt(n) + 1)) -1)
                    .allSatisfy(value -> n % value == 0);
    }

    /**
     * Test forAll, but in EC it is called allSatisfy.  This will calculate
     * all the prime numbers
     */
    @Test
    public void testForAll() {
        System.out.println(isPrime(2)); //Should be true
        System.out.println(isPrime(4));
        System.out.println(isPrime(5));
        System.out.println(isPrime(7));
        System.out.println(isPrime(5449));
        System.out.println(isPrime(5550));
    }
}

