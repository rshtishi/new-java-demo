package com.github.rshtishi;

import java.util.function.*;

public class FunctionalInterfaceDemo {

    private static Predicate<String> predicate = (String s) -> s.isEmpty();
    private static Consumer<String> consumer = (String s) -> System.out.println(s);
    private static Function<String, Integer> function = (String s) -> s.length();
    private static Supplier<String> supplier = () -> "Hi";
    private static UnaryOperator<String> unaryOperator = (String s) -> s.toLowerCase();

    private static BiPredicate<String, Integer> biPredicate = (String s, Integer i) -> s.isEmpty() && i == 0;
    private static BiConsumer<String, Integer> biConsumer = (String s, Integer i) -> System.out.println(s + "" + i);
    private static BiFunction<Integer, Double, String> biFunction = (Integer i, Double d) -> "product: " + (i * d);
    private static BinaryOperator<String> binaryOperator = (String a, String b) -> a + b;

    public static void main(String[] args) {
        //Predicate
        System.out.println(predicate.test("Hello"));
        //Consumer
        consumer.accept("Rando");
        //Function
        System.out.println(function.apply("Hello"));
        //Supplier
        System.out.println(supplier.get());
        //UnaryOperator
        System.out.println(unaryOperator.apply("RANDO"));

        System.out.println();

        //BiPredicate
        System.out.println(biPredicate.test("", 0));
        //BiConsumer
        biConsumer.accept("Num: ", 5);
        //BiFunction
        System.out.println(biFunction.apply(10, 3.5));
        //BinaryOperator
        System.out.println(binaryOperator.apply("Rando","Shtishi"));
    }

}
