package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;
import org.example.common.entity.Product;

/**
 * Команда remove_greater: удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreaterCommand implements CommandInterface {
    private final CollectionManager cm;
    private final ConsoleOutput out;

    public RemoveGreaterCommand(CollectionManager cm, ConsoleOutput out) {
        this.cm = cm;
        this.out = out;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "Удаляет все элементы, превышающие заданный по значению";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        if (!(payload instanceof Product)) {
            out.printError("Payload must be a Product for remove_greater");
            return null;
        }
        Product p = (Product) payload;
        long removedCount = cm.removeGreater(p);
        out.println("Removed " + removedCount + " elements greater than given.");
        return null;
    }
}