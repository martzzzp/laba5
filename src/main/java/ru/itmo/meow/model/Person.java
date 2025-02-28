package ru.itmo.meow.model;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Строка не может быть пустой, Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
}
