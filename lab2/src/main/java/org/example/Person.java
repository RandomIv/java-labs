package org.example;

public class Person {
    String name;
    String surname;
    int age;

    public Person(){}
    public Person(String name,String surname,int age){
        this.name=name;
        this.surname=surname;
        this.age=age;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
