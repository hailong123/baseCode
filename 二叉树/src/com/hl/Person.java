package com.hl;

public class Person implements Comparable<Person> {

    private int age;

    public Person(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person p) {
       return age - p.age;
    }

    public int getAge() {
        return age;
    }
}
