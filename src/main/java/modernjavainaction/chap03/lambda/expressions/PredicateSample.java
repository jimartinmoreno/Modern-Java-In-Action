package modernjavainaction.chap03.lambda.expressions;

import java.util.function.Predicate;

public class PredicateSample {

    private boolean isValidName(String string) {
        return Character.isUpperCase(string.charAt(0));
    }

    private boolean filter(String string, Predicate<String> stringPredicate) {
        return stringPredicate.test(string);
    }

    public static void main(String[] args) {
        new PredicateSample().testFilter("Nacho");
    }

    private void testFilter(String s) {
        if (this.filter(s, this::isValidName)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test passed");
        }
    }
}
