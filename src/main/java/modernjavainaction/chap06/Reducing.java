package modernjavainaction.chap06;

import java.util.Comparator;
import java.util.function.BinaryOperator;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static modernjavainaction.chap06.Dish.menu;

public class Reducing {

    public static void main(String... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator2());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator3());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator4());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    private static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithoutCollectors() {
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }

    private static Dish findMostCaloricDish() {
        menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
        return menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2).get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static Dish findMostCaloricDishUsingComparator2() {
        return menu.stream().collect(maxBy(comparingInt(Dish::getCalories))).get();
    }

    private static Dish findMostCaloricDishUsingComparator3() {
        return menu.stream().reduce(BinaryOperator.maxBy(comparingInt(Dish::getCalories))).get();
    }

    private static Dish findMostCaloricDishUsingComparator4() {
        return menu.stream().sorted(comparingInt(Dish::getCalories).reversed()).findFirst().get();
    }
    private static Dish findMostCaloricDishUsingComparator5() {
        return menu.stream().collect(reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories)))).get();
    }

    private static Dish findMostCaloricDishUsingComparator6() {
        return menu.stream().collect(reducing((dish, dish2) -> dish.getCalories() > dish.getCalories() ? dish : dish2)).get();
    }

}
