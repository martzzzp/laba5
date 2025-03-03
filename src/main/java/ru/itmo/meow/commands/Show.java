package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;

public class Show implements Command {
    private final ProductManager productManager;

    public Show(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        if (productManager.getProducts().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println("Элементы коллекции:");
            productManager.getProducts().forEach((id, product) -> System.out.println(product.toString()));
        }
    }
}

