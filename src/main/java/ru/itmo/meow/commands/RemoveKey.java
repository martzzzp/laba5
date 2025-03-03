package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.exceptions.InvalidInputException;
import ru.itmo.meow.exceptions.ElementNotFoundException;

public class RemoveKey implements Command {
    private final ProductManager productManager;

    public RemoveKey(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new InvalidInputException("Ошибка: Укажите `id` элемента, который хотите удалить.");
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

        productManager.removeProduct(id);
        System.out.println("Продукт с id " + id + " успешно удален.");
    }
}

