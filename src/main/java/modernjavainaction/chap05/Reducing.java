package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static modernjavainaction.chap04.Dish.menu;

public class Reducing {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        Optional<String> greeting = Optional.<String>of("name");

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum: " + sum2);
        int sum3 = numbers.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum: " + sum3);
        int sum3_1 = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum3_1);


        Optional<Integer> max_ = numbers.stream().reduce((x, y) -> x > y ? x : y);
        System.out.println("Min: " + max_.get());
        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println("Max: " + max);
        int max4 = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        System.out.println("Max: " + max4);
        OptionalInt max4_1 = numbers.stream().mapToInt(Integer::intValue).max();
        System.out.println("Max: " + max4_1.getAsInt());
        Optional<Integer> max4_2 = numbers.stream().collect(Collectors.maxBy(Comparator.comparingInt(Integer::intValue)));
        System.out.println("Max: " + max4_2.get());
        Optional<Integer> max4_3 = numbers.stream().max(Comparator.comparingInt(Integer::intValue));
        System.out.println("Max: " + max4_3.get());

        Optional<Integer> min = numbers.stream().reduce((x, y) -> x < y ? x : y);
        System.out.println("Min: " + min.get());
        Optional<Integer> min_ = numbers.stream().reduce(Integer::min);
        System.out.println("Min: " + min_.get());
        int min5 = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMin();
        System.out.println("Min: " + min5);
        OptionalInt min5_1 = numbers.stream().mapToInt(Integer::intValue).min();
        System.out.println("Min: " + min5_1.getAsInt());
        Optional<Integer> min5_2 = numbers.stream().min(Comparator.comparingInt(Integer::intValue));
        System.out.println("Min: " + min5_2.get());
        Optional<Integer> min4_2 = numbers.stream().collect(Collectors.minBy(Comparator.comparingInt(Integer::intValue)));
        System.out.println("Min: " + min4_2.get());

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);

        System.out.println("Number of dishes:" + menu.stream().count());
    }
}
