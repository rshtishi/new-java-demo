package com.github.rshtishi;

import com.github.rshtishi.domain.Person;
import com.github.rshtishi.parametrization.FemalePersonPredicate;
import com.github.rshtishi.parametrization.PersonFormatter;
import com.github.rshtishi.parametrization.PersonPredicate;
import com.github.rshtishi.parametrization.SimplePersonFormatter;
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
