package com.example.demo.onlinequiz;

import lombok.Getter;

import java.util.*;

import static java.lang.System.out;

public class RePartners {
    public static void main(String[] args) {
        String str1 = "greeting";
        String str2 = new String("greeting"); // a new String in the heap, distinct from string pool
        out.println(str1 == str2); // false (not in the same heap memory location)
        out.println(str1.hashCode() == str2.hashCode()); // true (compared the content of the strings)
        out.println(str1.equals(str2)); // true
        String str3 = new String("greeting"); // a new String in the heap, distinct from str1 in the string pool
        out.println(str2 == str3); // false (not in the same memory location)
        out.println(str2.equals(str3)); // true

        String str4 = "greeting";
        out.println(str1 == str4);  // true  (same memory location in the string pool)

        String str5 = str1.intern();
        out.println(str1 == str5); //true, for both refer to the same object in string pool
    }
}

@Getter
final class ImmutablePerson {
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(int age, List<String> hobbies) {
        this.age = age;
        /*
         * Collections.unmodifiableList(new ArrayList<>(hobbies))
         */
        this.hobbies = List.copyOf(hobbies);
    }

    public static void main(String[] args) {
        List<String> hobbies = List.of("one", "two", "three", "four", "five");
        ImmutablePerson immutablePerson = new ImmutablePerson(1, hobbies);
        out.println(immutablePerson.getAge() + " " + immutablePerson.getHobbies());
    }
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class SetQuestion {
    public static void main(String[] args) {
        Set<Person> persons = new HashSet<>();

        Person person1 = new Person("John");
        Person person2 = new Person("Bill");

        persons.add(person1);
        persons.add(person2);

        /*
         * HashSet could not detect this change, it manages unique elements (based on the hashCode) at insertion time
         * To prevent this duplication, we can set person's name as final
         */
        person2.name = "John";

        out.println(persons.size()); // 2
        out.println(persons.contains(person1)); // true
        out.println(persons.contains(person2)); // true

        out.println(persons.remove(person2)); // true
        out.println(persons.remove(person1)); // false
        out.println(persons.size()); // 1
    }
}

class WordCounter {
    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new LinkedHashMap<>();

        for (String word : text.toLowerCase().split("")) // O(n)
            if (!word.isEmpty())
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);

        return wordCount;
    }

    public static void main(String[] args) {
        out.println(countWords("Mahsa"));
    }
}