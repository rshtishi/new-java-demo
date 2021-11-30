package com.github.rshtishi;

import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;
import com.github.rshtishi.parametrization.SimplePersonFormatter;
import com.github.rshtishi.service.PersonService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.rshtishi.Util.printPeople;

public class StreamsDemo {

    public static void main(String[] args) {
        PersonService personService = new PersonService();
        List<Person> people = personService.findAll();

        //external iteration
        List<Person> females = new ArrayList<>();
        Iterator<Person> peopleIterator = people.iterator();
        while (peopleIterator.hasNext()) {
            Person person = peopleIterator.next();
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }
        printPeople(females, new SimplePersonFormatter());

        //internal iteration
        List<Person> males = people.stream().filter(p -> p.getGender().equals(Gender.MALE))
                .collect(Collectors.toList());
        printPeople(males, new SimplePersonFormatter());

        //flatmap example
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> numberList = numbers1.stream().flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        numberList.stream().forEach(StreamsDemo::printPair);

        List<String> names = Arrays.asList("Rando", "Ema", "Ela");
        List<String> uniqueCharacters = names.stream().map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println(sum);

        //reducing sum examples
        sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        //reducing max min examples
        int max = numbers.stream().reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(max);

        max = numbers.stream().reduce(Integer.MIN_VALUE, (a, b) -> a >= b ? a : b);
        System.out.println(max);

        int min = numbers.stream().reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println(min);

         Optional<Integer> optionalMin = numbers.stream().min(Integer::compare);
         System.out.println(optionalMin.get());

         Optional<Integer> optionalMax = numbers.stream().max(Comparator.naturalOrder());
         System.out.println(optionalMax.get());

        //reducing joining example
        String namesStr = people.stream().map(person -> person.getFullName())
                .sorted()
                .reduce("", (a, b) -> a +"," +b);
        System.out.println(namesStr);

        namesStr = people.stream().map(person -> person.getFullName())
                .sorted() //more efficient due to the use of StringBuilder
                .collect(Collectors.joining(","));
        System.out.println(namesStr);

        // mapping to numeric streams
        OptionalDouble averageAge = people.stream().mapToInt(person -> person.getAge())
                .average();
        System.out.println(averageAge.getAsDouble());

        //generating values with rangeClosed
        Stream<int[]> pythagorianTriples = IntStream.rangeClosed(1,100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a,100).filter(b -> Math.sqrt(a*a+b*b)%1==0)
                                .mapToObj(b-> new int[]{a, b,(int) Math.sqrt(a*a+b*b)})
                );
        pythagorianTriples.forEach(triple -> System.out.print("("+triple[0]+","+triple[1]+","+triple[2]+")"));




    }

    private static void printPair(int[] ints) {
        System.out.println("(" + ints[0] + "," + ints[1] + ")");
    }
}
