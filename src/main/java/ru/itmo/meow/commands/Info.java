package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import java.time.LocalDateTime;

public class Info implements Command {
    private final ProductManager productManager;
    private final LocalDateTime initializationTime; // Храним дату инициализации коллекции

    public Info(ProductManager productManager, LocalDateTime initializationTime) {
        this.productManager = productManager;
        this.initializationTime = initializationTime;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: HashMap<Long, Product>");
        System.out.println("Дата инициализации: " + initializationTime);
        System.out.println("Количество элементов: " + productManager.getProducts().size());
    }
}

