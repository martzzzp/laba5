package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;

public class ShowCommand implements CommandInterface {
    private final CollectionManager coll;
    private final ConsoleOutput out;

    public ShowCommand(CollectionManager coll, ConsoleOutput out) {
        this.coll = coll;
        this.out = out;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Показывает все элементы коллекции";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        String result = coll.show();
        out.println(result);
        return payload;
    }
}
