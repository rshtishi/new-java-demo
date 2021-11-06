package com.github.rshtishi.service;

import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {

    public List<Person> findAll(){
        List<Person> people = new ArrayList<>();
        people.add(Person.builder().fullName("John Doe").age(30).height(1.75).gender(Gender.MALE).build());
        people.add(Person.builder().fullName("Chun Lee").age(16).height(1.60).gender(Gender.FEMALE).build());
        people.add(Person.builder().fullName("Phoenix Johnson").age(20).height(1.82).gender(Gender.MALE).build());
        people.add(Person.builder().fullName("Janet Taylor").age(40).height(1.68).gender(Gender.FEMALE).build());
        people.add(Person.builder().fullName("Jason Lee").age(25).height(1.90).gender(Gender.MALE).build());
        people.add(Person.builder().fullName("Kirsten Jasckon").age(22).height(1.57).gender(Gender.FEMALE).build());
        people.add(Person.builder().fullName("Jen Jen").age(30).height(1.60).gender(Gender.OTHER).build());
        people.add(Person.builder().fullName("Jay Jay").age(40).height(1.65).gender(Gender.OTHER).build());
        return people;
    }
}
