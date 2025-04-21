package org.example.command;

/**
 * Общий интерфейс для всех команд.
 */
public interface CommandInterface {
    /**
     * Выполнить команду с заданными аргументами.
     * @param args аргументы команды
     */
    void execute(String[] args);

    /**
     * Внутреннее имя команды (то, что вводит пользователь).
     */
    String getName();
}
