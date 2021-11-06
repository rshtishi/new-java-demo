package com.github.rshtishi.parametrization;

import com.github.rshtishi.domain.Gender;
import com.github.rshtishi.domain.Person;

public class GenderFemalePersonPredicate implements PersonPredicate {

    @Override
    public boolean check(Person person) {
        return person.equals(Gender.FEMALE);
    }
}
