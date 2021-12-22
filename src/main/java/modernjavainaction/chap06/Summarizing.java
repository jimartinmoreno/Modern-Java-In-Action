package modernjavainaction.chap06;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.function.BinaryOperator;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static modernjavainaction.chap06.Dish.menu;

public class Summarizing {

    public static void main(String... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());

        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Menu statistics by Type: " + calculateMenuStatisticsByType());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }

    private static long howManyDishes() {
        return menu.stream().collect(counting());
    }


    private static int calculateTotalCalories() {
        menu.stream().mapToInt(Dish::getCalories).sum();
        menu.stream().collect(summarizingInt(Dish::getCalories)).getSum();
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static Double calculateAverageCalories() {
        menu.stream().mapToInt(Dish::getCalories).average();
        menu.stream().collect(summarizingInt(Dish::getCalories)).getAverage();
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    private static Map<Dish.Type, IntSummaryStatistics> calculateMenuStatisticsByType() {
        return menu.stream().collect(groupingBy(Dish::getType, summarizingInt(Dish::getCalories)));
    }

    private static String getShortMenu() {
        return menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(", "));
    }

}
