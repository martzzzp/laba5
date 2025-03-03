package ru.itmo.meow.commands;

import ru.itmo.meow.manager.ProductManager;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commands = new HashMap<>();
    private final LocalDateTime initializationTime = LocalDateTime.now(); // Фиксируем время запуска

    public CommandExecutor(ProductManager productManager) {
        commands.put("help", new Help());
        commands.put("info", new Info(productManager, initializationTime)); // Передаем дату
        commands.put("show", new Show(productManager));
    }

    public void executeCommand(String command, String[] args) {
        Command cmd = commands.get(command);
        if (cmd != null) {
            cmd.execute(args);
        } else {
            System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
        }
    }
}


