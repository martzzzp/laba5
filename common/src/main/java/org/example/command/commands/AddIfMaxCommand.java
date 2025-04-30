package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;
import org.example.common.entity.Product;

/**
 * Команда add_if_max: добавляет новый элемент, если его значение больше всех остальных.
 */
public class AddIfMaxCommand implements CommandInterface {
    private final CollectionManager cm;
    private final ConsoleOutput out;

    public AddIfMaxCommand(CollectionManager cm, ConsoleOutput out) {
        this.cm = cm;
        this.out = out;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "Добавляет элемент, если его значение больше всех в коллекции";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        if (!(payload instanceof Product)) {
            out.printError("Payload must be a Product for add_if_max");
            return null;
        }
        Product p = (Product) payload;
        boolean added = cm.addIfMax(p);
        if (added) {
            out.println("Element added as max.");
        } else {
            out.println("Element not added: not greater than existing max.");
        }
        return null;
    }
}