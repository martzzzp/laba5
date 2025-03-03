package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandExecutor {
    private final Map<String, Command> commands = new HashMap<>();
    private final LocalDateTime initializationTime = LocalDateTime.now(); // Дата запуска

    public CommandExecutor(ProductManager productManager, Scanner scanner) {
        commands.put("help", new Help());
        commands.put("info", new Info(productManager, initializationTime));
        commands.put("show", new Show(productManager));
        commands.put("insert", new Insert(productManager, scanner));
        commands.put("update", new Update(productManager, scanner));
        commands.put("remove_key", new RemoveKey(productManager)); // Добавили remove_key
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




