package modernjavainaction.chap02.lambdas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FactorySample {

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static void main(String... args) {
        Fruit apple = giveMeFruit("apple", 110);
        System.out.println(apple);
        Fruit orange = giveMeFruit("orange", 110);
        System.out.println(orange);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase()).apply(weight);
    }

    public interface Fruit {
    }

    public static class Apple implements Fruit {
        private int weight = 0;

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

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{weight=%d}", weight);
        }
    }

    public static class Orange implements Fruit {
        private int weight = 0;

        public Orange(int weight) {
            this.weight = weight;
        }

        public Orange() {
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Orange{weight=%d}", weight);
        }
    }


}
