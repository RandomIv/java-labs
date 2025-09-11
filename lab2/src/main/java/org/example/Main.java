package org.example;
import com.google.gson.Gson;
public class Main {
    public static void main(String[] args) {
        Person person = new Person("John", "Kennedy", 42);
        String jsonPerson = new Gson().toJson(person);
        System.out.println(jsonPerson);
        Person newPerson = new Gson().fromJson(jsonPerson, Person.class);
        System.out.println(person.equals(newPerson));
    }
}