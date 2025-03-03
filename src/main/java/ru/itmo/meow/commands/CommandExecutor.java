package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandExecutor {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandExecutor(ProductManager productManager, Scanner scanner) {
        commands.put("help", new Help());
        // Дата запуска
        LocalDateTime initializationTime = LocalDateTime.now();
        commands.put("info", new Info(productManager, initializationTime));
        commands.put("show", new Show(productManager));
        commands.put("insert", new Insert(productManager, scanner));
        commands.put("update", new Update(productManager, scanner));
        commands.put("remove_key", new RemoveKey(productManager)); // Добавили remove_key
        commands.put("clear", new Clear(productManager)); // Добавили clear
        commands.put("save", new Save(productManager)); // Добавили save
        commands.put("execute_script", new ExecuteScript(this)); // Добавили execute_script
        commands.put("exit", new Exit()); // Добавили exit
        commands.put("remove_greater", new RemoveGreater(productManager, scanner)); // Добавили remove_greater
        commands.put("replace_if_lowe", new ReplaceIfLower(productManager, scanner));
        commands.put("remove_lower_key", new RemoveLowerKey(productManager));
        commands.put("count_less_than_manufacture_cost", new CountLessThanManufactureCost(productManager));
        commands.put("filter_greater_than_part_number", new FilterGreaterThanPartNumber(productManager));
        commands.put("print_field_descending_unit_of_measure", new PrintFieldDescendingUnitOfMeasure(productManager));
    }

    public void executeCommand(String command, String[] args) {
        Command cmd = commands.get(command);
        if (cmd != null) {
            try {
                cmd.execute(args);
            } catch (Exception e) {
                System.out.println(e.getMessage()); // Ловим и выводим ошибки
            }
        } else {
            System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
        }
    }
}




