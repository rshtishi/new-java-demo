package com.github.rshtishi;

import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;
import com.github.rshtishi.parametrization.*;
import com.github.rshtishi.service.PersonService;

import java.util.ArrayList;
import java.util.List;

public class NewJavaDemo {

    public static void main(String[] args) {
        PersonService personService = new PersonService();
        List<Person> people = personService.findAll();

        printPeople(people, new SimplePersonFormatter());

        List<Person> females = filterPeople(people, new FemalePersonPredicate());

        printPeople(females, new SimplePersonFormatter());
        //using anonymous class for passing the parameterized behaviours(person format)
        printPeople(females, new PersonFormatter() {
            @Override
            public String format(Person person) {
                return String.format("[fullName:%s]", person.getFullName());
            }
        });

        //using lambda expression for passing the parameterized behaviours(person format)
        printPeople(females, (person) -> {
            return String.format("{fullName:%s, age:%d}", person.getFullName(), person.getAge());
        });

        GeneralPredicate<Person>  malesPredicate = new GeneralPredicate<Person>() {
            @Override
            public boolean check(Person person) {
                return person.getGender().equals(Gender.MALE);
            }
        };

        //using anonymous classes
        List<Person> males = filterPeople(people, malesPredicate);

        printPeople(males, new SimplePersonFormatter());


    }

    public static List<Person> filterPeople(List<Person> people, GeneralPredicate<Person> generalPredicate) {
        List<Person> filteredPeople = new ArrayList<>();
        for (Person person : people) {
            if (generalPredicate.check(person)) {
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }

    public static List<Person> filterPeople(List<Person> people, PersonPredicate personPredicate) {
        List<Person> filteredPeople = new ArrayList<>();
        for (Person person : people) {
            if (personPredicate.check(person)) {
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }

    public static void printPeople(List<Person> people, PersonFormatter personFormatter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Person person : people) {
            String formattedPerson = personFormatter.format(person);
            stringBuilder.append(formattedPerson).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }
}
