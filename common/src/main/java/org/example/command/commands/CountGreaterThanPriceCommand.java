package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;

/**
 * Команда count_greater_than_price: считает, сколько элементов имеют цену больше заданной.
 */
public class CountGreaterThanPriceCommand implements CommandInterface {
    private final CollectionManager cm;
    private final ConsoleOutput out;

    public CountGreaterThanPriceCommand(CollectionManager cm, ConsoleOutput out) {
        this.cm = cm;
        this.out = out;
    }

    @Override
    public String getName() {
        return "count_greater_than_price";
    }

    @Override
    public String getDescription() {
        return "Считает элементы с ценой больше заданной";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        if (args.length < 1) {
            out.printError("count_greater_than_price requires a price argument");
            return null;
        }
        double threshold;
        try {
            threshold = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            out.printError("Invalid price: " + args[0]);
            return null;
        }
        long count = cm.countGreaterThanPrice(threshold);
        out.println("Count: " + count);
        return null;
    }
}