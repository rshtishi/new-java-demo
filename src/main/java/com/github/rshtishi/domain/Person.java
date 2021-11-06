package com.github.rshtishi.domain;

import com.github.rshtishi.domain.Gender;
import lombok.Builder;

@Builder
public class Person {

    private String fullName;
    private int age;
    private double height;
    private Gender gender;

}
