package com.github.rshtishi;

import java.util.Locale;
import java.util.function.*;

public class MethodReferenceDemo {

    public static void main(String[] args) {

        Consumer<String> lambdaConsumer = (String s) -> System.out.println(s);
        lambdaConsumer.accept("Consumer Lambda Expresion");
        Consumer<String> methodReferenceConsumber = System.out::println;
        methodReferenceConsumber.accept("Consumer Method Reference");

        Supplier<String> lambdaSupplier = () -> "Supplier Lambda Expression";
        System.out.println(lambdaSupplier.get());
        Supplier<String> constructorReferenceSupplier = String::new;
        System.out.println(constructorReferenceSupplier);

        Predicate<String> lambdaPredicate = (String s) -> s.isEmpty();
        System.out.println(lambdaPredicate.test("Rando"));
        Predicate<String> methodReferencePredicate = String::isEmpty;
        System.out.println(methodReferencePredicate.test("Shtishi"));

        Function<String,Integer> lambdaFunction = (String s) -> s.length();
        System.out.println(lambdaFunction.apply("Rando"));
        Function<String,Integer> methodReferenceFunction = String::length;
        System.out.println(methodReferenceFunction.apply("Rando"));

        UnaryOperator<String> lambdaUnaryOperator = (String s) -> s.toUpperCase(Locale.ROOT);
        System.out.println(lambdaUnaryOperator.apply("rando"));
        UnaryOperator<String> methodReferenceUnaryOperator = String::toUpperCase;
        System.out.println(methodReferenceUnaryOperator.apply("shtishi"));

    }
}
