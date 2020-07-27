package com.hai;

public class Main {
    public static void main(String[] args) {
    }

    private void test () {

        ArrayList<Person> person = new ArrayList<>();

        person.add(new Person(10,"Jack"));
        person.add(new Person(12,"James"));

        System.out.println(person);

    }
}
