package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;

public class Clear implements Command {
    private final ProductManager productManager;

    public Clear(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        if (productManager.getProducts().isEmpty()) {
            System.out.println("Коллекция уже пуста.");
        } else {
            productManager.clear();
            System.out.println("Все элементы коллекции успешно удалены.");
        }
    }
}
