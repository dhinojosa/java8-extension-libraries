package com.xyzcorp.streamx;

import one.util.streamex.EntryStream;
import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StreamExTest {

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

        EntryStream<String, Integer> result =
            StreamEx.of(ipsumLorem.split("\\s+"))
                    .mapToEntry(List::of)
                    .mapValues(List::size);

        System.out.println(result);
    }


    //Need an implementation, since it is not available.
    //But it is more complicated than that and it has to do
    //with parallel processing.
    private static <T> StreamEx<List<T>> sliding(StreamEx<List<T>> input, int size, int skip) {
        return input.headTail((head, tail) -> head.size() == size ? sliding(
            tail.mapFirst(next -> StreamEx.of(head.subList(skip, size), next).toFlatList(l -> l)), size, skip).prepend(head):
            sliding(tail.mapFirst(next -> StreamEx.of(head, next).toFlatList(l -> l)), size, skip));
    }

    public static <T> Function<StreamEx<T>, StreamEx<List<T>>> sliding(int size) {
        return s -> sliding(s.map(Collections::singletonList), size, 1);
    }

    public static <T> Function<StreamEx<T>, StreamEx<List<T>>> sliding(int size, int skip) {
        return s -> sliding(s.map(Collections::singletonList), size, skip);
    }

    /**
     * StreamX Weakness - No error types
     **/
    public Optional<String> grid(int size) {
        if (size < 0)
            return Optional.empty(); //Java Optional
        else {
            var squared = size * size;
            var stringSize = Integer.toString(squared).length();
            var slideBySize = StreamExTest.<String>sliding(size, size);

            StreamEx<String> it =
                StreamEx.iterate(1, i -> i + 1)
                        .limit(squared)
                        .map(n -> {
                            var initString = String.format(
                                "%%0%dd", stringSize);
                            return String.format(initString,
                                n);
                        });

            String s3 = slideBySize
                .apply(it)
                .map(StreamEx::of)
                .map(s -> s.joining(","))
                .joining("\n");

            return Optional.of(s3);
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
            return IntStreamEx
                .range(2, (int) (Math.sqrt(n) + 1))
                .allMatch(value -> n % value == 0);
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
