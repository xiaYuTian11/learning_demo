package com.demo.chapter2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author TMW
 * @since 2020/1/6 11:04
 */
public class Demo02 {
    private static List<Transaction> transactions;

    @BeforeAll
    public static void init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test01() {
        List<Transaction> collect = transactions.stream().filter(transaction -> Objects.equals(transaction.getYear(), 2011))
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect1 = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(collect1);

        Set<String> collect2 = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(Collectors.toSet());
        System.out.println(collect2);

        List<Trader> cambridge = transactions.stream().map(Transaction::getTrader).distinct().filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName, String::compareTo)).collect(Collectors.toList());
        System.out.println(cambridge);

        String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("", (x, y) -> x + y);
        System.out.println(reduce);

        String collect3 = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(Collectors.joining());
        System.out.println(collect3);

        boolean milan = transactions.stream().map(transaction -> transaction.getTrader().getCity()).anyMatch(str -> str.equals("Milan"));
        System.out.println(milan);
        boolean milan1 = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milan1);

        int cambridge1 = transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).mapToInt(Transaction::getValue).sum();
        System.out.println(cambridge1);

        int asInt = transactions.stream().mapToInt(Transaction::getValue).max().getAsInt();
        System.out.println(asInt);

        int asInt1 = transactions.stream().mapToInt(Transaction::getValue).reduce(Integer::max).getAsInt();
        System.out.println(asInt1);

        int asInt2 = transactions.stream().mapToInt(Transaction::getValue).min().getAsInt();
        System.out.println(asInt2);

        int asInt3 = transactions.stream().mapToInt(Transaction::getValue).min().getAsInt();
        System.out.println(asInt3);

        List<double[]> collect4 = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0).collect(Collectors.toList());
        collect4.forEach(doubles -> {
                    Arrays.stream(doubles).forEach(System.out::println);
                }
        );

    }

    @Test
    public void test02() throws IOException {
        Path path = Paths.get("D:\\java\\ideaWorkingSpace\\demo\\java8_action\\src\\test\\resources\\data.txt");
        List<String> stringList = Files.lines(path, Charset.defaultCharset()).flatMap(line -> Arrays.stream(line.split(" "))).distinct().collect(Collectors.toList());
        // System.out.println(stringList);
        stringList.forEach(System.out::println);
    }

    @Test
    public void test03() {
        List<Integer> collect = Stream.iterate(0, n -> n + 2).limit(100).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test04() {
        List<Integer> collect = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10).map(t -> t[0]).collect(Collectors.toList());
        System.out.println(collect);
    }

}
