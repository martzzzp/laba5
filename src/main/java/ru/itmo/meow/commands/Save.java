package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.model.Product;
import ru.itmo.meow.exceptions.InvalidInputException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Save implements Command {
    private final ProductManager productManager;
    private final String filePath = "products.xml"; // Имя файла для сохранения

    public Save(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute(String[] args) {
        if (productManager.getProducts().isEmpty()) {
            throw new InvalidInputException("Ошибка: Коллекция пуста, нечего сохранять.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<products>\n");

            for (Map.Entry<Long, Product> entry : productManager.getProducts().entrySet()) {
                Product product = entry.getValue();
                writer.write("  <product>\n");
                writer.write("    <id>" + product.getId() + "</id>\n");
                writer.write("    <name>" + product.getName() + "</name>\n");
                writer.write("    <price>" + product.getPrice() + "</price>\n");
                writer.write("    <partNumber>" + product.getPartNumber() + "</partNumber>\n");
                writer.write("    <manufactureCost>" + product.getManufactureCost() + "</manufactureCost>\n");
                writer.write("    <unitOfMeasure>" + product.getUnitOfMeasure() + "</unitOfMeasure>\n");
                writer.write("  </product>\n");
            }

            writer.write("</products>\n");
            System.out.println("Коллекция успешно сохранена в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
