package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;
/**
 * Команда clear: очищает всю коллекцию.
 */
public class ClearCommand implements CommandInterface {
    private final CollectionManager cm;
    private final ConsoleOutput out;

    public ClearCommand(CollectionManager cm, ConsoleOutput out) {
        this.cm = cm;
        this.out = out;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Очищает коллекцию";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        cm.clear();
        out.println("Collection cleared.");
        return null;
    }
}