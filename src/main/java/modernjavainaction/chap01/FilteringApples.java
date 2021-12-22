package modernjavainaction.chap01;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {
    static Predicate<Apple> greenApple = apple -> "green".equals(apple.getColor());
    static Predicate<Apple> heavyApple = apple -> apple.getWeight() > 150;
    static Predicate<Apple> greenAndHeavyApple = greenApple.and(apple -> apple.getWeight() > 150);

    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        filterGreenApples(inventory);
        filterHeavyApples(inventory);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        // [Apple{color='green', weight=155}]
        // List<Apple> greenHeavyApples = filterApples(inventory, greenAndHeavyApple);
        List<Apple> greenHeavyApples = filterApples(inventory, FilteringApples::isGreenHeavyApple);
        System.out.println(">>>>> " + greenHeavyApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
        System.out.println(weirdApples);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return greenApple.test(apple);
    }

    public static boolean isNotGreenApple(Apple apple) {
        return greenApple.negate().test(apple);
    }

    public static boolean isHeavyApple(Apple apple) {
        return heavyApple.test(apple);
    }

    public static boolean isGreenHeavyApple(Apple apple) {
        //return greenApple.and(heavyApple).test(apple);
        return greenAndHeavyApple.test(apple);
    }

    public static boolean isRedAndHeavyAppleOrGreen(Apple apple) {
        //return greenApple.and(heavyApple).test(apple);
        return greenApple.or(greenApple.negate().and(heavyApple)).test(apple);
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {

        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public String getColor() {
            return color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color='%s', weight=%d}", color, weight);
        }

    }

}
