package com.example.demo.onlinequiz;

import java.util.*;

public class RePartners {
    public static void main(String[] args) {
        String str1 = "greeting";
        String str2 = new String("greeting"); // a new String in the heap, distinct from str1 in the string pool
        System.out.println(str1 == str2); // false (not in the same memory location)
        System.out.println(str1.hashCode() == str2.hashCode()); // true (compared the content of the strings)
        System.out.println(str1.equals(str2)); // true

        String str3 = "greeting";
        System.out.println(str1 == str3);  // true  (same memory location in the string pool)
    }
}

final class MutablePerson {
    private int age;
    private List<String> hobbies;

    public MutablePerson(int age, List<String> hobbies) {
        this.age = age;
        /*
         * Collections.unmodifiableList(new ArrayList<>(hobbies))
         */
        this.hobbies = List.copyOf(hobbies);
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public static void main(String[] args) {
        List<String> hobbies = List.of("one", "two", "three", "four", "five");
        MutablePerson mutablePerson = new MutablePerson(1, hobbies);
        System.out.println(mutablePerson.getAge() + " " + mutablePerson.getHobbies());
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
         * HashSet could not detect this change, because it manages unique elements based on the hashCode computed insertion time
         * To prevent this duplication, we can set person's name as final
         */
        person2.name = "John";

        System.out.println(persons.size()); // 2
        System.out.println(persons.contains(person1)); // true
        System.out.println(persons.contains(person2)); // true

        System.out.println(persons.remove(person2)); // true
        System.out.println(persons.remove(person1)); // false
        System.out.println(persons.size()); // 1
    }
}

class WordCounter {
    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : text.toLowerCase().split(""))
            if (!word.isEmpty())
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);

        return wordCount;
    }

    public static void main(String[] args) {
        System.out.println(countWords("Mahsa"));
        System.out.println(Arrays.stream("Mahsa".split(" ")).count());
    }
}