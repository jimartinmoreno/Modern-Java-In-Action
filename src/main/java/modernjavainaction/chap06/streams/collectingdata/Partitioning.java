package modernjavainaction.chap06.streams.collectingdata;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static modernjavainaction.chap06.streams.collectingdata.Dish.menu;

public class Partitioning {

    public static void main(String... args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian2());
        System.out.println("Most caloric amount by vegetarian: " + mostCaloricAmountPartitionedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType, toCollection(LinkedList::new))));
    }

    private static Object mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }

    private static Object mostCaloricPartitionedByVegetarian2() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                reducing(BinaryOperator.maxBy(comparingInt(Dish::getCalories))),
                                Optional::get)));
    }


    private static Object mostCaloricAmountPartitionedByVegetarian() {
        return menu.stream().collect(
                groupingBy(Dish::isVegetarian,
                        collectingAndThen(
                                summarizingInt(Dish::getCalories), IntSummaryStatistics::getMax)));

    }

}
