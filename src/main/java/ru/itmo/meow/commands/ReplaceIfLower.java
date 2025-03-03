package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
import ru.itmo.meow.exceptions.InvalidInputException;
import ru.itmo.meow.exceptions.ElementNotFoundException;

import java.util.Scanner;

public class ReplaceIfLower implements Command {
    private final ProductManager productManager;
    private final Scanner scanner;

    public ReplaceIfLower(ProductManager productManager, Scanner scanner) {
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
        System.out.println("Текущая цена продукта: " + existingProduct.getPrice());
        System.out.print("Введите новую цену (должна быть меньше текущей): ");

        long newPrice;
        try {
            newPrice = Long.parseLong(scanner.nextLine().trim());
            if (newPrice <= 0) {
                throw new InvalidInputException("Ошибка: Цена должна быть больше 0.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Ошибка: Цена должна быть числом.");
        }

        if (newPrice < existingProduct.getPrice()) {
            existingProduct.setPrice(newPrice);
            System.out.println("Цена продукта обновлена: " + existingProduct);
        } else {
            System.out.println("Цена не изменена, так как введенное значение не меньше текущего.");
        }
    }
}

