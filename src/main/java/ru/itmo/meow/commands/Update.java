package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
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

        System.out.print("Введите новое название продукта (" + existingProduct.getName() + "): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            existingProduct.setName(name);
        }

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

        System.out.println("Продукт успешно обновлен: " + existingProduct);
    }
}

