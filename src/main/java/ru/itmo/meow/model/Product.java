package ru.itmo.meow.model;

import java.util.Date;

public class Product {
    private Long id; // Поле не может быть null, должно быть больше 0, уникально, генерируется автоматически
    private String name; // Поле не может быть null, строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private Date creationDate; // Поле не может быть null, генерируется автоматически
    private long price; // Значение должно быть больше 0
    private String partNumber; // Длина не должна быть больше 83, уникально, не может быть null
    private float manufactureCost;
    private UnitOfMeasure unitOfMeasure; // Может быть null
    private Person owner; // Поле не может быть null

    // Конструктор
    public Product(Long id, String name, Coordinates coordinates, long price, String partNumber,
                   float manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date(); // Автоматическая генерация
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Coordinates getCoordinates() { return coordinates; }
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    public Date getCreationDate() { return creationDate; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public String getPartNumber() { return partNumber; }
    public void setPartNumber(String partNumber) { this.partNumber = partNumber; }

    public float getManufactureCost() { return manufactureCost; }
    public void setManufactureCost(float manufactureCost) { this.manufactureCost = manufactureCost; }

    public UnitOfMeasure getUnitOfMeasure() { return unitOfMeasure; }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }

    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    // Метод toString() для вывода информации о продукте
    @Override
    public String toString() {
        return "Product { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", partNumber='" + partNumber + '\'' +
                ", manufactureCost=" + manufactureCost +
                ", unitOfMeasure=" + unitOfMeasure +
                ", owner=" + owner +
                " }";
    }
}

