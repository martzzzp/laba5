package ru.itmo.meow;

import ru.itmo.meow.manager.ProductManager;
import ru.itmo.meow.commands.CommandExecutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager(new HashMap<>());

        // Список XML-файлов для загрузки коллекции
        List<String> xmlFiles = Arrays.asList("product_data1.xml", "product_data2.xml", "product_data3.xml");

        // Загружаем коллекцию из всех XML-файлов
        productManager.loadFromXML(xmlFiles);

        // Создаем обработчик команд
        CommandExecutor executor = new CommandExecutor(productManager, scanner);

        // Главный цикл программы
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String[] arguments = (parts.length > 1) ? parts[1].split(" ") : new String[0];

            executor.executeCommand(command, arguments);
        }
    }
}
