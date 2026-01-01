package com.example.sealed;

public sealed abstract class Animal permits Dog, Cat {
    public abstract String sound();
}
