package com.github.rshtishi.parametrization;

import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;

public class FemalePersonPredicate implements PersonPredicate {

    @Override
    public boolean check(Person person) {
        return person.getGender().equals(Gender.FEMALE);
    }
}
