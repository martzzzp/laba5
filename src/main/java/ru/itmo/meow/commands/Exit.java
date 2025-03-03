package ru.itmo.meow.commands;

public class Exit implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Завершение работы программы...");
        System.exit(0); // Завершаем выполнение программы
    }
}
