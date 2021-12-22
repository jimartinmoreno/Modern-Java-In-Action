package modernjavainaction.chap02;

import java.util.*;
import java.util.function.*;

public class SupplierSample {

    public static void main(String... args) {
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        System.out.println(a1);

        Supplier<Apple> c2 = () -> new Apple();
        Apple a2 = c1.get();
        System.out.println(a2);

        Function<Integer, Apple> c2_1 = Apple::new;
        Apple a2_1 = c2_1.apply(110);
        System.out.println(a2_1);

        BiFunction<Integer, Color, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(110, Color.GREEN);
        System.out.println(a3);

        BiFunction<Integer, Color, Apple> c4 = (weight, color) -> new Apple(weight, color);
        Apple a4 = c4.apply(110, Color.RED);
        System.out.println(a4);


        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println(apples);
    }

    public static  List<Apple> map(List<Integer> weightList, BiFunction<Integer, Color, Apple> bf) {
        List<Apple> result = new ArrayList<>();
        weightList.forEach(weight -> result.add(bf.apply(weight, Color.RED)));
        return result;
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

        public Apple(int weight) {
            this.weight = weight;
        }

        public Apple() {
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

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }



}
