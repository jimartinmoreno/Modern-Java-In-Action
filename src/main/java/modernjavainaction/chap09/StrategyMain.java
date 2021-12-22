package modernjavainaction.chap09;

import javax.xml.validation.Validator;

public class StrategyMain {

    public static void main(String[] args) {
        // old school
        Validator v1 = new Validator(new IsNumeric());
        System.out.println(v1.validate("aaaa"));
        Validator v2 = new Validator(new IsAllLowerCase());
        System.out.println(v2.validate("bbbb"));

        // with lambdas
        Validator v3 = new Validator(s -> s.matches("\\d+"));
        System.out.println(v3.validate("aaaa"));
        Validator v4 = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(v4.validate("bbbb"));
    }

    /**
     * An interface to represent some algorithm (the interface Strategy)
     */
    interface ValidationStrategy {
        boolean execute(String s);
    }

    /**
     * concrete implementations of that interface to to validate lowercase letter format
     */
    static private class IsAllLowerCase implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    /**
     * concrete implementations of that interface to validate numeric format
     */
    static private class IsNumeric implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    /**
     * client that use the strategy objects
     */
    static private class Validator {
        private final ValidationStrategy strategy;
        public Validator(ValidationStrategy validationStrategy) {
            strategy = validationStrategy;
        }
        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }
}
