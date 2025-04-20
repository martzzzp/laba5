package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.entity.Product;
import org.example.managers.CollectionManager;

import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingUnitOfMeasureCommand extends Command {
    private final ConsoleOutput consoleOutput;

    public PrintFieldDescendingUnitOfMeasureCommand(ConsoleOutput consoleOutput) {
        super("print_field_descending_unit_of_measure", "вывести значения поля unitOfMeasure всех элементов в порядке убывания");
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            consoleOutput.printError("Команда не принимает аргументов");
            return;
        }
        if (CollectionManager.getCollection().isEmpty()) {
            consoleOutput.println("Коллекция пуста");
            return;
        }

        List<String> sortedUnitOfMeasures = CollectionManager.getCollection()
                .values()
                .stream()
                .map(Product::getUnitOfMeasure)     // p -> p.getUnitOfMeasure()
                .sorted(Comparator.reverseOrder())
                .map(Enum::toString)                // енам в строку для норм вывода
                .toList();

        sortedUnitOfMeasures.forEach(consoleOutput::println);
    }
}
