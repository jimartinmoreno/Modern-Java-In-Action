package modernjavainaction.chap02.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilteringGenerics {

    public static void main(String... args) {
        List<Apple> inventory = new ArrayList<>(4);
        inventory.add(new Apple(80, Color.GREEN));
        inventory.add(new Apple(155, Color.GREEN));
        inventory.add(new Apple(120, Color.RED));

        List<Integer> integerList = List.of(1, 2, 3, 4, 5);

        List<Apple> resultAppleList = filter(inventory, apple -> apple.getColor() == Color.GREEN);
        resultAppleList.sort(Comparator
                .<Apple>comparingInt(Apple::getWeight).reversed()
        );

        // [Apple{color=GREEN, weight=155}, Apple{color=GREEN, weight=80}]
        System.out.println(resultAppleList);

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        System.out.println(filter(inventory, new AppleGreenPredicate()));


        // [Apple{color=GREEN, weight=155}]
        System.out.println(filter(inventory, new AppleWeightPredicate()));

        // []
        System.out.println(filter(inventory, new AppleRedAndHeavyPredicate()));

        // [Apple{color=RED, weight=120}]
        System.out.println(filter(inventory, a -> a.getColor() == Color.RED));

        // [Apple{color=RED, weight=120}]
        System.out.println(filter(integerList, a -> a > 2));

        inventory.forEach(apple -> apple.setWeight(1000));
        System.out.println(inventory);

        doSomething(new Apple(10, Color.RED), (apple -> {
            inventory.add(apple);
        }));

        doSomething(new Apple(1000, Color.RED), (inventory::add));
        System.out.println(inventory);

        doSomething(new Apple(1000, Color.RED), (inventory::remove));
        System.out.println(inventory);

    }

    public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : inventory) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> void doSomething(T t, Consumer<T> c) {
        c.accept(t);
    }

    enum Color {
        RED,
        GREEN
    }

    public static class Apple {
        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Apple apple = (Apple) o;
            return weight == apple.weight && color == apple.color;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, color);
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }

    static class AppleWeightPredicate implements Predicate<Apple> {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    static class AppleGreenPredicate implements Predicate<Apple> {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }
    }

    static class AppleRedAndHeavyPredicate implements Predicate<Apple> {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.RED && apple.getWeight() > 150;
        }
    }

}
