package ru.itmo.meow;

public class Product {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private String partNumber; //Длина строки не должна быть больше 83, Значение этого поля должно быть уникальным, Поле не может быть null
    private float manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Person owner; //Поле не может быть null
}
