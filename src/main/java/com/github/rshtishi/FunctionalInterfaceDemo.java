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
        System.out.println(binaryOperator.apply("Rando", "Shtishi"));

        System.out.println();

        IntPredicate intPredicate = (int i) -> i == 0;
        System.out.println(intPredicate.test(0));
        LongPredicate longPredicate = (long l) -> l > 10L;
        System.out.println(longPredicate.test(100));
        DoublePredicate doublePredicate = (double d) -> d < 10;
        System.out.println(doublePredicate.test(4));

        IntConsumer intConsumer = (int i) -> System.out.println(i);
        intConsumer.accept(1);
        LongConsumer longConsumer = (long l) -> System.out.println(l);
        longConsumer.accept(2L);
        DoubleConsumer doubleConsumer = (double d) -> System.out.println(d);
        doubleConsumer.accept(3.0);

        IntFunction<String> intFunction = (int i) -> "val:" + String.valueOf(i);
        System.out.println(intFunction.apply(1));
        IntToDoubleFunction intToDoubleFunction = (int i) -> Double.valueOf(i);
        System.out.println(intToDoubleFunction.applyAsDouble(1));
        IntToLongFunction intToLongFunction = (int i) -> Long.valueOf(i);
        System.out.println(intToLongFunction.applyAsLong(1));

        LongFunction<String> longFunction = (long i) -> String.valueOf(i);
        System.out.println(longFunction.apply(1));
        LongToDoubleFunction longToDoubleFunction = (long l) -> Double.valueOf(l);
        System.out.println(longToDoubleFunction.applyAsDouble(5L));
        LongToIntFunction longToIntFunction = (long l) -> 0;
        System.out.println(longToIntFunction.applyAsInt(4));

        DoubleFunction<String> doubleFunction = (double d) -> "val: " + d;
        System.out.println(doubleFunction.apply(1.0D));
        DoubleToIntFunction doubleToIntFunction = (double d) -> 0;
        System.out.println(doubleToIntFunction.applyAsInt(1.0D));
        DoubleToLongFunction doubleToLongFunction = (double d) -> 1000L;
        System.out.println(doubleToLongFunction.applyAsLong(10.0D));

        ToIntFunction<String> toIntFunction = (String s) -> Integer.valueOf(s);
        System.out.println(toIntFunction.applyAsInt("1"));
        ToDoubleFunction<String> toDoubleFunction = (String s) -> Double.valueOf(s);
        System.out.println(toDoubleFunction.applyAsDouble("1.0D"));
        ToLongFunction<String> toLongFunction = (String s) -> Long.valueOf(s);
        System.out.println(toLongFunction.applyAsLong("100"));

        BooleanSupplier booleanSupplier = () -> true;
        System.out.println(booleanSupplier.getAsBoolean());
        IntSupplier intSupplier = () -> 100;
        System.out.println(intSupplier.getAsInt());
        LongSupplier longSupplier = () -> 10L;
        System.out.println(longSupplier.getAsLong());

        IntUnaryOperator intUnaryOperator = (int i) -> i++;
        System.out.println(intUnaryOperator.applyAsInt(0));
        LongUnaryOperator longUnaryOperator = (long l) -> l = l + 50L;
        System.out.println(longUnaryOperator.applyAsLong(50L));
        DoubleUnaryOperator doubleUnaryOperator = (double d) -> d = d / 2;
        System.out.println(doubleUnaryOperator.applyAsDouble(50.0));

        IntBinaryOperator intBinaryOperator = (int a, int b) -> a + b;
        System.out.println(intBinaryOperator.applyAsInt(5, 5));
        LongBinaryOperator longBinaryOperator = (long a, long b) -> a + b;
        System.out.println(longBinaryOperator.applyAsLong(100L, 100L));
        DoubleBinaryOperator doubleBinaryOperator = (double a, double b) -> a + b;
        System.out.println(doubleBinaryOperator.applyAsDouble(10.5,5.3));
        
    }

}
