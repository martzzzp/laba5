package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
import ru.itmo.meow.exceptions.InvalidInputException;

import java.util.List;
import java.util.stream.Collectors;

public class FilterGreaterThanPartNumber implements Command {
    private final ProductManager productManager;

    public FilterGreaterThanPartNumber(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new InvalidInputException("Ошибка: Укажите значение partNumber.");
        }

        String partNumber = args[0];

        List<Product> filteredProducts = productManager.getProducts().values().stream()
                .filter(product -> product.getPartNumber().compareTo(partNumber) > 0)
                .collect(Collectors.toList());

        if (filteredProducts.isEmpty()) {
            System.out.println("Нет элементов с partNumber больше " + partNumber);
        } else {
            System.out.println("Элементы с partNumber больше " + partNumber + ":");
            filteredProducts.forEach(System.out::println);
        }
    }
}

