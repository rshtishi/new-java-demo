package com.github.rshtishi;

import java.util.function.*;

public class FunctionalInterfaceDemo {

    private static Predicate<String> predicate= (String s) -> s.isEmpty();
    private static Consumer<String>  consumer = (String s) -> System.out.println(s);
    private static Function<String,Integer> function = (String s) -> s.length();
    private static Supplier<String> supplier = () -> "Hi";
    private static UnaryOperator<String> unaryOperator = (String s) -> s.toLowerCase();

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
    }

}
