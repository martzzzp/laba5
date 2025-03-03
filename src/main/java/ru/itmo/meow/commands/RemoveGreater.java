package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
import ru.itmo.meow.exceptions.InvalidInputException;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class RemoveGreater implements Command {
    private final ProductManager productManager;
    private final Scanner scanner;

    public RemoveGreater(ProductManager productManager, Scanner scanner) {
        this.productManager = productManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Удаление всех элементов, у которых цена больше заданной...");

        System.out.print("Введите цену для сравнения: ");
        long price;
        try {
            price = Long.parseLong(scanner.nextLine().trim());
            if (price <= 0) {
                throw new InvalidInputException("Ошибка: Цена должна быть больше 0.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Ошибка: Цена должна быть числом.");
        }

        // Удаляем все элементы, у которых price > заданного
        Iterator<Map.Entry<Long, Product>> iterator = productManager.getProducts().entrySet().iterator();
        int count = 0;

        while (iterator.hasNext()) {
            Map.Entry<Long, Product> entry = iterator.next();
            if (entry.getValue().getPrice() > price) {
                iterator.remove();
                count++;
            }
        }

        System.out.println("Удалено " + count + " элементов, у которых цена больше " + price + ".");
    }
}

