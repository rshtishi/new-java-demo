package com.github.rshtishi;

import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;
import com.github.rshtishi.parametrization.SimplePersonFormatter;
import com.github.rshtishi.service.PersonService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.rshtishi.Util.printPeople;

public class StreamsDemo {

    public static void main(String[] args) {
        PersonService personService = new PersonService();
        List<Person> people = personService.findAll();

        //external iteration
        List<Person> females = new ArrayList<>();
        Iterator<Person> peopleIterator = people.iterator();
        while(peopleIterator.hasNext()){
            Person person = peopleIterator.next();
            if(person.getGender().equals(Gender.FEMALE)){
                females.add(person);
            }
        }
        printPeople(females, new SimplePersonFormatter());

        //internal iteration
        List<Person> males = people.stream().filter(p -> p.getGender().equals(Gender.MALE))
                .collect(Collectors.toList());
        printPeople(males, new SimplePersonFormatter());
    }
}
