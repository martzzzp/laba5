package ru.itmo.meow.model;

public class Person {
    private String name;
    private String passportID;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;

    // 🔥 Конструктор с параметрами
    public Person(String name, String passportID, Color eyeColor, Color hairColor, Country nationality) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Имя не может быть пустым");
        if (passportID == null || passportID.isEmpty()) throw new IllegalArgumentException("Паспорт не может быть пустым");

        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    // 🔥 Геттеры
    public String getName() { return name; }
    public String getPassportID() { return passportID; }
    public Color getEyeColor() { return eyeColor; }
    public Color getHairColor() { return hairColor; }
    public Country getNationality() { return nationality; }

    // 🔥 Сеттеры (для обновления данных)
    public void setName(String name) { this.name = name; }
    public void setPassportID(String passportID) { this.passportID = passportID; }
    public void setEyeColor(Color eyeColor) { this.eyeColor = eyeColor; }
    public void setHairColor(Color hairColor) { this.hairColor = hairColor; }
    public void setNationality(Country nationality) { this.nationality = nationality; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', passportID='" + passportID + "', eyeColor=" + eyeColor +
                ", hairColor=" + hairColor + ", nationality=" + nationality + "}";
    }
}
