package ru.itmo.meow;

import ru.itmo.meow.managers.ProductManager;
import ru.itmo.meow.commands.CommandExecutor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        CommandExecutor executor = new CommandExecutor(productManager);

        while (true) {
            System.out.print("> "); // Ждем ввод команды
            String command = scanner.nextLine();
            executor.executeCommand(command); // Выполняем команду
        }
    }
}

