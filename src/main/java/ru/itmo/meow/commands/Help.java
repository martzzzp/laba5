package ru.itmo.meow.commands;

public class Help implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Доступные команды:");
        System.out.println("help - вывести справку");
        System.out.println("info - информация о коллекции");
        System.out.println("show - показать все элементы");
    }
}
