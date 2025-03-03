package ru.itmo.meow.model;

public class Coordinates {
    private double x;
    private Double y; // Может быть null

    //  Конструктор
    public Coordinates(double x, Double y) {
        this.x = x;
        this.y = y;
    }

    //  Геттеры
    public double getX() { return x; }
    public Double getY() { return y; }

    //  Сеттеры (для UpdateCommand)
    public void setX(double x) { this.x = x; }
    public void setY(Double y) { this.y = y; }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}

