package activityExample;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var people = List.of(
            new Person("John", "Doe", 30),
            new Person("Jane", "Doe", 25),
            new Person("James", "Smith", 40),
            new Person("Emily", "Jones", 22),
            new Person("Michael", "Brown", 35),
            new Person("Emma", "White", 28),
            new Person("Matthew", "Green", 45),
            new Person("Olivia", "Black", 20)
        );

        people.stream().filter(p -> p.age() > 30)
              .forEach(System.out::println);
    }
}
