package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.*;
import ru.itmo.meow.exceptions.InvalidInputException;
import ru.itmo.meow.exceptions.DuplicateKeyException;

import java.util.Scanner;

public class Insert implements Command {
    private final ProductManager productManager;
    private final Scanner scanner;

    public Insert(ProductManager productManager, Scanner scanner) {
        this.productManager = productManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new InvalidInputException("Ошибка: Укажите ключ (id) перед добавлением элемента.");
        }

        long id;
        try {
            id = Long.parseLong(args[0]);
            if (id <= 0) {
                throw new InvalidInputException("Ошибка: id должен быть положительным числом.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Ошибка: id должен быть числом.");
        }

        if (productManager.getProducts().containsKey(id)) {
            throw new DuplicateKeyException("Ошибка: Элемент с таким id уже существует.");
        }

        System.out.println("Добавление нового продукта с id " + id + "...");

        // Ввод названия продукта
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new InvalidInputException("Ошибка: Название продукта не может быть пустым.");
        }

        // Ввод координат
        System.out.print("Введите X координату: ");
        double x = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Введите Y координату (оставьте пустым для null): ");
        String yInput = scanner.nextLine().trim();
        Double y = yInput.isEmpty() ? null : Double.parseDouble(yInput);

        Coordinates coordinates = new Coordinates(x, y);

        // Цена продукта
        System.out.print("Введите цену продукта: ");
        long price;
        try {
            price = Long.parseLong(scanner.nextLine().trim());
            if (price <= 0) {
                throw new InvalidInputException("Ошибка: Цена должна быть больше 0.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Ошибка: Цена должна быть числом.");
        }

        // Ввод partNumber (уникальное поле)
        System.out.print("Введите partNumber (не длиннее 83 символов): ");
        String partNumber = scanner.nextLine().trim();
        if (partNumber.length() > 83) {
            throw new InvalidInputException("Ошибка: Длина partNumber не должна превышать 83 символа.");
        }

        // Ввод manufactureCost
        System.out.print("Введите manufactureCost (может быть пустым): ");
        String costInput = scanner.nextLine().trim();
        Float manufactureCost = costInput.isEmpty() ? null : Float.parseFloat(costInput);

        // Ввод UnitOfMeasure (enum)
        System.out.println("Доступные единицы измерения: SQUARE_METERS, PCS, GRAMS, MILLIGRAMS");
        System.out.print("Введите UnitOfMeasure (или оставьте пустым): ");
        String unitInput = scanner.nextLine().trim();
        UnitOfMeasure unitOfMeasure = unitInput.isEmpty() ? null : UnitOfMeasure.valueOf(unitInput);

        // Ввод владельца продукта (Person)
        System.out.print("Введите имя владельца: ");
        String ownerName = scanner.nextLine().trim();
        if (ownerName.isEmpty()) {
            throw new InvalidInputException("Ошибка: Имя владельца не может быть пустым.");
        }

        System.out.print("Введите паспорт владельца: ");
        String passportID = scanner.nextLine().trim();
        if (passportID.isEmpty()) {
            throw new InvalidInputException("Ошибка: Паспорт владельца не может быть пустым.");
        }

        // Ввод цвета глаз (enum)
        System.out.println("Доступные цвета глаз: YELLOW, ORANGE, BROWN, RED, BLACK, BLUE");
        System.out.print("Введите цвет глаз (или оставьте пустым): ");
        String eyeColorInput = scanner.nextLine().trim();
        Color eyeColor = eyeColorInput.isEmpty() ? null : Color.valueOf(eyeColorInput);

        // Ввод цвета волос (enum)
        System.out.println("Доступные цвета волос: YELLOW, ORANGE, BROWN, RED, BLACK, BLUE");
        System.out.print("Введите цвет волос (или оставьте пустым): ");
        String hairColorInput = scanner.nextLine().trim();
        Color hairColor = hairColorInput.isEmpty() ? null : Color.valueOf(hairColorInput);

        // Ввод национальности (enum)
        System.out.println("Доступные страны: USA, CHINA, INDIA, JAPAN");
        System.out.print("Введите страну владельца (или оставьте пустым): ");
        String nationalityInput = scanner.nextLine().trim();
        Country nationality = nationalityInput.isEmpty() ? null : Country.valueOf(nationalityInput);

        Person owner = new Person(ownerName, passportID, eyeColor, hairColor, nationality);

        // Создаем новый продукт
        Product newProduct = new Product(id, name, coordinates, price, partNumber, manufactureCost, unitOfMeasure, owner);

        // Добавляем в коллекцию
        productManager.addProductWithKey(id, newProduct);

        System.out.println("Продукт успешно добавлен: " + newProduct);
    }
}



