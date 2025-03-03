package ru.itmo.meow.commands;

public interface Command {
    void execute(String[] args); // Все команды должны иметь этот метод
}

