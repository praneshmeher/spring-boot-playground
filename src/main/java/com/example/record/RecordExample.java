package com.example.record;

public class RecordExample {
    public static void main(String[] args) {
        PersonRecord p1 = new PersonRecord("Alice", 30);
        PersonRecord p2 = new PersonRecord("Alice", 30);

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("accessors: name=" + p1.name() + ", age=" + p1.age());
    }
}
