package com.github.rshtishi;

import com.github.rshtishi.domain.AgeGroup;
import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;
import com.github.rshtishi.parametrization.SimplePersonFormatter;
import com.github.rshtishi.service.PersonService;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.rshtishi.Util.printPeople;
import static java.util.stream.Collectors.*;

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
                .reduce("", (a, b) -> a + "," + b);
        System.out.println(namesStr);

        namesStr = people.stream().map(person -> person.getFullName())
                .sorted() //more efficient due to the use of StringBuilder
                .collect(joining(","));
        System.out.println(namesStr);

        // mapping to numeric streams
        OptionalDouble averageAge = people.stream().mapToInt(person -> person.getAge())
                .average();
        System.out.println(averageAge.getAsDouble());

        double avgAge = people.stream().collect(averagingInt(Person::getAge));
        System.out.println(avgAge);

        //generating values with rangeClosed
        Stream<int[]> pythagorianTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagorianTriples.limit(3).forEach(triple -> System.out.print("(" + triple[0] + "," + triple[1] + "," + triple[2] + ")"));
        System.out.println();
        // group by gender imperative style
        Map<Gender, List<Person>> peopleByGender = new HashMap<>();
        for (Person person : people) {
            Gender gender = person.getGender();
            List<Person> peopleForGender = peopleByGender.get(gender);
            if (peopleForGender == null) {
                peopleForGender = new ArrayList<>();
                peopleByGender.put(gender, peopleForGender);
            }
            peopleForGender.add(person);
        }
        System.out.println(peopleByGender);

        //using streams
        peopleByGender = people.stream().collect(groupingBy(Person::getGender));
        System.out.println(peopleByGender);

        int peopleAgeSum = people.stream().collect(summingInt(Person::getAge));
        System.out.println(peopleAgeSum);

        IntSummaryStatistics ageStatistic = people.stream().collect(summarizingInt(Person::getAge));
        System.out.println(ageStatistic);

        peopleAgeSum = people.stream().collect(reducing(0, Person::getAge, Integer::sum));
        System.out.println(peopleAgeSum);

        Map<AgeGroup, List<Person>> peopleByAgeGroup = people.stream().collect(groupingBy(person ->
        {
            if (person.getAge() < 35) return AgeGroup.YOUNG;
            else if (person.getAge() < 45) return AgeGroup.MIDDLE;
            else return AgeGroup.OLD;
        }));
        System.out.println(peopleByAgeGroup);

        Map<AgeGroup, Long> peopleCountByAgeGroup = people.stream().collect(groupingBy(person -> {
                    if (person.getAge() < 35) return AgeGroup.YOUNG;
                    else if (person.getAge() < 45) return AgeGroup.MIDDLE;
                    else return AgeGroup.OLD;
                }, counting()
        ));
        System.out.println(peopleCountByAgeGroup);

        Map<AgeGroup, Optional<Person>> optionalOldestPersonByAgeGroup = people.stream().collect(groupingBy(person -> {
            if (person.getAge() < 35) return AgeGroup.YOUNG;
            else if (person.getAge() < 45) return AgeGroup.MIDDLE;
            else return AgeGroup.OLD;
        }, maxBy(Comparator.comparingInt(Person::getAge))));

        System.out.println(optionalOldestPersonByAgeGroup);

        Map<AgeGroup, Person> oldestPersonByAgeGroup = people.stream().collect(groupingBy(person -> {
            if (person.getAge() < 35) return AgeGroup.YOUNG;
            else if (person.getAge() < 45) return AgeGroup.MIDDLE;
            else return AgeGroup.OLD;
        }, collectingAndThen(maxBy(Comparator.comparingInt(Person::getAge)), Optional::get)));

        System.out.println(oldestPersonByAgeGroup);

        Map<AgeGroup, String> namesByAgeGroup = people.stream().collect(groupingBy(person -> {
            if (person.getAge() < 35) return AgeGroup.YOUNG;
            else if (person.getAge() < 45) return AgeGroup.MIDDLE;
            else return AgeGroup.OLD;
        }, Collectors.mapping(person -> person.getFullName(), joining(","))));

        System.out.println(namesByAgeGroup);

        Map<Boolean, List<Person>> peoplePartionedByGender = people.stream().collect(partitioningBy(person ->
                person.getGender().equals(Gender.MALE)));
        System.out.println(peoplePartionedByGender);

        Map<Boolean, Map<AgeGroup, List<Person>>> malesByAgeGroup = people.stream().collect(partitioningBy(person ->
                        person.getGender().equals(Gender.MALE),
                groupingBy(person -> {
                    if (person.getAge() < 35) return AgeGroup.YOUNG;
                    else if (person.getAge() < 45) return AgeGroup.MIDDLE;
                    else return AgeGroup.OLD;
                })));

        System.out.println(malesByAgeGroup);

        Map<Boolean, Map<AgeGroup, String>> namesByAgeGroupPartitionedByGender = people.stream().collect(partitioningBy(
                person -> person.getGender().equals(Gender.MALE),
                groupingBy(person -> {
                    if (person.getAge() < 35) return AgeGroup.YOUNG;
                    else if (person.getAge() < 45) return AgeGroup.MIDDLE;
                    else return AgeGroup.OLD;
                }, Collectors.mapping(person -> person.getFullName(), joining(",")))
        ));

        System.out.println(namesByAgeGroupPartitionedByGender);

        Map<Boolean, Person> youngestPersonPartionedByGender = people.stream().collect(partitioningBy(
                person -> person.getGender().equals(Gender.MALE),
                collectingAndThen(minBy(Comparator.comparingInt(Person::getAge)), Optional::get))
        );

        System.out.println(youngestPersonPartionedByGender);


    }


    private static void printPair(int[] ints) {
        System.out.println("(" + ints[0] + "," + ints[1] + ")");
    }
}
