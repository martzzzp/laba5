package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;

public class InfoCommand implements CommandInterface {
    private final CollectionManager coll;
    private final ConsoleOutput out;

    public InfoCommand(CollectionManager coll, ConsoleOutput out) {
        this.coll = coll;
        this.out = out;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Выводит информацию о коллекции";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        String result = coll.info();
        out.println(result);
        return payload;
    }
}
