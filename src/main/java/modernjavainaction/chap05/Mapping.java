package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static modernjavainaction.chap04.Dish.menu;

public class Mapping {

    public static void main(String... args) {
        // map
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .toList();
        System.out.println(dishNames);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .toList();
        System.out.println(wordLengths);

        // flatMap
        words.stream()
                .flatMap(line -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .toList();

        pairs.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));
        System.out.println();

        // Creating a list of Prime Numbers
        List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11, 13);

        // Creating a list of Odd Numbers
        List<Integer> OddNumbers = Arrays.asList(1, 3, 5);

        // Creating a list of Even Numbers
        List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8);

        List<List<Integer>> listOfListofInts =
                Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers);

        System.out.println("The Structure before flattening is : " + listOfListofInts);

        // Using flatMap for transformating and flattening.
        List<Integer> listofInts = listOfListofInts.stream()
                .flatMap(list -> list.stream())
                .toList();

        System.out.println("The Structure after flattening is : " + listofInts);

        // Using flatMap for transformating and flattening.
        List<Integer> listofInts2 = listOfListofInts.stream()
                .flatMap(Collection::stream)
                .toList();

        System.out.println("The Structure after flattening is : " + listofInts2);
    }

}
