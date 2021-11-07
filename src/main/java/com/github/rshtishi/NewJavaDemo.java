package com.github.rshtishi;

import com.github.rshtishi.domain.Person;
import com.github.rshtishi.parametrization.GenderFemalePersonPredicate;
import com.github.rshtishi.parametrization.PersonPredicate;
import com.github.rshtishi.service.PersonService;

import java.util.ArrayList;
import java.util.List;

public class NewJavaDemo {

    public static void main(String[] args) {
        PersonService personService = new PersonService();
        List<Person> people = personService.findAll();
        System.out.println(people);
        List<Person> females = filterPeople(people, new GenderFemalePersonPredicate());
        System.out.println(females);
    }

    public static List<Person> filterPeople(List<Person> people, PersonPredicate personPredicate) {
        List<Person> filteredPeople = new ArrayList<>();
        for (Person person : people) {
            if(personPredicate.check(person)){
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }
}
