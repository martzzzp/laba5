package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
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

        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new InvalidInputException("Ошибка: Название продукта не может быть пустым.");
        }

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

        Product newProduct = new Product(id, name, null, price, "", 0, null, null);
        productManager.addProductWithKey(id, newProduct);

        System.out.println("Продукт успешно добавлен: " + newProduct);
    }
}


