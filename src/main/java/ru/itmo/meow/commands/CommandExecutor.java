package ru.itmo.meow.commands;

import ru.itmo.meow.managers.ProductManager;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandExecutor(ProductManager productManager) {
        // Регистрируем команды (будем добавлять сюда новые классы)
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(productManager));
        commands.put("show", new ShowCommand(productManager));
    }

    public void executeCommand(String command, String[] args) {
        Command cmd = commands.get(command);
        if (cmd != null) {
            cmd.execute(args); // Выполняем команду
        } else {
            System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
        }
    }
}


