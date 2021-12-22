package modernjavainaction.chap05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

    public static void main(String... args) throws Exception {

        // System.getProperties().forEach((key, value) -> System.out.println(key + " = " + value));

        Stream.of("java.home", "user.name", "user.home")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)))
                .forEach(System.out::println);

        // Stream.of
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);

        // Stream.empty
        Stream<String> emptyStream = Stream.empty();

        // Arrays.stream
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());

        // Stream.iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // Fibonacci with iterate
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.printf("(%d, %d)", t[0], t[1]));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        // random stream of doubles with Stream.generate
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
                    @Override
                    public int getAsInt() {
                        return 2;
                    }
                })
                .limit(5)
                .forEach(System.out::println);

        IntSupplier fibSupplier = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return previous;
            }
        };

        IntStream.generate(fibSupplier)
                .limit(10)
                .forEach(System.out::println);

        Path myPath = Paths.get(".", "target/classes/modernjavainaction/chap05/data.txt");

        try (Stream<String> input = Files.lines(myPath, Charset.defaultCharset())) {
            input.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> input = Files.lines(myPath, Charset.defaultCharset())) {
            long uniqueWords = input.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println("There are " + uniqueWords + " unique words in data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> input = Files.lines(myPath, Charset.defaultCharset())) {
            input.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        long uniqueWords = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
//                .flatMap(line -> Arrays.stream(line.split(" ")))
//                .distinct()
//                .count();


    }

}