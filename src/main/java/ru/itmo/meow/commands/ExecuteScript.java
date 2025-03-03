package ru.itmo.meow.commands;

import ru.itmo.meow.exceptions.InvalidInputException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScript implements Command {
    private final CommandExecutor commandExecutor;

    public ExecuteScript(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new InvalidInputException("Ошибка: Укажите имя файла.");
        }

        String fileName = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("Выполнение скрипта из файла: " + fileName);

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    System.out.println("> " + line); // Показываем команду перед выполнением
                    String[] parts = line.split(" ", 2);
                    String command = parts[0];
                    String[] arguments = (parts.length > 1) ? parts[1].split(" ") : new String[0];

                    commandExecutor.executeCommand(command, arguments);
                }
            }
            System.out.println("Скрипт выполнен успешно.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
