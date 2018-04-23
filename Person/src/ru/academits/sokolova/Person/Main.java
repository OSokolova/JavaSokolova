package ru.academits.sokolova.Person;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) {
        List<Person> persons = new ArrayList<>();
        Person p1 = new Person("olga", 20);
        Person p2 = new Person("olga1", 21);
        Person p3 = new Person("olga2", 22);
        Person p4 = new Person("olga3", 23);
        Person p5 = new Person("olga4", 24);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);
        persons.forEach(System.out::println);
    }
}
