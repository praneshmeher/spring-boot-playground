package com.example.sealed;

public class SealedExample {
    public static void main(String[] args) {
        Animal d = new Dog();
        Animal c = new Cat();

        System.out.println("Dog: " + d.sound());
        System.out.println("Cat: " + c.sound());
    }
}
