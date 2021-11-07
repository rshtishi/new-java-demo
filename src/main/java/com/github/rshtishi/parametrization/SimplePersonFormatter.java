package com.github.rshtishi.parametrization;

import com.github.rshtishi.domain.Person;

public class SimplePersonFormatter implements PersonFormatter {
    @Override
    public String format(Person person) {
        return String.format("[full name: %s, gender: %s]", person.getFullName(), person.getGender());
    }
}
