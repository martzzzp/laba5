package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.*;
import ru.itmo.meow.exceptions.InvalidInputException;
import ru.itmo.meow.exceptions.ElementNotFoundException;

import java.util.Scanner;

public class Update implements Command {
    private final ProductManager productManager;
    private final Scanner scanner;

    public Update(ProductManager productManager, Scanner scanner) {
        this.productManager = productManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new InvalidInputException("Ошибка: Укажите `id` элемента, который хотите обновить.");
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

        if (!productManager.getProducts().containsKey(id)) {
            throw new ElementNotFoundException("Ошибка: Продукт с id " + id + " не найден.");
        }

        Product existingProduct = productManager.getProducts().get(id);
        System.out.println("Обновление продукта с id " + id + "... (оставьте поле пустым, если не хотите его менять)");

        // Ввод названия
        System.out.print("Введите новое название продукта (" + existingProduct.getName() + "): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            existingProduct.setName(name);
        }

        // Ввод координат
        System.out.print("Введите X координату (" + existingProduct.getCoordinates().getX() + "): ");
        String xInput = scanner.nextLine().trim();
        if (!xInput.isEmpty()) {
            existingProduct.getCoordinates().setX(Double.parseDouble(xInput));
        }

        System.out.print("Введите Y координату (" + existingProduct.getCoordinates().getY() + ", оставьте пустым для null): ");
        String yInput = scanner.nextLine().trim();
        if (!yInput.isEmpty()) {
            existingProduct.getCoordinates().setY(Double.parseDouble(yInput));
        } else {
            existingProduct.getCoordinates().setY(null);
        }

        // Ввод цены
        System.out.print("Введите новую цену продукта (" + existingProduct.getPrice() + "): ");
        String priceInput = scanner.nextLine().trim();
        if (!priceInput.isEmpty()) {
            try {
                long price = Long.parseLong(priceInput);
                if (price <= 0) {
                    throw new InvalidInputException("Ошибка: Цена должна быть больше 0.");
                }
                existingProduct.setPrice(price);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Ошибка: Цена должна быть числом.");
            }
        }

        // Ввод partNumber
        System.out.print("Введите новый partNumber (" + existingProduct.getPartNumber() + "): ");
        String partNumber = scanner.nextLine().trim();
        if (!partNumber.isEmpty() && partNumber.length() <= 83) {
            existingProduct.setPartNumber(partNumber);
        }

        // Ввод manufactureCost
        System.out.print("Введите новое manufactureCost (" + existingProduct.getManufactureCost() + ", может быть пустым): ");
        String costInput = scanner.nextLine().trim();
        if (!costInput.isEmpty()) {
            existingProduct.setManufactureCost(Float.parseFloat(costInput));
        }

        // Ввод UnitOfMeasure
        System.out.println("Доступные единицы измерения: SQUARE_METERS, PCS, GRAMS, MILLIGRAMS");
        System.out.print("Введите новый UnitOfMeasure (" + existingProduct.getUnitOfMeasure() + "): ");
        String unitInput = scanner.nextLine().trim();
        if (!unitInput.isEmpty()) {
            existingProduct.setUnitOfMeasure(UnitOfMeasure.valueOf(unitInput));
        }

        // Ввод владельца (Person)
        System.out.print("Введите новое имя владельца (" + existingProduct.getOwner().getName() + "): ");
        String ownerName = scanner.nextLine().trim();
        if (!ownerName.isEmpty()) {
            existingProduct.getOwner().setName(ownerName);
        }

        System.out.print("Введите новый паспорт владельца (" + existingProduct.getOwner().getPassportID() + "): ");
        String passportID = scanner.nextLine().trim();
        if (!passportID.isEmpty()) {
            existingProduct.getOwner().setPassportID(passportID);
        }

        // Ввод цвета глаз
        System.out.println("Доступные цвета глаз: YELLOW, ORANGE, BROWN, RED, BLACK, BLUE");
        System.out.print("Введите новый цвет глаз (" + existingProduct.getOwner().getEyeColor() + "): ");
        String eyeColorInput = scanner.nextLine().trim();
        if (!eyeColorInput.isEmpty()) {
            existingProduct.getOwner().setEyeColor(Color.valueOf(eyeColorInput));
        }

        // Ввод цвета волос
        System.out.println("Доступные цвета волос: YELLOW, ORANGE, BROWN, RED, BLACK, BLUE");
        System.out.print("Введите новый цвет волос (" + existingProduct.getOwner().getHairColor() + "): ");
        String hairColorInput = scanner.nextLine().trim();
        if (!hairColorInput.isEmpty()) {
            existingProduct.getOwner().setHairColor(Color.valueOf(hairColorInput));
        }

        // Ввод национальности
        System.out.println("Доступные страны: USA, CHINA, INDIA, JAPAN");
        System.out.print("Введите новую страну владельца (" + existingProduct.getOwner().getNationality() + "): ");
        String nationalityInput = scanner.nextLine().trim();
        if (!nationalityInput.isEmpty()) {
            existingProduct.getOwner().setNationality(Country.valueOf(nationalityInput));
        }

        System.out.println("Продукт успешно обновлен: " + existingProduct);
    }
}

