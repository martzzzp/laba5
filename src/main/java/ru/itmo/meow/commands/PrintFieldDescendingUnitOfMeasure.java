package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
import ru.itmo.meow.model.UnitOfMeasure;

import java.util.List;
import java.util.stream.Collectors;

public class PrintFieldDescendingUnitOfMeasure implements Command {
    private final ProductManager productManager;

    public PrintFieldDescendingUnitOfMeasure(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        List<UnitOfMeasure> sortedUnits = productManager.getProducts().values().stream()
                .map(Product::getUnitOfMeasure)
                .filter(unit -> unit != null) // Исключаем null значения
                .distinct() // Убираем дубликаты
                .sorted((a, b) -> b.compareTo(a)) // Сортируем по убыванию
                .collect(Collectors.toList());

        if (sortedUnits.isEmpty()) {
            System.out.println("В коллекции нет значений unitOfMeasure.");
        } else {
            System.out.println("unitOfMeasure в порядке убывания:");
            sortedUnits.forEach(System.out::println);
        }
    }
}

