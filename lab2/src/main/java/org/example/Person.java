package org.example;

import java.util.Objects;

public final class Person {
    final String name;
    final String surname;
    final int age;

    public Person(String name,String surname,int age){
        this.name=name;
        this.surname=surname;
        this.age=age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Person person)) return false;
        return  Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && age == person.age;
    }
    @Override
    public int hashCode() {
        return Objects.hash(surname, name, age);
    }
    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + ", age=" + age + '}';
    }
}
