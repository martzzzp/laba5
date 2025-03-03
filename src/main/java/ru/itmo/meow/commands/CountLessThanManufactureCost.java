package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
import ru.itmo.meow.exceptions.InvalidInputException;

public class CountLessThanManufactureCost implements Command {
    private final ProductManager productManager;

    public CountLessThanManufactureCost(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new InvalidInputException("Ошибка: Укажите значение manufactureCost.");
        }

        float manufactureCost;
        try {
            manufactureCost = Float.parseFloat(args[0]);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Ошибка: manufactureCost должен быть числом.");
        }

        long count = productManager.getProducts().values().stream()
                .filter(product -> product.getManufactureCost() < manufactureCost)
                .count();

        System.out.println("Количество элементов с manufactureCost меньше " + manufactureCost + ": " + count);
    }
}

