package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;
import java.util.List;
import org.example.common.entity.Product;

/**
 * Команда filter_less_than_price: выводит элементы, цена которых меньше заданной.
 */
public class FilterLessThanPriceCommand implements CommandInterface {
    private final CollectionManager cm;
    private final ConsoleOutput out;

    public FilterLessThanPriceCommand(CollectionManager cm, ConsoleOutput out) {
        this.cm = cm;
        this.out = out;
    }

    @Override
    public String getName() {
        return "filter_less_than_price";
    }

    @Override
    public String getDescription() {
        return "Показывает элементы с ценой меньше заданной";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        if (args.length < 1) {
            out.printError("filter_less_than_price requires a price argument");
            return null;
        }
        double threshold;
        try {
            threshold = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            out.printError("Invalid price: " + args[0]);
            return null;
        }
        List<Product> filtered = cm.filterLessThanPrice(threshold);
        for (Product product : filtered) {
            out.println(String.valueOf(product));
        }
        return null;
    }
}