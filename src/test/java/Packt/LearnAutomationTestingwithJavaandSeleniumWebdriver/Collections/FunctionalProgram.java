package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalProgram {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(7);
        list.add(9);

        // map through la list Integer
        list.forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        list.stream().filter(e -> e % 2 == 1).forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        list.stream().filter(e -> e % 2 == 0).forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        List<Integer> numbers = List.of(3, 5, 3, 213, 45, 5, 7);
        numbers.stream().sorted().forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        numbers.stream().distinct().forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        numbers.stream().distinct().sorted().forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        IntStream.range(1, 11).forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        Random random = new Random();
        IntStream.generate(() -> random.nextInt(100) + 1).limit(3).forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        IntStream.range(1, 11).map(e -> e * e).forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        List.of("Apple", "Ant", "Bat").stream().map(el -> el.toLowerCase()).forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        List.of("Apple", "Ant", "Bat").stream().map(el -> el.toUpperCase()).forEach(e -> System.out.print(" " + e));
        System.out.println("\n");
        List mylist = List.of(23, 12, 34, 53).stream().filter(e -> e % 2 == 0).collect(Collectors.toList());
        System.out.println("myList collections = " + mylist);
        System.out.println("\n");
        List mylist2 = Collections.singletonList(List.of(23, 12, 34, 53).stream().filter(n -> n % 2 == 0).max((n1, n2) -> Integer.compare(n1, n2)));
        System.out.println("myList collections compared= " + mylist);
    }
}
