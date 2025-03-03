package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
import ru.itmo.meow.exceptions.InvalidInputException;

import java.util.Iterator;
import java.util.Map;

public class RemoveLowerKey implements Command {
    private final ProductManager productManager;

    public RemoveLowerKey(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new InvalidInputException("Ошибка: Укажите `id`, меньше которого нужно удалить элементы.");
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

        // Удаляем все элементы, у которых id < заданного
        Iterator<Map.Entry<Long, Product>> iterator = productManager.getProducts().entrySet().iterator();
        int count = 0;

        while (iterator.hasNext()) {
            Map.Entry<Long, Product> entry = iterator.next();
            if (entry.getKey() < id) {
                iterator.remove();
                count++;
            }
        }

        if (count > 0) {
            System.out.println("Удалено " + count + " элементов, у которых id меньше " + id + ".");
        } else {
            System.out.println("Нет элементов с id меньше " + id + ".");
        }
    }
}
