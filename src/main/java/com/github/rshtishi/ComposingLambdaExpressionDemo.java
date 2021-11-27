package com.github.rshtishi;

import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;
import com.github.rshtishi.parametrization.SimplePersonFormatter;
import com.github.rshtishi.service.PersonService;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.rshtishi.Util.printPeople;

public class ComposingLambdaExpressionDemo {

    public static void main(String[] args) {
        PersonService personService = new PersonService();
        List<Person> people = personService.findAll();
        printPeople(people, new SimplePersonFormatter());
        people.sort(Comparator.comparing(Person::getFullName)
                .reversed()
                .thenComparing(Person::getAge));
        printPeople(people, new SimplePersonFormatter());

        Predicate<Person> malePredicate = (Person p) -> p.getGender().equals(Gender.MALE);
        people.stream().filter(malePredicate).forEach(System.out::println);
        System.out.println();
        Predicate<Person> notMalePredicate = malePredicate.negate();
        people.stream().filter(notMalePredicate).forEach(System.out::println);
        System.out.println();
        Predicate<Person> maleOver20Predicate = malePredicate.and(person -> person.getAge() > 20);
        people.stream().filter(maleOver20Predicate).forEach(System.out::println);
        System.out.println();
        Predicate<Person> maleOver20OrtallMalePredicate = maleOver20Predicate.or(person -> person.getHeight() > 1.8);
        people.stream().filter(maleOver20OrtallMalePredicate).forEach(System.out::println);
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1));
        h = f.compose(g);
        System.out.println(h.apply(1));
    }
}
